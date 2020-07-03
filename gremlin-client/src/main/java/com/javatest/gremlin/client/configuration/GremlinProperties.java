package com.javatest.gremlin.client.configuration;

import lombok.Data;
import org.apache.tinkerpop.gremlin.driver.ser.Serializers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@Component
public class GremlinProperties {

    // @Value("#{'${gremlin.endpoints}'.split(',')}")
    private List<String> endpoints;


    private Integer port = 8182;

    /*
    @Value("${gremlin.username}")
    private String username;

    @Value("${gremlin.password}")
    private String password;

    private boolean sslEnabled;

    private boolean telemetryAllowed = true;
    */
    private String serializer = Serializers.GRYO_V3D0.toString();

    public GremlinProperties() {
        this.endpoints = new ArrayList<>();
        this.endpoints.add("127.0.0.1");
    }
}
