package com.javatest.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class JacksonTest {

    @Test
    public void testBaseTypes() {

        try {
            ObjectMapper json = new ObjectMapper();

            String jsonLong = json.writeValueAsString(2000);
            log.info("jsonLong: {}", jsonLong);

            Long longValue = json.readValue("100", Long.class);
            log.info("longValue: {}", longValue);

            String jsonDouble = json.writeValueAsString(300.46D);
            log.info("jsonDouble: {}", jsonDouble);

            Double doubleValue = json.readValue("100.36", Double.class);
            log.info("doubleValue: {}", doubleValue);

            String jsonStr = json.writeValueAsString("hello");
            log.info("jsonStr: {}", jsonStr);

            String strValue = json.readValue("\"hello world\"", String.class);
            log.info("strValue: {}", strValue);
        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }
    }

    @Test
    public void testClassType() {
        try {
            ObjectMapper json = new ObjectMapper();

            Person person = new Person("marko", 30);
            String jsonPerson = json.writeValueAsString(person);
            log.info("jsonPerson: {}", jsonPerson);

            Person p = json.readValue("{\"name\":\"james\",\"age\":28}", Person.class);
            log.info("person: {}", p);

        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }

    }

    @Test
    public void testGenericType() {
        try {
            ObjectMapper json = new ObjectMapper();

            List<Person> persons = new ArrayList<>();
            persons.add(new Person("marko", 30));
            persons.add(new Person("james", 28));

            String jsonPersons = json.writeValueAsString(persons);
            log.info("jsonPersons: {}", jsonPersons);

            List<Person> listPerson = json.readValue(jsonPersons, new TypeReference<List<Person>>() { });
            log.info("listPerson: {}", listPerson);

        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }
    }

    /**
     * 自定义序列化和反序列化，自定义converter @JsonSerialize @JsonDeSerialize
     */
    @Test
    public void testCustomSerializer() {
        try {


        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }
    }


    /**
     * @JsonPropertyOrder
     */
    @Test
    public void testCustomOrder() {
        try {


        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }
    }

    /**
     * @JsonManagedReference @JsonBackReference @JsonIdentityInfo
     */
    @Test
    public void testReference() {
        try {


        } catch (Throwable t) {
            log.error("{}", t.getMessage());
        }
    }
}
