package com.javatest.framework.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;


public class GzipUtil {
    /**
     * Gzip data decompress
     *
     * @param compressData compress data by gzip
     * @throws Exception
     */
    public static String decompress(byte[] compressData) throws Exception {

        InputStream inStream = new GZIPInputStream(new ByteArrayInputStream(compressData));
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int len;
        while ((len = inStream.read(buffer)) > 0) {
            bao.write(buffer, 0, len);
        }
        bao.close();
        inStream.close();
        return bao.toString();
    }
}
