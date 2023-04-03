package com.tcp_work.MultipleSendingAndMultipleReceiving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Server {
    public static void main(String[] args) throws IOException {
        //创建服务端对象
        ServerSocket ss = new ServerSocket(1025);

        //创建接收对象
        Socket accept = ss.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            int b;
            while((b=br.read()) != -1){
                System.out.print((char) b);
            }
            
        //释放资源
        ss.close();
    }
}
