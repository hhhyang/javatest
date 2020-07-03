package com.javatest.nioclient;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class NioClient {
    public static final Logger logger = Logger.getLogger("NioClient");
    private Selector selector;

    public void initClient(final String serverIp, final int serverPort) throws IOException {

        logger.info("initClient");

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);


        selector = Selector.open();

        InetSocketAddress address = new InetSocketAddress(serverIp, serverPort);
        sc.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        sc.bind(new InetSocketAddress("172.18.188.117",4680));
        sc.connect(address);

        SocketChannel sc2 = SocketChannel.open();
        sc2.setOption(StandardSocketOptions.SO_REUSEADDR, true);
        sc2.bind(new InetSocketAddress( 4680));
        sc2.configureBlocking(true);

        // sc2.connect(address);

        sc.register(selector, SelectionKey.OP_CONNECT);

    }

    public void listen() throws IOException {

        logger.info("listen");

        while (true) {
            int num = selector.select();

            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> itor = keys.iterator();

            while (itor.hasNext()) {
                SelectionKey key = itor.next();

                logger.info(key.toString());

                if (key.isConnectable()) {
                    SocketChannel sc = (SocketChannel) key.channel();

                    if (sc.isConnectionPending()) {
                        sc.finishConnect();
                    }


                    DataTrans dataTrans = new DataTrans();
                    dataTrans.setC('a');
                    dataTrans.setI(100);
                    dataTrans.setL(300L);
                    dataTrans.setD(40.8D);

                    logger.info(dataTrans.toString());

                    ByteBuffer byteBuffer = ByteBuffer.allocate(100);

                    // byteBuffer.asCharBuffer().put(dataTrans.getC());
                    // byteBuffer.asIntBuffer().put(dataTrans.getI());
                    // byteBuffer.asLongBuffer().put(dataTrans.getL());
                    // byteBuffer.asDoubleBuffer().put(dataTrans.getD());

                    byteBuffer.putChar(dataTrans.getC());
                    byteBuffer.putInt(dataTrans.getI());
                    byteBuffer.putLong(dataTrans.getL());
                    byteBuffer.putDouble(dataTrans.getD());

                    logger.info(byteBuffer.toString());

                    byteBuffer.flip();

                    logger.info(byteBuffer.toString());

                    sc.write(byteBuffer);

                    sc.configureBlocking(false);

                    sc.register(selector, SelectionKey.OP_READ);

                    logger.info(byteBuffer.toString());

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

        logger.info("begin :");

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
    }

}
