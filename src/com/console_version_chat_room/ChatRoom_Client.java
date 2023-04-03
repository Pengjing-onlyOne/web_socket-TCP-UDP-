package com.console_version_chat_room;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Optional;
import java.util.Scanner;

/**
 * @Description:
 * @Version: V1.0
 */
public class ChatRoom_Client {
    public static void main(String[] args) throws IOException {

        //创建连接对象
        Socket socket = new Socket("127.0.0.1",1025);
        if(Optional.ofNullable(socket).isPresent()) {
            System.out.println("服务器已经连接成功");
            String upto_server = "";
            while (true) {
                //接收服务器的消息
                System.out.println("=================欢迎来到TCP聊天室===================");
                System.out.println("1登录");
                System.out.println("2注册");
                System.out.println("请输入您的选择:");
                Scanner scanner = new Scanner(System.in);
                String choose = scanner.nextLine();
                switch (choose) {
                    case "1":
                        upto_server = login(choose,socket);
                        break;
                    case "2":
                        upto_server = login(choose,socket);
                        break;
                    case "3":
                        return;
                    default:
                        System.out.println("没有该选项");
                }
                if("登录成功".equals(upto_server)){
                    break;
                }
            }

            while (true) {
                System.out.println("登陆成功，开始聊天");
                System.out.println("请输入你要说的话");
                Scanner scanner_message = new Scanner(System.in);
                BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream());
                String message_in = scanner_message.nextLine();
                bos.write(message_in.getBytes());
                bos.flush();
                if("886".equals(message_in)){
                    break;
                }
                //读取消息
                BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String message_out = br.readLine();
                System.out.println(message_out);
            }


        }
    }

    private static String login(String choose,Socket socket) throws IOException {
        Scanner scanner_name = new Scanner(System.in);
        System.out.println("请输入用户名:");
        String name = scanner_name.nextLine();
        System.out.println("请输入密码:");
//        Scanner scanner_password = new Scanner(System.in);
        String password = scanner_name.nextLine();
        String upto_server = choose+"&"+"username="+name+"passwor="+password;
        BufferedOutputStream bos = new BufferedOutputStream( socket.getOutputStream());
        bos.write(upto_server.getBytes());
        bos.flush();
        socket.shutdownOutput();

        //读取消息
        BufferedReader  br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = br.readLine();
        System.out.println(message);
        return  message;
    }
}
