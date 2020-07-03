package com.javatest.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseTransactionLog implements TransactionLog {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceMain.class);

    public DatabaseTransactionLog() {
        LOG.info("construct DatabaseTransactionLog");
    }


    @Override
    public void log() {
        LOG.info("execute DatabaseTransactionLog.log()");
    }
}
