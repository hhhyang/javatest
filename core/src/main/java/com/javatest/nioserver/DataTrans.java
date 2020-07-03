package com.javatest.nioserver;

import lombok.Data;

@Data
public class DataTrans {

    private char c;
    private int i;
    private long l;
    private double d;

    public String toString() {

        return "c: " + c + ", i: " + i + ", l: " + l + ", d: " + d;
    }


}
