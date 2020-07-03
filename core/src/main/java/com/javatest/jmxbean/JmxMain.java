
package com.javatest.jmxbean;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public class JmxMain {

    public static void main(String[] args) {

        Hello hello = new Hello();
        try {
            MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

            mBeanServer.registerMBean(hello, new ObjectName("myapp:service=MyServer"));

            hello.run();
        } catch (Exception e) {
            //
        }
    }

}

