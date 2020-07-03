package com.javatest.springconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;


public class ConditionImpl implements Condition {

    private static final Logger LOG = LoggerFactory.getLogger(ConditionImpl.class);

    /**
     *
     * @param context 可以获取spring Enviroment等信息
     * @param metadata 可以获取类的注解等信息
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        Environment env = context.getEnvironment();

        if (env != null) {
            MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(ScannedBean2.class.getName());
            if (attrs != null) {
                for (Object value : attrs.get("value")) {
                    LOG.info("value: {}", value);
                    if (env.acceptsProfiles((String[]) value)) {
                        return true;
                    }
                }
            }
            return true;
        }

        return true;

    }
}
