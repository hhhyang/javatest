package com.javatest.framework.commons.utils;

import java.util.Comparator;


public class IPAddressComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        if (o1 == o2) {
            return 0;
        } else if (o1 == null) {
            return -1;
        } else if (o2 == null) {
            return 1;
        }

        boolean v1IsV4 = IPAddressUtil.isIPv4LiteralAddress(o1);
        boolean v2IsV4 = IPAddressUtil.isIPv4LiteralAddress(o2);
        boolean v1IsV6 = IPAddressUtil.isIPv6LiteralAddress(o1);
        boolean v2IsV6 = IPAddressUtil.isIPv6LiteralAddress(o2);

        if (v1IsV4 && v2IsV4) {
            byte[] ip1 = IPAddressUtil.textToNumericFormatV4(o1);
            byte[] ip2 = IPAddressUtil.textToNumericFormatV4(o2);
            return compareByteArray(ip1, ip2);
        } else if (v1IsV6 && v2IsV6) {
            byte[] ip1 = IPAddressUtil.textToNumericFormatV6(o1);
            byte[] ip2 = IPAddressUtil.textToNumericFormatV6(o2);
            return compareByteArray(ip1, ip2);
        } else if (v1IsV4 && v2IsV6) {
            return -1;
        } else if (v1IsV6 && v2IsV4) {
            return 1;
        } else {
            return o1.compareTo(o2);
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
