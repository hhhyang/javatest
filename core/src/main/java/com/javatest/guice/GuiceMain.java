package com.javatest.guice;


import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuiceMain {

    private static final Logger LOG = LoggerFactory.getLogger(GuiceMain.class);


    public static void main(String []args) {

        System.setProperty("log4j.configuration",
                "file:/Users/yangshengbing/Documents/idea_java/javatest/src/main/java/log4j.properties");

        try {

            LOG.info("entry");
            /*
             * Guice.createInjector() takes your Modules, and returns a new Injector
             * instance. Most applications will call this method exactly once, in their
             * main() method.
             */
            Injector injector = Guice.createInjector(new BillingModule());


            LOG.info("getInstance");
            /*
             * Now that we've got the injector, we can build objects.
             */
            //BillingService billingService = injector.getInstance(BillingService.class);

            //TransactionLog log = injector.getInstance(DatabaseTransactionLog.class);
            //log.log();

            //billingService.chargeOrder();


            LOG.info("exit");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


