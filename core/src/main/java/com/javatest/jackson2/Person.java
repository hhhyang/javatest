
package com.javatest.jackson2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
// 反序列化时，忽略不认识的字段
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Person {
    /**
     * name : abc
     * age : 20
     * male : true
     */

    private String name;
    private int age;
    private boolean male;
    // private Exam exam;
    private List<Exam> exam;
    // private Exam[] exam;  // 与List<Exam> 效果一样


    @Data
    public static class Exam {
        private String subject;
        private float score;

    }


}

