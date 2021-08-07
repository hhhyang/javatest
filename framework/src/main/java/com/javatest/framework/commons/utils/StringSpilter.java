package com.javatest.framework.commons.utils;

import com.google.common.base.Splitter;

import java.util.List;


public class StringSpilter {

    public static List<String> splitToListAndTrim(final String toSplit, final String delimiter) {

        Splitter splitter = Splitter.on(delimiter).omitEmptyStrings().trimResults();
        return splitter.splitToList(toSplit);

    }

    public static String[] splitToArrayAndTrim(final String toSplit, final String delimiter) {

        List<String> stringList = splitToListAndTrim(toSplit, delimiter);
        return stringList.toArray(new String[stringList.size()]);
    }

}
