package com.netproject.tcp_demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class Tcp_client {
    public static void main(String[] args) throws IOException {

        //创建一个Socket对象,前提服务端必须能够连接的上,不然会报错
        Socket socket = new Socket("127.0.0.1",1025);

        //包装需要发送的数据
        OutputStream os = socket.getOutputStream();

        os.write("你好世界".getBytes());

        //释放资源
        os.close();
        socket.close();
    }
}
