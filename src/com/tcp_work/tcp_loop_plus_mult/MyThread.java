package com.tcp_work.tcp_loop_plus_mult;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * @Description:
 * @Version: V1.0
 */
public class MyThread implements Runnable{
    Socket socket;

    public MyThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //获取接收的数据,并解析
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("server_download/tcp_loop_plus_mult/"+ LocalDateTime.now().format( DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+".txt"));
            //读取数据并写入到服务器中
            byte[] bytes= new byte[1024];
            int len;
            while((len = bis.read(bytes)) != -1){
                bos.write(bytes,0,len);
            }
            bos.flush();

            //反馈给客户端消息
            BufferedWriter br = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            br.write("文件上传完毕");
            br.newLine();
            br.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if(Optional.ofNullable(socket).isPresent()){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
