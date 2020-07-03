
package com.javatest.gson;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Book {

    /**
     * title : Java Puzzlers: Traps, Pitfalls, and Corner Cases
     * isbn-10 : 032133678X
     * isbn-13 : 978-0321336781
     * authors : ["Joshua Bloch","Neal Gafter"]
     */

    private String title;
    @SerializedName(value = "isbn-10", alternate = {"Isbn-10", "isbn_10"})
    private String isbn10;
    @SerializedName("isbn-13")
    private String isbn13;
    private List<String> authors;



}

