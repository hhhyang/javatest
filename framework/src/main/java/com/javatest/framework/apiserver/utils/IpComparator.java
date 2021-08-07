package com.javatest.framework.apiserver.utils;

import com.javatest.framework.commons.domain.vo.ApiException;
import com.javatest.framework.commons.domain.vo.ApiStatusCode;
import org.apache.commons.lang3.reflect.FieldUtils;
import sun.net.util.IPAddressUtil;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;

public class IpComparator<T> implements Comparator<T> {

    private final String fieldName;

    public IpComparator(final String fieldName) {
        Objects.requireNonNull(fieldName, "field name must not be null!");
        this.fieldName = fieldName;
    }


    @Override
    public int compare(T o1, T o2) {
        try {

            Object v1;
            Object v2;
            if (Map.class.isAssignableFrom(o1.getClass())) {
                v1 = ((Map) o1).get(fieldName);
                v2 = ((Map) o2).get(fieldName);
            } else {
                v1 = FieldUtils.readField(o1, fieldName, true);
                v2 = FieldUtils.readField(o2, fieldName, true);
            }

            if (v1 == null && v2 == null) {
                return 0;
            } else if (v1 == null) {
                return -1;
            } else if (v2 == null) {
                return 1;
            } else {

                if (String.class.isAssignableFrom(v1.getClass()) && String.class.isAssignableFrom(v2.getClass())) {

                    String str1 = (String) v1;
                    String str2 = (String) v2;

                    boolean v1IsV4 = IPAddressUtil.isIPv4LiteralAddress(str1);
                    boolean v2IsV4 = IPAddressUtil.isIPv4LiteralAddress(str2);
                    boolean v1IsV6 = IPAddressUtil.isIPv6LiteralAddress(str1);
                    boolean v2IsV6 = IPAddressUtil.isIPv6LiteralAddress(str2);

                    if ((v1IsV4 || v1IsV6) && (v2IsV4 || v2IsV6)) {
                        return compareInetAddress(v1, v2);
                    }
                }

                // other type
                if (v1.getClass() == String.class) {
                    return ((String) v1).compareTo((String) v2);
                } else if (v1.getClass() == Long.class) {
                    return ((Long) v1).compareTo((Long) v2);
                } else if (v1.getClass() == Integer.class) {
                    return ((Integer) v1).compareTo((Integer) v2);
                } else if (v1.getClass() == Double.class) {
                    return ((Double) v1).compareTo((Double) v2);
                } else if (v1.getClass() == Float.class) {
                    return ((Float) v1).compareTo((Float) v2);
                } else if (v1.getClass() == Short.class) {
                    return ((Short) v1).compareTo((Short) v2);
                } else if (v1.getClass() == Byte.class) {
                    return ((Byte) v1).compareTo((Byte) v2);
                } else if (v1.getClass() == Character.class) {
                    return ((Character) v1).compareTo((Character) v2);
                } else {
                    return 0;
                }
            }

        } catch (IllegalAccessException e) {
            String msg = String.format("this domain entity field can not accessed, field name: %s", fieldName);
            throw new ApiException(ApiStatusCode.INVALID_ACCESS_KEY_ID, msg);
        }
    }


    private int compareInetAddress(Object v1, Object v2) {

        String str1 = (String) v1;
        String str2 = (String) v2;

        boolean v1IsV4 = IPAddressUtil.isIPv4LiteralAddress(str1);
        boolean v2IsV4 = IPAddressUtil.isIPv4LiteralAddress(str2);
        boolean v1IsV6 = IPAddressUtil.isIPv6LiteralAddress(str1);
        boolean v2IsV6 = IPAddressUtil.isIPv6LiteralAddress(str2);

        if (v1IsV4 && v2IsV4) {
            byte[] ip1 = IPAddressUtil.textToNumericFormatV4(str1);
            byte[] ip2 = IPAddressUtil.textToNumericFormatV4(str2);
            return compareByteArray(ip1, ip2);
        } else if (v1IsV6 && v2IsV6) {
            byte[] ip1 = IPAddressUtil.textToNumericFormatV6(str1);
            byte[] ip2 = IPAddressUtil.textToNumericFormatV6(str2);
            return compareByteArray(ip1, ip2);
        } else if (v1IsV4 && v2IsV6) {
            return -1;
        } else {
            return 1;
        }


    }

    private int compareByteArray(byte[] a1, byte[] a2) {

        for (int i = 0; i < a1.length; i++) {
            int ia1 = ((int) a1[i]) & 0x0FF;
            int ia2 = ((int) a2[i]) & 0x0FF;

            if (ia1 < ia2) {
                return -1;
            } else if (ia1 > ia2) {
                return 1;
            }
        }

        return 0;

    }


}

