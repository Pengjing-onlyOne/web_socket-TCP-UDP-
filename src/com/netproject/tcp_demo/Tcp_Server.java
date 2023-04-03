package com.netproject.tcp_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Tcp_Server {
    public static void main(String[] args) throws IOException {
        //创建服务端对象
        ServerSocket ss = new ServerSocket(1025);

        //获取客户端发送的资源
        Socket accept = ss.accept();

        /*InputStream is = accept.getInputStream();
        //字符转换
        InputStreamReader isr = new InputStreamReader(is);
        //增加输出效率
        BufferedReader br = new BufferedReader(isr);
        */
        BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
        int b;
        while ((b = br.read()) !=-1){
            System.out.print((char) b);
        }
        //释放资源
      br.close();
        ss.close();
    }
}
