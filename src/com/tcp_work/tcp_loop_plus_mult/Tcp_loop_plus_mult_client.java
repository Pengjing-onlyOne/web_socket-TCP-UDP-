package com.tcp_work.tcp_loop_plus_mult;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Tcp_loop_plus_mult_client {
    private static BufferedOutputStream bos;

    public static void main(String[] args) throws IOException {
        //创建socket对象
        Socket socket = new Socket("127.0.0.1",1025);

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        //读取文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("client_upload/a.txt"));
        byte[] bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }
        bos.flush();
        socket.shutdownOutput();

        //读取服务端回复的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println("客户端回复的消息是:"+s);
    }
}
