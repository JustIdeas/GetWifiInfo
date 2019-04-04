package com.example.getwifiinfo;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.Parcel;

import java.time.LocalTime;
import java.util.Calendar;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import java.lang.Thread;


public class MainActivity extends AppCompatActivity   {
    boolean ThreadStatus = true;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_DISPLAY);


//        grantAccess(this, "Manifest.permission.ACCESS_FINE_LOCATION");
//        grantAccess(this, "Manifest.permission.ACCESS_WIFI_STATE");
//        grantAccess(this, "Manifest.permission.ACCESS_COARSE_LOCATION");
//        grantAccess(this, "Manifest.permission.ACCESS_NETWORK_STATE");

        final Button getinfo = findViewById(R.id.getInfo);

        getinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try {

                    getinfo.setEnabled(false);
                    ThreadStatus = true;
                    Threading_SendInfo(ThreadStatus);
                    Threading_InterfaceInfo(ThreadStatus);


                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

        Button STOP = findViewById(R.id.STOP);
        STOP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                ThreadStatus = false;
                getinfo.setEnabled(true);

            }

        });

    }

    public static Long getTime(){
        Calendar calendar = Calendar.getInstance();
        //Returns current time in millis
        long timeMilli2 = calendar.getTimeInMillis();
        return timeMilli2;
    }
    public void Threading_SendInfo(boolean statusThread) throws InterruptedException{
        final MainActivity main = this;
        Thread info = new Thread(new Runnable(){
            @Override
            public void run() {
                Process.setThreadPriority(Process.THREAD_PRIORITY_DEFAULT);
                WifiStatus wifistatus = new WifiStatus();

                String mac_field = wifistatus.GetWifi_BSSID(main);
                EditText delay = findViewById(R.id.delay);
                EditText serverIp = findViewById(R.id.serverIP);
                EditText serverPort = findViewById(R.id.serverPort);
                EditText testname = findViewById(R.id.testname);
                Integer link_speed = wifistatus.GetWifi_Link(main);
                Integer freq = wifistatus.GetWifi_Freq(main);
                Integer rssi = wifistatus.GetWifi_RSSI(main);
                String ssid = wifistatus.GetWifi_SSID(main);



                String delay_str = delay.getText().toString();
                String serverIp_str = serverIp.getText().toString();
                String serverPort_str = serverPort.getText().toString();
                String testname_str = testname.getText().toString();



                UdpSocketSend socketSend = new UdpSocketSend(serverIp_str, serverPort_str, mac_field, ssid, freq, rssi,getTime(), testname_str);

//                ThreadStatus = true;
                Integer Sequence = 0;
                while(ThreadStatus){

                    try {
                        Sequence++;
                        Thread.sleep(Integer.parseInt(delay_str));
                        socketSend.write(main, Sequence,wifistatus.GetWifi_BSSID(main), wifistatus.GetWifi_SSID(main), wifistatus.GetWifi_Freq(main), wifistatus.GetWifi_RSSI(main), getTime(), testname_str);


                    }catch (Exception e) {
                        continue;
//                        e.printStackTrace();

                    }

                }
            }
        });
        info.start();


    }
    public void Threading_InterfaceInfo(boolean statusThread) throws InterruptedException{
        final MainActivity main = this;
        Thread screen = new Thread(new Runnable(){
            @Override
            public void run() {


                TextView mac_field = findViewById(R.id.Text_MAC);
                EditText delay = findViewById(R.id.delay);
                EditText serverIp = findViewById(R.id.serverIP);
                EditText serverPort = findViewById(R.id.serverPort);
                TextView link_speed = findViewById(R.id.linkSpeed);
                TextView freq = findViewById(R.id.freq);
                TextView rssi = findViewById(R.id.rssi);
                TextView ssid = findViewById(R.id.ssidfield);
//                ProgressBar progress = findViewById(R.id.progressBar);
//
//
//                progress.setMax(100);

                WifiStatus wifistatus = new WifiStatus();



                Integer Sequence = 0;
                while(ThreadStatus){

                    try {

                        Thread.sleep(1000);
                        getTime();
                        mac_field.setText(wifistatus.GetWifi_BSSID(main));
                        link_speed.setText(Integer.toString(wifistatus.GetWifi_Link(main)));
                        freq.setText(Integer.toString(wifistatus.GetWifi_Freq(main)));
                        rssi.setText(Integer.toString(wifistatus.GetWifi_RSSI(main)));
                        ssid.setText(wifistatus.GetWifi_SSID(main));

                    }catch (Exception e) {
                        continue;
//                        e.printStackTrace();

                    }

                }
            }
        });
        screen.start();


    }

}
