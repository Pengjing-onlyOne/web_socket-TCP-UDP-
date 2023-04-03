package com.netproject.udp_Demo.udp_chat_room;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @Description:
 * @Version: V1.0
 */
public class SendMessage_charRoom {
    public static void main(String[] args) throws IOException {
        //创建对象DataGramSocket
        DatagramSocket ds = new DatagramSocket();

        //2.打包数据
        while (true) {
            System.out.println("请输入你要说的话:");
            Scanner scanner = new Scanner(System.in);
            String snl = scanner.nextLine();
            byte[] snlBytes = snl.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 1025;
            DatagramPacket dp = new DatagramPacket(snlBytes,snlBytes.length,address,port);

            //发送数据
            ds.send(dp);
            if("886".equals(snl)){
                break;
            }
        }
        //释放资源
        ds.close();
    }
}
