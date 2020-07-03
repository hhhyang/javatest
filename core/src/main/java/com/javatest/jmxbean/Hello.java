
package com.javatest.jmxbean;

import java.util.concurrent.atomic.AtomicLong;

public class Hello implements HelloMBean {


    private final AtomicLong counter = new AtomicLong(0L);
    private final long startTimeMillis = System.currentTimeMillis();

    @Override
    public long getFooCount() {
        return counter.get();
    }

    @Override
    public void setFooCount(long val) {
        counter.set(val);
    }

    @Override
    public long getUptimeMillis() {
        return System.currentTimeMillis() - startTimeMillis;
    }

    @Override
    public void printStuff(String stuff) {
        System.out.println(stuff);
    }


    public void run() throws InterruptedException {
        while (true) {
            counter.incrementAndGet();
            Thread.sleep(5000);
        }
    }


}

