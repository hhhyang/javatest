package com.javatest.javaserialization;

import java.io.Serializable;
import java.util.logging.Logger;

public class UserInfo implements Serializable {

    private final static Logger logger = Logger.getLogger("UserInfo");

    /**
     * userName : abc
     * userId : 48
     */

    private String userName;
    private int userId;

    public UserInfo() {
        logger.info("UserInfo construct");
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
}
