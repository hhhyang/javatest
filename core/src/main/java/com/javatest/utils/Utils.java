
package com.javatest.utils;

import static java.util.Objects.nonNull;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Utils {

    Logger LOG = LoggerFactory.getLogger(Utils.class);

    static String exceptionStackTrace(final Throwable e) {

        StringWriter sw = new StringWriter();

        e.printStackTrace(new PrintWriter(sw));

        return sw.toString();

    }

    /**
     * 关闭 AutoCloseable 对象
     * @param resource AutoCloseable 对象
     * @param <T> AutoCloseable 对象类型参数
     */
    static <T extends AutoCloseable> void closeRresource(final T resource) {

        if (nonNull(resource)) {
            try {
                resource.close();
            } catch (Exception e) {
                LOG.error((exceptionStackTrace(e)));
            }
        }

    }


    static String calcuNetAddress(final String ipaddr, final int maskValue) {

        try {
            byte[] ipBytes = InetAddress.getByName(ipaddr).getAddress();

            int mask = (-1) << (32 - maskValue);

            int ipInt = (((int) ipBytes[0] & 0x000000FF) << 24)
                    | (((int) ipBytes[1] & 0x000000FF) << 16)
                    | (((int) ipBytes[2] & 0x000000FF) << 8)
                    | (((int) ipBytes[3] & 0x000000FF));

            int ipMask = ipInt & mask;

            byte[] after = new byte[4];
            after[0] = (byte) (ipMask >>> 24);
            after[1] = (byte) (ipMask >>> 16);
            after[2] = (byte) (ipMask >>> 8);
            after[3] = (byte) (ipMask);

            return InetAddress.getByAddress(after).getHostAddress();
        } catch (UnknownHostException e) {
            LOG.error(Utils.exceptionStackTrace(e));

            return "";
        }

    }

}

