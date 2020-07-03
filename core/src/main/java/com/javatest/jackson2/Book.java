
package com.javatest.jackson2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Book {

    private String title;
    @JsonProperty(value = "isbn-10")
    private String isbn10;
    @JsonProperty("isbn-13")
    private String isbn13;
    private List<String> authors;


}
