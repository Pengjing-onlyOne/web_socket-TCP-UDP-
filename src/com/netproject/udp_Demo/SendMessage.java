package com.netproject.udp_Demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Description:
 * @Version: V1.0
 */
public class SendMessage {
    public static void main(String[] args) throws IOException {
        //1.创建发送对象DatagramSocket
        //如果不指定端口号,就会随机弄出一个端口号来发送数据
        DatagramSocket ds = new DatagramSocket();
        //2.打包数据
        String str = "start study netPriject";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 1025;
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);
        //发送数据
        ds.send(dp);
        ds.close();
    }
}
