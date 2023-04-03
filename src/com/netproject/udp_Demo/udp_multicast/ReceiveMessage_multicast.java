package com.netproject.udp_Demo.udp_multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @Description:
 * @Version: V1.0
 */
public class ReceiveMessage_multicast {
    public static void main(String[] args) throws IOException {
        //创建使用的组播工具
        MulticastSocket ms = new MulticastSocket(1025);

        //将本机天假到224.0.0.1的组中
        InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
        ms.joinGroup(inetAddress);

        //创建接收对象
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);

        //接收数据
        ms.receive(dp);

        //解析资源
        byte[] data = dp.getData();
        String hostAddress = dp.getAddress().getHostAddress();
        String name = dp.getAddress().getHostName();
        int length = dp.getLength();
        System.out.println("IP为"+hostAddress+",名字为"+name+"发来一条消息为:"+new String (bytes,0, bytes.length));
        //关闭资源
        ms.close();
    }
}
