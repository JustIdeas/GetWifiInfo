package com.example.getwifiinfo;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Button;
import java.lang.Thread;




public class MainActivity extends AppCompatActivity  {
    boolean ThreadStatus = true;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        final Button getinfo = findViewById(R.id.getInfo);

        getinfo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                try {

                    getinfo.setEnabled(false);
                    Threading(ThreadStatus);

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
    public void Threading(boolean statusThread) throws InterruptedException{
        final MainActivity main = this;
        Thread t = new Thread(new Runnable(){
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
                ProgressBar progress = findViewById(R.id.progressBar);

                progress.setMax(100);

                WifiStatus wifistatus = new WifiStatus(mac_field, link_speed, freq, rssi, ssid);
                UdpSocketSend socketSend = new UdpSocketSend(serverIp, serverPort, mac_field, ssid, freq, rssi);

                ThreadStatus = true;
                Integer Sequence = 0;
                while(ThreadStatus){

                    try {
                        Sequence++;
                        Thread.sleep(Integer.parseInt(delay.getText().toString()));
                        socketSend.write(main, Sequence);
                        wifistatus.GetWifi_BSSID(main);
                        wifistatus.GetWifi_Link(main);
                        wifistatus.GetWifi_Freq(main);
                        wifistatus.GetWifi_RSSI(main,progress);
                        wifistatus.GetWifi_SSID(main);

                    }catch (Exception e) {
                        continue;
//                        e.printStackTrace();

                    }

                }
            }
        });
        t.start();


    }
}
