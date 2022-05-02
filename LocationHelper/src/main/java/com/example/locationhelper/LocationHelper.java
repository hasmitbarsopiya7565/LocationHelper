package com.example.locationhelper;

import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.location.MainActivity;

public class LocationHelper extends MainActivity {

    public void getLocationDetails(){
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }else{
                getLocation();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
