package com.tcp_work.thread_pool;

import com.tcp_work.tcp_loop_plus_mult.MyThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Version: V1.0
 */
public class Thread_pool_server {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1L, TimeUnit.SECONDS, new SynchronousQueue<>());
        ServerSocket serverSocket = new ServerSocket(1025);
        while(true) {
            Socket socket = serverSocket.accept();
            threadPoolExecutor.execute(new MyThread(socket));
        }
    }
}
