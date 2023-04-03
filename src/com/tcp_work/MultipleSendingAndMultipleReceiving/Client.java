package com.tcp_work.MultipleSendingAndMultipleReceiving;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Description:
 * @Version: V1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建Socket对象
        Socket socket = new Socket("127.0.0.1",1025);

        //包装数据
        OutputStream os = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请输入你要发送的数据:");
            String s = scanner.nextLine();
            os.write(s.getBytes());
            if("886".equals(s)){
                break;
            }
        }
        socket.close();
    }
}
