package com.tcp_work.tcp_upload_file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
//明天换一种方式使用readlen的方式读和写
public class Tcp_upload_file_Client {
    public static void main(String[] args) throws IOException {
        //创建Socket对象
        Socket socket = new Socket("127.0.0.1",1025);

        //读取本地文件
//        FileInputStream fis = new FileInputStream("client_upload/a.txt");
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File("client_upload/img.png")));

        //创建输出流
        BufferedOutputStream bos =new BufferedOutputStream(socket.getOutputStream());


       byte[] bytes = new byte[1024];
       int len;
        while((len=bis.read(bytes)) != -1){
            bos.write(bytes,0,len);
        }

        //结束标记
        bos.flush();
        socket.shutdownOutput();


        //接收服务器的会写数据
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        System.out.println(br.readLine());


        //释放资源
        socket.close();
    }
}
