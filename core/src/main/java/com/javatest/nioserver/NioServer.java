package com.javatest.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class NioServer {
    public static final Logger logger = Logger.getLogger("NioServer");
    public static final int syncPort = 9200;

    private Selector selector;

    public void initServer() throws IOException {

        logger.info("initServer");

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        ssc.configureBlocking(false);

        ServerSocket ss = ssc.socket();
        InetSocketAddress address = new InetSocketAddress(syncPort + 1);
        ss.bind(address);

        selector = Selector.open();

        SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {

        logger.info("listen");

        while (true) {
            int num = selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> itor = selectionKeys.iterator();

            while (itor.hasNext()) {

                SelectionKey key = itor.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();

                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                    logger.info("accept " + sc.toString());

                }

                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    read(sc);
                }

                itor.remove();

            }
        }

    }

    public void read(final SocketChannel sc) throws IOException {
        logger.info("begin read: ");

        ByteBuffer inBuffer = ByteBuffer.allocate(100);

        sc.read(inBuffer);

        logger.info(inBuffer.toString());

        inBuffer.flip();

        DataTrans dataTrans = new DataTrans();
        dataTrans.setC(inBuffer.getChar());
        dataTrans.setI(inBuffer.getInt());
        dataTrans.setL(inBuffer.getLong());
        dataTrans.setD(inBuffer.getDouble());

        logger.info(dataTrans.toString());

        ByteBuffer outBuffer = ByteBuffer.allocate(100);

        outBuffer.putChar(dataTrans.getC());
        outBuffer.putInt(dataTrans.getI());
        outBuffer.putLong(dataTrans.getL());
        outBuffer.putDouble(dataTrans.getD());

        logger.info(outBuffer.toString());

        outBuffer.flip();

        logger.info(outBuffer.toString());

        sc.write(outBuffer);
    }

}

