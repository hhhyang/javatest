
package com.javatest.trywith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TryMain {

    public static final Logger LOG = LoggerFactory.getLogger(TryMain.class);


    public static class TT implements AutoCloseable {

        public TT() {

        }

        @Override
        public void close() throws Exception {
            LOG.info("closed");
            throw new Exception("aaa");
        }
    }




    public static void main(String []args) {


        try (TryMain.TT t = new TryMain.TT()) {

            LOG.info("main close");
            // throw new Exception("eeee");

        } catch (Exception e) {

            // 1、try resource 和 try body抛出的异常都会被捕获
            // 2、即使 try body抛出异常， try resource的close方法也会被调用
            // 3、处理try body异常时，如果 try resource的close方法也抛出异常，异常信息在suppressed中

            LOG.info(e.getMessage());
            //LOG.info(e.getSuppressed()[0].getMessage());

        }


    }

}
