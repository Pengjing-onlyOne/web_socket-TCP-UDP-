package com.tcp_work.receive_and_feedback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Client {
    public static void main(String[] args) throws IOException {
        //创建Socket对象
        Socket socket = new Socket("127.0.0.1",1025);

        //发送端代码,对应的是服务端的第一段代码
        OutputStream os = socket.getOutputStream();
        String s = "你好啊";
        os.write(s.getBytes());
        //添加一个结束标记,表示发送数据已经结束
        socket.shutdownOutput();


        //客户端代码,接收服务端的反馈代码
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        int b;
        while((b=br.read()) !=-1){
            System.out.print((char) b);
        }
    }
}
