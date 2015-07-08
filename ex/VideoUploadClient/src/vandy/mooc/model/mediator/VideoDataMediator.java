package vandy.mooc.model.mediator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.mime.TypedFile;

import vandy.mooc.model.mediator.webdata.Video;
import vandy.mooc.model.mediator.webdata.VideoServiceProxy;
import vandy.mooc.model.mediator.webdata.VideoStatus;
import vandy.mooc.model.mediator.webdata.VideoStatus.VideoState;
import vandy.mooc.utils.Constants;
import vandy.mooc.utils.VideoMediaStoreUtils;
import android.content.Context;
import android.net.Uri;

/**
 * Mediates communication between the Video Service and the local
 * storage on the Android device.  The methods in this class block, so
 * they should be called from a background thread (e.g., via an
 * AsyncTask).
 */
public class VideoDataMediator {
    /**
     * Status code to indicate that file is successfully
     * uploaded.
     */
    public static final String STATUS_UPLOAD_SUCCESSFUL =
        "Upload succeeded";
    
    /**
     * Status code to indicate that file upload failed 
     * due to large video size.
     */
    public static final String STATUS_UPLOAD_ERROR_FILE_TOO_LARGE =
        "Upload failed: File too big";
    
    /**
     * Status code to indicate that file upload failed.
     */
    public static final String STATUS_UPLOAD_ERROR =
        "Upload failed";
    
    /**
     * Defines methods that communicate with the Video Service.
     */
    private VideoServiceProxy mVideoServiceProxy;
    
    /**
     * Constructor that initializes the VideoDataMediator.
     * 
     * @param context
     */
    public VideoDataMediator() {
        // Initialize the VideoServiceProxy.
        mVideoServiceProxy = new RestAdapter
            .Builder()
            .setEndpoint(Constants.SERVER_URL)
            .build()
            .create(VideoServiceProxy.class);
    }

    /**
     * Uploads the Video having the given Id.  This Id is the Id of
     * Video in Android Video Content Provider.
     * 
     * @param videoId
     *            Id of the Video to be uploaded.
     *
     * @return A String indicating the status of the video upload operation.
     */
    public String uploadVideo(Context context,
                              Uri videoUri) {
        // Get the path of video file from videoUri.
        String filePath = VideoMediaStoreUtils.getPath(context,
                                                       videoUri);
        
        // Get the Video from Android Video Content Provider having
        // the given filePath.
        Video androidVideo = 
            VideoMediaStoreUtils.getVideo(context,
                                          filePath);
        
        // Check if any such Video exists in Android Video Content
        // Provider.
        if (androidVideo != null) {
            // Add the metadata of the Video to the Video Service and
            // get the resulting Video that contains additional
            // meta-data (e.g., Id and ContentType) generated by the
            // Video Service.
            Video receivedVideo = 
                mVideoServiceProxy.addVideo(androidVideo);

            // Check if the Video Service returned any Video metadata.
            if (receivedVideo != null) {
                // Prepare to Upload the Video data.
                              
                // Create an instance of the file to upload.
                File videoFile = new File(filePath);
                      
                // Check if the file size is less than the size of the
                // video that can be uploaded to the Video Service.
                if (videoFile.length() < Constants.MAX_SIZE_MEGA_BYTE) {
                    // Upload the Video data to the Video Service and get the
                    // status of the uploaded video data.
                    VideoStatus status =
                        mVideoServiceProxy.setVideoData
                            (receivedVideo.getId(),
                             new TypedFile(receivedVideo.getContentType(),
                                           videoFile));

                    // Check if the Status of the Video is ready or not.
                    if (status.getState() == VideoState.READY) 
                        // Video successfully uploaded.
                        return STATUS_UPLOAD_SUCCESSFUL;
                } else 
                    // Video can't be uploaded due to large video size.
                    return STATUS_UPLOAD_ERROR_FILE_TOO_LARGE;
            }
        }

        // Error occured while uploading the video.
        return STATUS_UPLOAD_ERROR;
    }

    /**
     * Get the List of Videos from Video Service.
     *
     * @return the List of Videos from Server or null if there is
     *         failure in getting the Videos.
     */
    public List<Video> getVideoList() {
        return (ArrayList<Video>) mVideoServiceProxy.getVideoList();
    }
}
