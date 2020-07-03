package com.javatest.nioclient;

import java.io.IOException;
import java.util.logging.Logger;

public class ClientMain {
    public static final Logger logger = Logger.getLogger("ClientMain");

    private static final String serverIp = "127.0.0.1";
    private static final int serverPort = 9201;

    public static void main(String[] args) {
        try {
            NioClient nioClient = new NioClient();
            nioClient.initClient(serverIp, serverPort);
            //nioClient.listen();

            for(;;) {

            }

        } catch (IOException e) {
            logger.warning(e.toString());
        }
    }
}
