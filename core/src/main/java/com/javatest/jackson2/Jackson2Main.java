
package com.javatest.jackson2;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2Main {

    private static final Logger LOG = LoggerFactory.getLogger(Jackson2Main.class);

    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper();

            // 反序列化
            String personStr = "{\"name\":\"yangshengbing\",\"age\":30,\"male\":true,\"exam\":[{\"subject\":\"shuxue\","
                    + "\"score\":96.5},{\"subject\":\"yuwen\",\"score\":90.5}]}";

            Person person = mapper.readValue(personStr, Person.class);

            LOG.info("person: {}", person);

            // 序列化
            String personStrGson = mapper.writeValueAsString(person);
            LOG.info("personStrGson: {}", personStrGson);
        } catch (IOException e) {
            LOG.error("error: {}", e.toString());
        }


    }
}

