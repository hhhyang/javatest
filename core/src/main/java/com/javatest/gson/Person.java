
package com.javatest.gson;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;
import lombok.Getter;
import lombok.Setter;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Person {

    /**
     * name : abc
     * age : 20
     * male : true
     */

    private String name;
    private Integer age;
    private Boolean male;
    private List<Exam> exam;
    private BigDecimal socre;

    private Object data;
    // private Exam[] exam;  // 与List<Exam> 效果一样


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String appendJsonString(final String str) {
        try {

            StringWriter stringWriter = new StringWriter();

            Gson gson = new Gson();
            JsonWriter writer = new JsonWriter(stringWriter);
            writer.beginObject()
                    .name("name")
                    .jsonValue(gson.toJson(this.name))
                    .name("age")
                    .jsonValue(gson.toJson(this.age))
                    .name("male")
                    .jsonValue(gson.toJson(this.male))
                    .name("data")
                    .jsonValue(str)
                    .endObject();

            writer.close();

            return stringWriter.toString();
        } catch (Exception e) {
            //
        }

        return " ";
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", male=" + male +
                ", exam=" + exam +
                '}';
    }

    public static class Exam {
        private String subject;
        private float score;

        @Override
        public String toString() {
            return "Exam{" +
                    "subject='" + subject + '\'' +
                    ", score=" + score +
                    '}';
        }
    }


}

