package com.example.getwifiinfo;

import android.content.Context;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSocketSend {
    DatagramSocket socket;
    InetAddress address;

    EditText serveripaddress;
    EditText portnumber;
    TextView BSSID;
    TextView SSID;
    TextView FREQ;
    TextView SIG;


    public UdpSocketSend(EditText serveripaddress, EditText portnumber, TextView BSSID, TextView SSID, TextView FREQ, TextView SIG) {
        this.serveripaddress = serveripaddress;
        this.portnumber = portnumber;
        this.BSSID = BSSID;
        this.SSID = SSID;
        this.FREQ = FREQ;
        this.SIG = SIG;
    }


    public void write(Context context, Integer SEQ) throws Exception{
        TextView new_bssid = BSSID.findViewById(R.id.Text_MAC);
        TextView new_ssid = SSID.findViewById(R.id.ssidfield);
        TextView new_freq = FREQ.findViewById(R.id.freq);
        TextView new_sig = SIG.findViewById(R.id.rssi);
        EditText new_serverip = serveripaddress.findViewById(R.id.serverIP);

        byte[] buf;
        JSONObject msg1 = new JSONObject();
        msg1.put("BSSID", new_bssid.getText().toString());
        msg1.put("SSID", new_ssid.getText().toString());
        msg1.put("frequency", new_freq.getText().toString());
        msg1.put("sequence", SEQ);
        msg1.put("Signal", Integer.parseInt(new_sig.getText().toString()));

        socket = new DatagramSocket();
        //address = InetAddress.getByName("177.96.132.3");
        address = InetAddress.getByName(serveripaddress.getText().toString());
        // send request
        buf = msg1.toString().getBytes("UTF8");
        DatagramPacket packet =
                new DatagramPacket(buf, buf.length, address, Integer.parseInt(portnumber.getText().toString()));
        socket.send(packet);

        }
    }

