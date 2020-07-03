
package com.javatest.jmxbean;

public interface HelloMBean {

    long getUptimeMillis();

    // 读写变量
    long getFooCount();
    void setFooCount(long val);

    // 调用方法
    void printStuff(String stuff);


}

