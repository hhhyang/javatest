
package com.javatest.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class HttpClientMain {
    public static void main(String[] args) {
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://localhost:8090/spittr/hello");
            request.setHeader("Accept", "application/gson");
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();

            System.out.println(entity.getContent().toString());

            //ObjectMapper mapper = new ObjectMapper();

            //Spittle sp = mapper.readValue(entity.getContent(), Spittle.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
