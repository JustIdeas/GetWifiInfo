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

    public WifiStatus(TextView mac, TextView linkspeed, TextView frequency, TextView RSSI, TextView ssid) {

        this.mac = mac;
        this.linkspeed = linkspeed;
        this.frequency = frequency;
        this.RSSI = RSSI;
        this.ssid = ssid;

    }



    public void GetWifi_BSSID(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String address = info.getBSSID();
        TextView new_mac = mac.findViewById(R.id.Text_MAC);
        new_mac.setText(address);

        }

    public void GetWifi_Link(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String  speed = Integer.toString(info.getLinkSpeed());
        TextView textspeed = linkspeed.findViewById(R.id.linkSpeed);
        textspeed.setText(speed);

    }

    public void GetWifi_Freq(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String freq = Integer.toString(info.getFrequency());
        TextView textfreq = frequency.findViewById(R.id.freq);
        textfreq.setText(freq);

    }

    public void GetWifi_RSSI(Context context, ProgressBar signal) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String rssi = Integer.toString(info.getRssi());
        Integer progressrssi = info.getRssi();
        TextView textrssi = RSSI.findViewById(R.id.rssi);
        ProgressBar signalevel = signal.findViewById(R.id.progressBar);
        signalevel.setProgress(Math.abs(progressrssi));
        textrssi.setText(rssi);

    }
    public void GetWifi_SSID(Context context) {

        WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String ssidget = info.getSSID();
        TextView textssid = ssid.findViewById(R.id.ssidfield);
        textssid.setText(ssidget);

    }
    }

