package com.javatest.framework.commons.utils;


import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;


public class Streams {

    public static <T> Stream<T> ofNullable(Collection<T> collection) {
        return Optional.ofNullable(collection).map(Collection::stream).orElse(Stream.empty());
    }
}
