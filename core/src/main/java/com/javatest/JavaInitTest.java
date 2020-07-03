package com.javatest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * 验证类初始和实例初始化顺序
 */
public class JavaInitTest {

    public static final Logger LOG = LoggerFactory.getLogger(TestMain.class);

    private static int staticInt = 40;
    private static Test2 staticTest2 = new Test2("staticTest2");
    private static final int staticFinalInt = 40;
    private static final int staticFinalInt2;
    private static final Test2 staticFinalTest2 = new Test2("staticFinalTest2");

    public static final int staticFinalTest3 = 0;

    static {
        System.out.println("========start static block 1========");

        System.out.println("pre staticInt = " + staticInt);
        System.out.println("pre staticFinalInt = " + staticFinalInt);
        staticInt = 120;

        staticFinalInt2 = 120;

        // 不能再赋值
        // staticFinalTest3 = 150;

        // 编译报错
        // staticFinalInt = 40;
        System.out.println("pre staticTest2 = " + staticTest2);

        //System.out.println("postStaticInt = " + postStaticInt);

        postStaticInt = 120;
        postStaticInt2 = 240;
        postStaticTest2 = new Test2("modified postStaticTest2");

        System.out.println("========end static block 1========");

    }

    private static int postStaticInt = 40;
    private static int postStaticInt2;
    private static Test2 postStaticTest2 = new Test2("postStaticTest2");

    private final int finalInt = 40;
    private final int finalInt2;
    private int generalInt = 40;
    private Test2 generalTest2 = new Test2("generalTest2");

    static {
        System.out.println("========start static block 2========");

        System.out.println("staticFinalInt2 = " + staticFinalInt2);

        System.out.println("staticInt = " + staticInt);

        System.out.println("postStaticInt = " + postStaticInt);

        System.out.println("postStaticInt2 = " + postStaticInt2);

        System.out.println("postStaticTest2 = " + postStaticTest2);

        System.out.println("========end static block 2========");
    }


    public JavaInitTest() {
        System.out.println("pre finalInt = " + finalInt);
        System.out.println("pre generalInt = " + generalInt);
        generalInt = 120;

        System.out.println("pre generalTest2 = " + generalTest2);

        // 不能再赋值
        // staticFinalTest3 = 150;

        // finalInt = 150;
        finalInt2 = 150;
        System.out.println("pre finalInt2 = " + finalInt2);
    }

    public static int getStaticInt() {
        System.out.println("static post staticInt = " + staticInt);
        return staticInt;
    }

    public int getGeneralInt() {
        System.out.println("post generalInt = " + generalInt);
        return generalInt;
    }


    public static class Test2 {

        public Test2(String a) {
            System.out.println("init Test2. a=" + a);
        }
    }



    public static void main(String []args) {


        LocalDateTime t = LocalDateTime.now();
        Long t1 = System.currentTimeMillis() / 1000;
        Long t2 = t.toEpochSecond(ZoneOffset.of("+8"));

        // LOG.info(t.format(DateTimeFormatter.ISO_DATE_TIME));

        LOG.info(t.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));


        LOG.info("=====");


        // getStaticInt();

        // JavaInitTest javaInitTest = new JavaInitTest();

        // javaInitTest.getGeneralInt();

    }


}
