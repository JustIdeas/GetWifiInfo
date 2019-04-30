package com.example.getwifiinfo;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import android.os.SystemClock;

public class UdpSocketSend {
    DatagramSocket socket;
    InetAddress address;

    String serveripaddress;
    String portnumber;
    String BSSID;
    String SSID;
    String TNAME;
    Integer FREQ;
    Integer SIG;
    Long TIME;


    public UdpSocketSend(String serveripaddress, String portnumber, String BSSID, String SSID, Integer FREQ, Integer SIG, Long TIME, String TNAME) {
        this.serveripaddress = serveripaddress;
        this.portnumber = portnumber;
        this.BSSID = BSSID;
        this.SSID = SSID;
        this.FREQ = FREQ;
        this.SIG = SIG;
        this.TIME = TIME;
        this.TNAME = TNAME;
    }


    public void write(Context context, Integer SEQ, String BSSID, String SSID, Integer FREQ, Integer SIG, Long TIME, String TNAME) throws Exception{
        byte[] buf;
        JSONObject msg1 = new JSONObject();
        msg1.put("BSSID", BSSID);
        msg1.put("SSID", SSID);
        msg1.put("frequency", FREQ);
        msg1.put("Signal", SIG);
        msg1.put("Testname", TNAME);


        socket = new DatagramSocket();
        address = InetAddress.getByName(serveripaddress);
        // send request
        buf = msg1.toString().getBytes("UTF8");
        DatagramPacket packet =
                new DatagramPacket(buf, buf.length, address, Integer.parseInt(portnumber));
        socket.send(packet);
        socket.close();

        }

    public void write2(JSONObject info) throws Exception{
        byte[] buf;

        buf = info.toString().getBytes("UTF8");
        socket = new DatagramSocket();
        address = InetAddress.getByName(serveripaddress);
        // send request

        DatagramPacket packet =
                new DatagramPacket(buf, buf.length, address, Integer.parseInt(portnumber));
        socket.send(packet);
        socket.close();


    }
    }

