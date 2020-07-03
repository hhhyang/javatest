
package com.javatest.utils;

public interface Strings {

    static Boolean nonNullAndEmpty(final String s) {
        if (s != null && !s.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
