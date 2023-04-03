package com.tcp_work.thread_pool;

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
public class Thread_pool_client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",1025);

        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("client_upload/a.txt"));
        byte[] bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }
        bos.flush();
        socket.shutdownOutput();

        //获取客户端反馈
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println("服务端返回的消息是:"+s);
    }
}
