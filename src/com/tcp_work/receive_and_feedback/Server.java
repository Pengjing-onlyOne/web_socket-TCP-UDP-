package com.tcp_work.receive_and_feedback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //创建服务端的ServerSocket
        ServerSocket ss = new ServerSocket(1025);
        //获取客户端对象
        /**
         * 服务端代码,对应的客户端打发送数据段代码
         */
        Socket accept = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        int b;
        //read方法会从连接通道读取数据
        //但是,需要一个结束标记,次数的循环才会停止
        //否则,程序就会一直停在read方法这里,等待读取下面的数据
        while((b=br.read())!=-1){
            System.out.print((char) b);
        }


        /**
         * 反馈端代码,从这里发送出去,在客户端的第二段代码上接收
         */
            OutputStream os = accept.getOutputStream();
        String s = "我很好";
            os.write(s.getBytes());
//            accept.shutdownOutput();



        accept.close();
        ss.close();
    }
}
