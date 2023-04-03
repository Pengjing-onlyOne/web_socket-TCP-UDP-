package com.netproject.udp_Demo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @Description:
 * @Version: V1.0
 */
public class ReceiveMessage {
    public static void main(String[] args) throws IOException {

        //1.创建DataGramSocket对象,在接收的时候一定绑定端口,一定要和发送的端口一致
        DatagramSocket ds = new DatagramSocket(1025);

        //接收数据包
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
        //该方法是一个阻塞方法
        ds.receive(dp);

        //3解析数据包
        //接收的数据
        byte[] data = dp.getData();
        //数据的长度
        int length = dp.getLength();
        //发送的ip地址
        InetAddress address = dp.getAddress();
        //发送的端口号
        int port = dp.getPort();
        System.out.println("接收的数据"+new String(data,0,length));
        System.out.println("该数据是从"+address+"这台电脑中的"+port+"发送的");
    }
}
