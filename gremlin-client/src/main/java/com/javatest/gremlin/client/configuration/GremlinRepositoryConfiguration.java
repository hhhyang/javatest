package com.javatest.gremlin.client.configuration;

import com.baidu.spring.data.gremlin.config.AbstractGremlinConfiguration;
import com.baidu.spring.data.gremlin.config.GremlinConfig;
import com.baidu.spring.data.gremlin.repository.config.EnableGremlinRepositories;
import org.springframework.context.annotation.Configuration;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Configuration
@EnableGremlinRepositories(basePackages = "com.example.gremlin.client.repo")
public class GremlinRepositoryConfiguration extends AbstractGremlinConfiguration {

    private GremlinProperties gremlinProps = new GremlinProperties();

    @Override
    public GremlinConfig getGremlinConfig() {

        return new GremlinConfig(gremlinProps.getEndpoints(),
                gremlinProps.getPort(),
                "",
                "",
                false,
                false,
                gremlinProps.getSerializer());
    }
}
