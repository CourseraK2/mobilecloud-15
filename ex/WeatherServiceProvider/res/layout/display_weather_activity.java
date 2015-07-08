<!-- Master layout. -->
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
 
    <LinearLayout
        android:gravity="center_vertical"
        android:layout_gravity="left"
        android:layout_height="wrap_content"
        android:layout_width="match_parent"
        android:orientation="vertical"
        android:padding="16dp">
        
        <!-- Header: Location, Day, Date -->
        <TextView
            android:fontFamily="sans-serif-condensed"
            android:id="@+id/detail_locationName"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:layout_marginBottom="25dp"
            android:paddingTop="5dp"
            android:ellipsize="end"
            android:singleLine="true"
            android:textColor="@color/blue"
            android:textSize="34sp" />
        
        <TextView
            android:fontFamily="sans-serif"
            android:id="@+id/detail_day_textview"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:textColor="@color/black"
            android:textSize="23sp" />

        <TextView
            android:fontFamily="sans-serif-condensed"
            android:id="@+id/detail_date_textview"
            android:layout_height="wrap_content"
            android:layout_width="wrap_content"
            android:textColor="@color/grey_700"
            android:textSize="16sp" />

        <!-- Main content: high, low, art, weather state -->
        <LinearLayout
            android:gravity="center_horizontal"
            android:layout_height="match_parent"
            android:layout_marginTop="16dp"
            android:layout_width="match_parent"
            android:orientation="horizontal">

            <LinearLayout
                android:gravity="left"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:orientation="vertical">

                <TextView
                    android:fontFamily="sans-serif-light"
                    android:id="@+id/detail_high_textview"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:textSize="86sp" />
                
                <TextView
                    android:id="@+id/detail_low_textview"
                    android:layout_gravity="center_horizontal"
                    android:layout_height="wrap_content"
                    android:layout_width="wrap_content"
                    android:textColor="@color/grey_700"
                    android:textSize="48sp" />

            </LinearLayout>

            <LinearLayout
                android:gravity="center_horizontal"
                android:layout_gravity="center"
                android:layout_height="wrap_content"
                android:layout_width="wrap_content"
                android:orientation="vertical">

                <ImageView
                    android:id="@+id/detail_icon"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content" />

                <TextView
                    android:id="@+id/detail_forecast_textview"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:fontFamily="sans-serif"
                    android:textAppearance="?android:textAppearanceLarge"
                    android:textColor="@color/grey_700" />
            </LinearLayout>
        </LinearLayout>

        <!-- Sunrise, Sunset, Humidity, Wind -->
        <LinearLayout
            android:layout_height="match_parent"
            android:layout_marginRight="0dp"
            android:layout_marginTop="16dp"
            android:layout_width="match_parent"
            android:orientation="vertical">
            
             <TextView
                android:id="@+id/detail_sunrise_textview"
                android:layout_height="wrap_content"
                android:layout_width="match_parent"
                android:fontFamily="sans-serif"
                android:textAppearance="?android:textAppearanceLarge" />
             
              <TextView
                android:id="@+id/detail_sunset_textview"
                android:layout_height="wrap_content"
                android:layout_marginTop="4dp"
                android:layout_width="match_parent"
                android:fontFamily="sans-serif"
                android:textAppearance="?android:textAppearanceLarge" />

            <TextView
                android:id="@+id/detail_humidity_textview"
                android:layout_height="wrap_content"
                android:layout_width="match_parent"
                android:layout_marginTop="4dp"
                android:fontFamily="sans-serif"
                android:textAppearance="?android:textAppearanceLarge" />

            <TextView
                android:id="@+id/detail_wind_textview"
                android:layout_height="wrap_content"
                android:layout_marginTop="4dp"
                android:layout_width="match_parent"
                android:fontFamily="sans-serif"
                android:textAppearance="?android:textAppearanceLarge" />
        </LinearLayout>
    </LinearLayout>
</ScrollView>
