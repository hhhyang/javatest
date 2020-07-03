package com.javatest.configadmin;


public interface ConfigManagerService {

    void register(final String path, final Class<?> target);

    void register(final String path, final Object target);
}
