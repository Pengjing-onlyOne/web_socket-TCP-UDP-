package com.tcp_work.tcp_upload_file_rename;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class TcpServerFileRename {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket对象
        ServerSocket ss = new ServerSocket(1025);

        //获取客户端的socket对象,并解析数据
        Socket socket = ss.accept();
        //使用缓冲流加速数据的传输
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        //获取客户端IP地址
        String address = socket.getInetAddress().getHostAddress();
        //获取端口号
        int port = socket.getLocalPort();

        //将文件写入到服务器中
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("server_download/rename/"+System.currentTimeMillis()+".png"));
        byte[] bytes = new byte[1024];
        int len;
        while((len = bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }
        //刷新缓冲流
        bos.flush();

        //给客户端返回
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("文件上传完毕");
        bw.newLine();
        bw.flush();

        //关闭资源
        bos.close();
        socket.close();
        ss.close();
    }
}
