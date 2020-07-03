package com.javatest.guice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaypalCreditCardProcessor implements CreditCardProcessor {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceMain.class);

    public PaypalCreditCardProcessor() {
        LOG.info("construct PaypalCreditCardProcessor");
    }


    @Override
    public void process() {
        LOG.info("execute PaypalCreditCardProcessor.process()");
    }
}
