package com.qianjh.tools.network.nat.impl;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.net.Socket;

/**
 * @author QianJH
 * @date 2017/11/11
 */
public class NatProcessor implements Runnable{

    private Socket socketIn;
    public NatProcessor(Socket socketIn) {
        this.socketIn = socketIn;
    }

    private Socket socketOut;

    /**
     * 已连接到请求的服务器
     */
    private static final String AUTHORED = "HTTP/1.1 200 Connection established\r\n\r\n";
    /**
     * 内部错误
     */
    private static final String SERVERERROR = "HTTP/1.1 500 Connection FAILED\r\n\r\n";

    @Override
    public void run() {

    }
}
