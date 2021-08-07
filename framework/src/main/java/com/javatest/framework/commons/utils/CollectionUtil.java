package com.javatest.framework.commons.utils;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {

    public static  <T> List<List<T>> splitList(List<T> origin, int size) {

        Assert.isTrue(size != 0, "size must not be zero");

        List<List<T>> result = new ArrayList<>();
        if (CollectionUtils.isEmpty(origin)) {
            return result;
        }
        List<T> slice = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < origin.size(); i++) {
            if (count >= size) {
                result.add(slice);
                slice = new ArrayList<>();
                count = 0;
            }
            slice.add(origin.get(i));
        }
        if (!slice.isEmpty()) {
            result.add(slice);
        }

        return result;
    }

}
