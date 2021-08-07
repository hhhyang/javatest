package com.javatest.framework.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;


public class InetAddressUtils {


    public static InetAddress fromInteger(final String intAddr) {

        try {
            Long longValue = Long.parseLong(intAddr);
            ByteBuffer buf = ByteBuffer.allocate(Integer.BYTES);
            buf.putInt(longValue.intValue());

            return InetAddress.getByAddress(buf.array());
        } catch (UnknownHostException e) {
            // noop
        }

        return null;

    }


    public static InetAddress calcNetAddr(final InetAddress addr, final int maskLen) {

        byte[] bytes = addr.getAddress();
        if (bytes.length == 4 && (maskLen < 0 || maskLen > 32)) {
            throw new IllegalArgumentException("maskLen must be within [0, 32], maskLen: " + maskLen);
        }

        if (bytes.length == 16 && (maskLen < 0 || maskLen > 128)) {
            throw new IllegalArgumentException("maskLen must be within [0, 128], maskLen: " + maskLen);
        }

        int zeroBits = (bytes.length << 3) - maskLen;
        int pos = bytes.length - 1;
        while (zeroBits > 0) {
            if (zeroBits >= 8) {
                bytes[pos] = 0;
                pos--;
                zeroBits -= 8;
            } else {
                bytes[pos] &= ~(1 << zeroBits - 1);
                zeroBits = 0;
            }
        }

        InetAddress r = null;
        try {
            r = InetAddress.getByAddress(bytes);
        } catch (UnknownHostException e) {
            // no op
        }

        return r;
    }

    public static String calcNetAddr(final String addr, final int maskLen) throws UnknownHostException {

        InetAddress inetAddr = InetAddress.getByName(addr);
        return calcNetAddr(inetAddr, maskLen).getHostAddress();

    }

    public static int prefix(final InetAddress addr1, final InetAddress addr2) {

        if (addr1 == null || addr2 == null) {
            throw new NullPointerException();
        }

        byte[] bytes1 = addr1.getAddress();
        byte[] bytes2 = addr2.getAddress();

        if (bytes1.length != bytes2.length) {
            throw new IllegalArgumentException("two address is not for the same protocol");
        }

        int bits = 0;
        for (int i = 0; i < bytes1.length; i++) {
            if (bytes1[i] == bytes2[i]) {
                bits += 8;
            } else if ((bytes1[i] & 0xF0) == (bytes2[i] & 0xF0)) {
                bits += 4;
                bits += halfBytePrefix(bytes1[i], bytes2[i]);
                break;
            } else {
                bits += halfBytePrefix((byte) (bytes1[i] >> 4), (byte) (bytes2[i] >> 4));
                break;
            }
        }

        return bits;
    }

    /**
     * 判断两个 IpAddress 是否有相同的前缀
     * @param ip1 ip1
     * @param ip2 ip2
     * @return
     */
    public static boolean sameSubnet(final InetAddress ip1, final InetAddress ip2, int maskLen) {

        byte[] bytes1 = ip1.getAddress();
        byte[] bytes2 = ip2.getAddress();

        if (bytes1.length != bytes2.length) {
            return false;
        }

        int i = 0;
        while (maskLen > 0) {
            if (maskLen >= 8) {
                if (bytes1[i] != bytes2[i]) {
                    return false;
                } else {
                    i++;
                    maskLen = maskLen - 8;
                    continue;
                }
            } else {
                int mask = ~(1 << maskLen - 1) & 0x0FF;
                return (bytes1[i] & mask) == (bytes2[i] & mask);
            }
        }

        return true;


    }

    public static int maskLen(final String maskAddr) throws UnknownHostException {

        InetAddress inetAddr = InetAddress.getByName(maskAddr);
        byte[] bytes = inetAddr.getAddress();

        int len = 0;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == -1) {
                len += 8;
            } else {
                byte v = (byte) (bytes[i] >>> 4);
                if (v == 0x0F) {
                    len += 4;
                    len += halfByteLeadingOnes(bytes[i] & 0x0F);
                } else {
                    len += halfByteLeadingOnes(v & 0x0F);
                }
                break;
            }
        }

        return len;
    }

    private static int halfBytePrefix(final byte b1, final byte b2) {

        int tmp = (~(b1 ^ b2)) & 0x0F;
        return halfByteLeadingOnes(tmp);

    }

    private static int halfByteLeadingOnes(final int b) {
        switch (b & 0x0F) {

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

}
