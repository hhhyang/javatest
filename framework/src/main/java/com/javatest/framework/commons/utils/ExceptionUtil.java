package com.javatest.framework.commons.utils;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {

    public static String strExceptionStack(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
