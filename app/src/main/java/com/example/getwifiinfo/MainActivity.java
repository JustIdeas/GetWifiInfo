package com.example.getwifiinfo;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import android.os.Parcel;

import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.Calendar;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.CircularArray;
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
import java.util.Dictionary;
import java.util.List;

import android.os.SystemClock;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity   {
    boolean ThreadStatus = true;
    JSONObject infoJson = new JSONObject();
    boolean Circulararray_boolean = true;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_DISPLAY);

        final Button getinfo = findViewById(R.id.getInfo);

        getinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try {

                    getinfo.setEnabled(false);
                    ThreadStatus = true;
                    Threading_DriverInfo(ThreadStatus);
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
                Integer freq = wifistatus.GetWifi_Freq(main);
                Integer rssi = wifistatus.GetWifi_RSSI(main);
                String ssid = wifistatus.GetWifi_SSID(main);



                String delay_str = delay.getText().toString();
                String serverIp_str = serverIp.getText().toString();
                String serverPort_str = serverPort.getText().toString();
                String testname_str = testname.getText().toString();



                UdpSocketSend socketSend = new UdpSocketSend(serverIp_str, serverPort_str, mac_field, ssid, freq, rssi,getTime(), testname_str);


                Integer Sequence = 0;
                while(ThreadStatus){
                    if(!Circulararray_boolean) {

                        try {

                            Sequence++;
                            Thread.sleep(Integer.parseInt(delay_str));
                            JSONObject newinfo = infoJson;
                            newinfo.put("sequence", Sequence );
                            newinfo.put("Time", getTime() );
                            socketSend.write2(newinfo);
                        } catch (Exception e) {
                            continue;

                        }

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
                TextView link_speed = findViewById(R.id.linkSpeed);
                TextView freq = findViewById(R.id.freq);
                TextView rssi = findViewById(R.id.rssi);
                TextView ssid = findViewById(R.id.ssidfield);

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

                    }

                }
            }
        });
        screen.start();


    }


    public void Threading_DriverInfo(boolean statusThread) throws InterruptedException, Exception{
        final MainActivity main = this;
        Thread buffer = new Thread(new Runnable(){
            @Override
            public void run() {

                WifiStatus wifistatus = new WifiStatus();
                while(ThreadStatus){
                        try {
                            Thread.sleep(1);
                            String mac_field_ = wifistatus.GetWifi_BSSID(main);
                            Integer freq_ = wifistatus.GetWifi_Freq(main);
                            Integer rssi_ = wifistatus.GetWifi_RSSI(main);
                            String ssid_ = wifistatus.GetWifi_SSID(main);
                            EditText testname = findViewById(R.id.testname);
                            String testname_str_ = testname.getText().toString();

                            infoJson.put("BSSID", mac_field_);
                            infoJson.put("SSID", ssid_);
                            infoJson.put("frequency", freq_);
                            infoJson.put("Signal", rssi_);
                            infoJson.put("Testname", testname_str_);

                            Circulararray_boolean = false;

                        } catch (Exception e) {
                            continue;

                        }

                }
            }
        });
        buffer.start();


    }
}
