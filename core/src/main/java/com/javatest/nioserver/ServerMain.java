package com.javatest.nioserver;

import java.io.IOException;
import java.util.logging.Logger;

public class ServerMain {

    public static final Logger logger = Logger.getLogger("ServerMain");

    public static void main(String[] args) {

        try {
            NioServer nioServer = new NioServer();
            nioServer.initServer();
            //nioServer.listen();

        } catch (IOException e) {
            logger.warning(e.toString());
        }


    }
}
