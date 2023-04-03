package com.tcp_work.tcp_loop_plus_mult;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */

//使用循环加多线程实现客户端,是客户端不会停止
public class Tcp_loop_plus_mult_server {

    public static void main(String[] args) throws IOException {
        //创建一般服务端流程
        ServerSocket ss = new ServerSocket(1025);

        while (true) {
            //获取创建客户端发送的对象
            Socket socket = ss.accept();

            //接收到对象就开启新的线程
            new Thread(new MyThread(socket)).start();
        }
    }
}
