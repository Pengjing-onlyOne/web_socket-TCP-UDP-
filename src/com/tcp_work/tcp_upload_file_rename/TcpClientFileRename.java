package com.tcp_work.tcp_upload_file_rename;

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
public class TcpClientFileRename {

    public static void main(String[] args) throws IOException {
        //创建socket对象
        Socket socket = new Socket("127.0.0.1",1025);

        //包装需要上传的数据
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("client_upload/img.png"));
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[]  bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) !=-1){
            bos.write(bytes,0,len);
        }
        bos.flush();
        socket.shutdownOutput();

        //获取服务端的返回信息
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = br.readLine();
        System.out.println("服务端反馈的消息是:"+s);

        bis.close();
        socket.close();

    }
}
