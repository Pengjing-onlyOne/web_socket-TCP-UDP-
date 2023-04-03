package com.netproject;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description: jdk自带的网络地址类
 * @Version: V1.0
 */
public class NetAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress address = InetAddress.getByName("192.168.1.6");
        System.out.println(address.getHostName());
        System.out.println(address.getAddress());
        System.out.println(address.getHostAddress());
        System.out.println(address.getCanonicalHostName());
    }
}
