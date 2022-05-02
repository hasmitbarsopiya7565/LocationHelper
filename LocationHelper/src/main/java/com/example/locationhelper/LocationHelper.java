package com.example.locationhelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.location.GpsTracker;
import com.example.location.MainActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper extends AppCompatActivity {


    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static double latitude,longitude;

    public static double getLatitude() {
        return latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static String getAddress() {
        return address;
    }

    public static String getCity() {
        return city;
    }

    public static String getState() {
        return state;
    }

    public static String getCountry() {
        return country;
    }

    public static String getPostalCode() {
        return postalCode;
    }

    public static String getKnownName() {
        return knownName;
    }

    private static String address,city,state,country,postalCode,knownName;

    public LocationHelper(Context context) {
        LocationHelper.context = context;
        getLocation();
    }


    public static void getLocation(){

        GpsTracker gpsTracker = new GpsTracker(context);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            List<Address> addresses = null;
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                city = addresses.get(0).getLocality();
                state = addresses.get(0).getAdminArea();
                country = addresses.get(0).getCountryName();
                postalCode = addresses.get(0).getPostalCode();
                knownName = addresses.get(0).getFeatureName(); // Only if Available
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    /*public double[] getCurrentLatitudeLongitude(){
        double[] latitudeLongitude = new double[2];
        gpsTracker = new GpsTracker(context);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            latitudeLongitude[0] = latitude;
            latitudeLongitude[1] = longitude;

        }else{
            gpsTracker.showSettingsAlert();
        }
        return latitudeLongitude;
    }

    public String getAddressFromCurrentLatitudeLongitude(){
        String address = "";
        gpsTracker = new GpsTracker(context);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            List<Address> addresses = null;
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName(); // Only if Available
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            gpsTracker.showSettingsAlert();
        }
        return address;
    }*/
}
