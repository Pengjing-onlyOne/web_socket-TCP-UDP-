package com.netproject.udp_Demo.udp_chat_room;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @Description:
 * @Version: V1.0
 */
public class ReceiveMessage_chatRoom {
    public static void main(String[] args) throws IOException {

        //创建对象,必须和发送的端口号一致
        DatagramSocket ds = new DatagramSocket(1025);

        //包装对象
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
        while (true) {
            ds.receive(dp);
            //解析对象
            byte[] data = dp.getData();
            int length = dp.getLength();
            String hostAddress = dp.getAddress().getHostAddress();
            String name  = dp.getAddress().getHostName();
            System.out.println("ip为"+hostAddress+",主机名为"+name+"发送了"+new String(bytes,0,length));
        }
    }
}
