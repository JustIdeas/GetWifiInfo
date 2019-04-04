package com.example.getwifiinfo;
import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.TextView;
import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.content.pm.PackageManager;

public class WifiStatus {

    private static String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_NETWORK_STATE,
    };;
    TextView mac;
    TextView linkspeed;
    TextView frequency;
    TextView RSSI;
    TextView ssid;

    public WifiStatus() {


    }



    public static String GetWifi_BSSID(Context context) {
        if (grantAccess(context, "Manifest.permission.ACCESS_FINE_LOCATION")){
            return "Not Granted";

        }else{

            WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            String address = info.getBSSID();
            return address;

        }
    }

    public static int  GetWifi_Link(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        int speed = info.getLinkSpeed();
        return speed;

    }

    public static int  GetWifi_Freq(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        int freq = info.getFrequency();
        return freq;

    }

    public static int  GetWifi_RSSI(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        Integer progressrssi = info.getRssi();
        return  progressrssi;

    }
    public static String  GetWifi_SSID(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String ssidget = info.getSSID();
        return ssidget;

    }
    public static boolean grantAccess(Context context, String Permission) {
        Integer OK = 0;
        if (ContextCompat.checkSelfPermission(context, Permission) != PackageManager.PERMISSION_GRANTED) {
            //Permission Not Granted
            ActivityCompat.requestPermissions((Activity) context, permissions, 1);
            return false;
        } else {
            return true;

        }

    }

    }

