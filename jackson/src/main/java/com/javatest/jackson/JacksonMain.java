package com.javatest.jackson;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JacksonMain {

    public static void main(String[] args) {
        try {
            ObjectMapper json = new ObjectMapper();

            String jsonLong = json.writeValueAsString(2000);
            log.info("jsonLong: {}", jsonLong);

            Long longValue = json.readValue("100", Long.class);
            log.info("longValue: {}", longValue);

            String jsonStr = json.writeValueAsString("hello");
            log.info("jsonStr: {}", jsonStr);

            String strValue = json.readValue("\"hello world\"", String.class);
            log.info("strValue: {}", strValue);
        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }

    }
}
