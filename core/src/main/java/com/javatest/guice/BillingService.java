package com.javatest.guice;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingService {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceMain.class);

    private final CreditCardProcessor processor;
    private final TransactionLog transactionLog;

    @Inject
    BillingService(CreditCardProcessor processor,
                   TransactionLog transactionLog) {
        this.processor = processor;
        this.transactionLog = transactionLog;
    }

    public void chargeOrder() {

        LOG.info("chargeOrder begin");

        transactionLog.log();

        processor.process();

        LOG.info("chargeOrder end");

    }



}
