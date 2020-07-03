package com.javatest.javaserialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.logging.Logger;

public class UserInfo2 implements Externalizable {

    private final static Logger logger = Logger.getLogger("UserInfo");

    /**
     * userName : abc
     * userId : 48
     */

    private String userName;
    private int userId;

    public UserInfo2() {
        logger.info("UserInfo2 construct");
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

        //out.writeObject(userName);

        logger.info("len : " + userName.length());

        //out.writeInt(userName.length());
        // out.writeBytes(userName);
        out.writeInt(userId);

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        // userName = (String) in.readObject();
        //int len = in.readInt();
        //logger.info("len read : " + len);
        //byte[] b = new byte[len];
        //in.read(b);
        //userName = new String(b);
        //logger.info("len read : " + userName + " " + userName.length()  );
        //userId = in.readInt();


    }
}
