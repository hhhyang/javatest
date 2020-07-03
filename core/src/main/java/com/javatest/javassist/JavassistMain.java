package com.javatest.javassist;

import javassist.ClassPool;
import javassist.CtClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class JavassistMain {

    public static final Logger LOG = LoggerFactory.getLogger(JavassistMain.class);


    public static void main(String []args) {

        ClassPool pool = ClassPool.getDefault();

        try {

            CtClass cc = pool.get("com.javatest.javassist.Rectangle");

            cc.setSuperclass(pool.get("com.javatest.javassist.Point"));

            // 将修改后的字节码写入文件
            // cc.writeFile();
            cc.writeFile("/Users/yangshengbing/Documents/idea_java/example/javatest/target/classes");

            // 将修改后的字节码写入文件，这个文件的内容与上面文件的内容应该一样
            byte[] b = cc.toBytecode();
            OutputStream o = new FileOutputStream("/Users/yangshengbing/Documents/idea_java/example/javatest/target/classes/com/javatest/javassist/test.class");
            o.write(b);
            o.close();

            // 加载修改后的类，并获取它的父类
            Class clazz = cc.toClass();
            LOG.info("{}", clazz.getSuperclass().getName());


            CtClass newCtClass = pool.makeClass("com.javatest.javassist.Circle");

            newCtClass.writeFile("/Users/yangshengbing/Documents/idea_java/example/javatest/target/classes");





        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
