package com.example.getwifiinfo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.ProgressBar;
import android.widget.TextView;


public class WifiStatus {

    TextView mac;
    TextView linkspeed;
    TextView frequency;
    TextView RSSI;
    TextView ssid;

    public WifiStatus() {


    }



    public static String GetWifi_BSSID(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getBSSID();
        return address;

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
    }

