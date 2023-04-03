package com.tcp_work.tcp_upload_file;

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
//使用缓冲流对文件进行操作的时候需要再每次操作完之后添加一个刷新的操作,将缓冲区里面的数据输出,并清空缓冲区
public class Tcp_upload_file_server {
    public static void main(String[] args) throws IOException {
        //创建服务端对象,并绑定端口号
        ServerSocket ss = new ServerSocket(1025);
        //设置输出的位置
        String path = "server_download/img.png";
        //获取接受的数据
        Socket socket = ss.accept();
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));

        byte[] bytes = new byte[1024];
        int len ;
        while((len=bis.read(bytes))!= -1){
            bos.write(bytes,0,len);
        }
//        bos.flush();
        socket.shutdownOutput();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write("文件上传完毕");
        bw.newLine();
        bw.flush();

        //释放资源
        bos.close();
        socket.close();
        ss.close();

    }
}
