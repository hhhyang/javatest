package com.javatest.ipv4;


public class Ipv4AddressUtils {

    public static int toInt(final String ipv4) {
        if (ipv4 == null) {
            throw new NullPointerException();
        }
        String[] segments = ipv4.split("\\.");
        if (segments.length != 4) {
            throw new IllegalArgumentException();
        }
        int val1 = Integer.parseInt(segments[0]) & 0x0FF;
        int val2 = Integer.parseInt(segments[1]) & 0x0FF;
        int val3 = Integer.parseInt(segments[2]) & 0x0FF;
        int val4 = Integer.parseInt(segments[3]) & 0x0FF;
        int result = (val1 << 24) | (val2 << 16) | (val3 << 8) | val4;
        return result;
    }


    public static int prefix(final int ip1, final int ip2) {

        return prefixInternal(ip1, ip2, 32);

    }

    public static boolean sameSubnet(final int ip1, final int ip2, final int mask) {
        return (ip1 & mask) == (ip2 & mask);
    }


    private static int halfBytePrefix(final int b1, final int b2) {

        int tmp = (~(b1 ^ b2)) & 0x0000_000F;
        switch (tmp) {

            case 0B1000 :
            case 0B1001 :
            case 0B1010 :
            case 0B1011 : {
                return 1;
            }
            case 0B1100 :
            case 0B1101 : {
                return 2;
            }
            case 0B1110 : {
                return 3;
            }
            case 0B1111 : {
                return 4;
            }

            default : {
                return 0;
            }
        }

    }

    private static int prefixInternal(final int int1, final int int2, final int len) {

        if (len <= 4) {
            return halfBytePrefix(int1, int2);
        }

        int halfLen = len / 2;

        int mask = (int) ((0x1L << len) - 1);
        int lowMask = (int) ((0x1L << halfLen) - 1);

        int highMask = mask & (~lowMask);
        if ((int1 & highMask) == (int2 & highMask)) {
            return halfLen + prefixInternal(int1 & lowMask, int2 & lowMask, halfLen);
        } else {
            return prefixInternal(int1 >> halfLen, int2 >> halfLen, halfLen);
        }

    }

}


