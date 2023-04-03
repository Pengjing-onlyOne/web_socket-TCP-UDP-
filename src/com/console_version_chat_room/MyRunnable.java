package com.console_version_chat_room;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @Description:
 * @Version: V1.0
 */
public class MyRunnable implements Runnable{
    Socket socket;
    String message;

    public MyRunnable(Socket socket,String message) {
        this.socket = socket;
    }

    private static  final  String NAME_REGEX = "[a-zA-Z]{6,18}";
    private static final  String PASSWORD_REGEX="^[a-zA-Z][0-9]{2,7}";

    @Override
    public void run() {
        //执行需要执行的任务
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
