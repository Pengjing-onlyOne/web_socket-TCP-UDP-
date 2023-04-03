package com.netproject.udp_Demo.udp_broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @Description:
 * @Version: V1.0
 */
public class SendMessageBroadcast {
    public static void main(String[] args) throws IOException {

        //获取广播对象
        MulticastSocket ms = new MulticastSocket();

        //组装发送数据
        String str = "This is Broadcast";
        byte[] bytes = str.getBytes();
        InetAddress inetAddress = InetAddress.getByName("255.255.255.255");
        int port = 1025;
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,inetAddress,port);

        //发送数据
        ms.send(dp);

        //关闭资源
        ms.close();
    }
}
