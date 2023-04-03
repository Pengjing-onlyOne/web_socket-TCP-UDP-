package com.console_version_chat_room;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Version: V1.0
 */
public class ChatRoom_Server {

    private static  final  String NAME_REGEX = "[a-zA-Z]{6,18}";
    private static final  String PASSWORD_REGEX="^[a-zA-Z][0-9]{2,7}";
    //做一个控制台版的聊天室
    //使用多线程处理任务,来一个socket开启一个线程
    public static void main(String[] args) {
        try {
    FileOutputStream fos = new FileOutputStream("default_user/defaultUser.txt");
    Properties properties = new Properties();
    properties.store(fos,"默认用户");
    //创建线程池
            //执行任务
            //创建ServerSocket对象
            ServerSocket ss = new ServerSocket(1025);
            Socket socket = ss.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String out = br.readLine();
            String longinOr_regest_message ="";
            String thread_name="";
            if(out.contains("username")){
                String[] split = out.split("&");
                String choose = split[0];
                String[] split_name = split[1].split("=");
                String name = split_name[1];
                String[] split_password = split[2].split("=");
                String password = split_password[1];
                if("1".equals(choose)){
                    //这是登录操作
                    if(properties.containsKey(name)){
                        //用户名存在,校验密码
                        if(properties.getProperty(name).equals(password)){
                            longinOr_regest_message = "登陆成功，开始聊天";
                            thread_name = name;
                        }else {
                            longinOr_regest_message = "输入密码错误";
                        }
                    }else {
                        longinOr_regest_message="用户名不存在";
                    }
                }else if("2".equals(choose)){
                    //注册操作
                    //将数据提取出来
                    //校验用户名是否合法
                    if(!name.matches(NAME_REGEX)){
                        longinOr_regest_message = "用户名不合法";
                    }
                    if(properties.containsKey(name)){
                        longinOr_regest_message="该用户已存在";
                    }
                    //校验密码是否合法
                    if(!password.matches(PASSWORD_REGEX)){
                        longinOr_regest_message = "密码不合法";
                    }
                    longinOr_regest_message="完成注册";
                    properties.put(name,password);
                    //将注册信息写到本地
                    properties.store(new FileOutputStream("default_user/defaultUser.txt"),"defaultUser.txt");
                }
                bw.write(longinOr_regest_message);
                bw.newLine();
                bw.flush();
            }

            if("登陆成功，开始聊天".equals(longinOr_regest_message)) {
                String finalThread_name = thread_name;
                ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 20, 10, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, finalThread_name);
                    }
                }, new ThreadPoolExecutor.DiscardOldestPolicy());
                //接收总的消息
                while (true) {
                    threadPoolExecutor.submit(new MyRunnable(socket, out));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("发生异常:"+e.getMessage());
        }
    }

}
