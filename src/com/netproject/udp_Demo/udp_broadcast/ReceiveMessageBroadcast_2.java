package com.netproject.udp_Demo.udp_broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**
 * @Description:
 * @Version: V1.0
 */
public class ReceiveMessageBroadcast_2 {
    public static void main(String[] args) throws IOException {
        //获取广播对象
        MulticastSocket ms = new MulticastSocket(1025);

        //组装广播对象
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);

        ms.receive(dp);

        //解析资源
        byte[] data = dp.getData();
        String hostAddress = dp.getAddress().getHostAddress();
        String name = dp.getAddress().getHostName();
        int length = dp.getLength();
        System.out.println("IP为"+hostAddress+"的名字为"+name+"发送了一条消息:"+new String(bytes,0,length));

        //关闭资源
        ms.close();
    }
}
