package com.javatest.springconfig;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

// 设置bean的名字
@Service("bean3")
public class ScannedBean3 {

    private static final Logger LOG = LoggerFactory.getLogger(ScannedBean3.class);

    @Setter
    @Getter
    @Accessors(chain = true)
    private int val = 0;


    public ScannedBean3(final int val) {
        LOG.info("init ScannedBean3, val = {}", val);
        this.val = val;
    }

    // 必须要有无参构造函数，但是可以是private，使用反射进行实例化的？
    private ScannedBean3() {
        LOG.info("init ScannedBean3");
    }

}
