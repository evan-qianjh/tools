package com.qianjh.tools.network.nat.impl;

import lombok.AllArgsConstructor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * @author QianJH
 * @date 2017/11/11
 */
@AllArgsConstructor
public class PortListener extends Thread {
    private Integer port;

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(
                1, new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d")
                .daemon(true).build());

//        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                socket.setKeepAlive(true);
                executorService.execute(new NatProcessor(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
//        }
    }
}
