package com.tcp_work.tcp_upload_file.readlen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
//使用缓冲流对文件进行操作的时候需要再每次操作完之后添加一个刷新的操作,将缓冲区里面的数据输出,并清空缓冲区
public class Tcp_upload_file_server_useByReadlen {
    public static void main(String[] args) throws IOException {
        //创建服务端对象,并绑定端口号
        ServerSocket ss = new ServerSocket(1025);
        //设置输出的位置
        String path = "server_download/a.txt";
        //获取接受的数据
        Socket socket = ss.accept();
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));

        //使用bufferReader读取
        BufferedReader br = new BufferedReader(new InputStreamReader(bis));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(bos));

//        byte[] bytes = new byte[1024];
        String len;
        while((len=br.readLine())!= null){
            bw.write(len);
        }
//        bos.flush();

        BufferedWriter bw_out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw_out.write("文件上传完毕");
        bw_out.newLine();
        bw_out.flush();

        //释放资源
        bw.close();
        br.close();
        bos.close();
        socket.close();
        ss.close();

    }
}
