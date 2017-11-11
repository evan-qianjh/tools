package com.qianjh.tools.network.nat;


import java.io.IOException;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String... args) throws IOException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println("NAT Server Start At " + sdf.format(new Date()));
        //启动各个端口的监听
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            Socket socket = null;
            socket = server.accept();
            socket.setKeepAlive(true);
            InetAddress address = socket.getInetAddress();
            System.out.println("getCanonicalHostName: " + address.getCanonicalHostName());
            System.out.println("getHostAddress: " + address.getHostAddress());
            System.out.println("getHostName: " + address.getHostName());

            InetSocketAddress socketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.println("getPort: " + socketAddress.getPort());
        }
    }
}
