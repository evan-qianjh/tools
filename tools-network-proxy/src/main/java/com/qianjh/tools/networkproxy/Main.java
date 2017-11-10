package com.qianjh.tools.networkproxy;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    static final int listenPort = 9999;

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        ServerSocket serverSocket = new ServerSocket(listenPort);
        final ExecutorService tpe = Executors.newCachedThreadPool();
//        ScheduledExecutorService executor = new ScheduledExecutorService(1, new BasicThreadFactory.Builder().namingPattern("").daemon(true).build());

        System.out.println("Proxy Server Start At " + sdf.format(new Date()));
        System.out.println("listening port:" + listenPort + "……");
        System.out.println();
        System.out.println();

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                socket.setKeepAlive(true);
                //加入任务列表，等待处理
                tpe.execute(new ProxyTask(socket));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
