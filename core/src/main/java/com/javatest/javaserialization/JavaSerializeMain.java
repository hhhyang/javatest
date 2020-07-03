
package com.javatest.javaserialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Logger;

public class JavaSerializeMain {

    private final static Logger logger = Logger.getLogger("JavaSerializeMain");

    public static void main(String[] args) {

        UserInfo userInfo = new UserInfo();

        userInfo.setUserName("John, Haney");
        userInfo.setUserId(80);

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(userInfo);

            byte[] result = bos.toByteArray();

            logger.info("origin length: " + ("John, Haney".length() + Integer.BYTES * 2));
            logger.info("result length: " + result.length);

            ByteArrayInputStream bis = new ByteArrayInputStream(result);
            ObjectInputStream ois = new ObjectInputStream(bis);

            UserInfo userInfo_1 = (UserInfo) ois.readObject();

            logger.info("userName: " + userInfo_1.getUserName());
            logger.info("userId: " + userInfo_1.getUserId());
        } catch (Exception e) {
            logger.warning(e.toString());
        }


        logger.info("======================");

        // ======================
        UserInfo2 userInfo2 = new UserInfo2();

        userInfo2.setUserName("John, Haney");
        userInfo2.setUserId(80);

        try {
            ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(bos2);

            oos2.writeObject(userInfo2);

            byte[] result2 = bos2.toByteArray();

            logger.info("origin length: " + ("John, Haney".length() + Integer.BYTES * 2));
            logger.info("result length: " + result2.length);

            ByteArrayInputStream bis2 = new ByteArrayInputStream(result2);
            ObjectInputStream ois2 = new ObjectInputStream(bis2);

            UserInfo2 userInfo_2 = (UserInfo2) ois2.readObject();

            logger.info("userName: " + userInfo_2.getUserName());
            logger.info("userId: " + userInfo_2.getUserId());
        } catch (Exception e) {
            logger.warning(e.toString());
        }





    }

}
