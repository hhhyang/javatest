
package com.javatest.protobuf;

import com.javatest.protobuf.FirstProtobuf.testBuf;

public class ProtobufMain {

    public static void main(String[] args) {

        try {
            testBuf buf = testBuf.newBuilder().setID(80).setUrl("ok").build();
            //builder.setID(80);
            //builder.setUrl("ok");

            //FirstProtobuf.testBuf buf = builder.build();

            byte[] content = buf.toByteArray();

            testBuf newBuf = testBuf.parseFrom(content);

            System.out.println(newBuf.getID());
            System.out.println(newBuf.getUrl());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
