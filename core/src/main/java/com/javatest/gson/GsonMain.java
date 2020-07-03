
package com.javatest.gson;

import com.google.gson.stream.JsonWriter;
import com.javatest.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.StringWriter;

public class GsonMain {
    private static final Logger LOG = LoggerFactory.getLogger(GsonMain.class);

    static void base() {
        Gson gson = new GsonBuilder().create();

        String personStr = "{\"exam\":[{\"subject\":\"shuxue\","
                + "\"score\":96.5056},{\"subject\":\"yuwen\",\"score\":90.5}], \"test\": 89}";

        LOG.info("{}", personStr);

        Person person = gson.fromJson(personStr, Person.class);

        // Person person1 = (Person) person.clone();

        LOG.info("person: {}", person);

        person.setData(personStr);

        //String personStrGson = gson.toJson(person);
        String personStrGson = person.appendJsonString(personStr);

        LOG.info("personStrGson: {}", personStrGson);
    }



    public static void main(String[] args) {

        StringWriter sw = new StringWriter();
        BufferedWriter bw = new BufferedWriter(sw);

        try {
            JsonWriter jwriter = new JsonWriter(bw);
            jwriter.beginObject()
                    .name("input")
                    .beginObject()
                        .name("host-name")
                        .value("www")
                        .name("peer-as")
                        .value(120)
                    .endObject()
                    .endObject()
                    .close();
        } catch (Exception e) {
            LOG.error(Utils.exceptionStackTrace(e));
        }

        LOG.info(sw.toString());


    }


}

