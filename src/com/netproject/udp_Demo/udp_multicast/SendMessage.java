package com.netproject.udp_Demo.udp_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @Description:
 * @Version: V1.0
 */
public class SendMessage {
    public static void main(String[] args) throws IOException {
        //组播使用的对象
        MulticastSocket ms = new MulticastSocket();

        //包装需要的对象
        String str = "This is multicast";
        byte[] bytes = str.getBytes();
        InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
        int port = 1025;
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,inetAddress,port);

        //发送消息
        ms.send(dp);

        //关闭资源
        ms.close();
    }
}
