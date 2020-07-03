package com.javatest;

import java.net.InetAddress;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ctc.wstx.util.ExceptionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class TestMain {

    // public static final Logger logger = Logger.getLogger(TestMain.class.getName());
    public static final Logger log = LoggerFactory.getLogger(TestMain.class);

    /** Float-like encoding for positive longs that preserves ordering and 4 significant bits. */
    public static int longToInt4(long i) {
        if (i < 0) {
            throw new IllegalArgumentException("Only supports positive values, got " + i);
        }
        int numBits = 64 - Long.numberOfLeadingZeros(i);
        if (numBits < 4) {
            // subnormal value
            return Math.toIntExact(i);
        } else {
            // normal value
            int shift = numBits - 4;
            // only keep the 5 most significant bits
            int encoded = Math.toIntExact(i >>> shift);
            // clear the most significant bit, which is implicit
            encoded &= 0x07;
            // encode the shift, adding 1 because 0 is reserved for subnormal values
            encoded |= (shift + 1) << 3;
            return encoded;
        }
    }

    public static String str(String format, Object... args) {


        String[] s = new String[args.length];
        for (int i = 0; i < args.length; i ++) {
            log.error("i: {}, value: {}", i, args[i]);
        }

        return  String.format(format, args);

    }

    public static long retrievePortNumFromtOid(String oid, int pos) {
        long portNum = -1;
        if (StringUtils.isEmpty(oid)) {
            return portNum;
        }
        try {
            String[] oidNums = oid.split("\\.");
            int p = pos >= 0 ? pos : oidNums.length + pos;
            portNum = Long.parseLong(oidNums[p]);
        } catch (Exception e) {
            portNum = -1;
            log.warn("retrieve port num failed from oid: {}, exception info: ");
        }
        return portNum;
    }


    public static void main(String[] args) {
        SpringApplication.run(TestMain.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return (args -> {
            System.setProperty("log4j.configuration",
                    "file:/Users/yangshengbing/Documents/idea_java/javatest/src/main/java/log4j.properties");


            try {
                ObjectMapper mapper = new ObjectMapper();
                EmbeddedResponse response = new EmbeddedResponse(new EmbeddedResponse.EmbeddedNetware<>(Lists.newArrayList("a", "b")));

                log.error("finish, {}, {}", mapper.writeValueAsString(response));

                response = new EmbeddedResponse(new EmbeddedResponse.EmbeddedInterface<>("hello"));
                log.error("finish, {}, {}", mapper.writeValueAsString(response));
                // System.in.read();

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Data
    @AllArgsConstructor
    public static class A implements Cloneable {

        public Integer i;

        @Override
        public A clone() throws CloneNotSupportedException {
            return (A) super.clone();
        }

    }

    @Data
    @AllArgsConstructor
    public static class B {
        public A a;
        public String s;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EmbeddedResponse {

        private Embedded embedded;


        public interface Embedded {

        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EmbeddedNetware<U> implements Embedded {
            private U netwares;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class EmbeddedInterface<U> implements Embedded {
            private U infs;
        }

    }



}
