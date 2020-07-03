package com.javatest.gremlin.client.domain.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
public class SimpleTopoVertex extends SimpleTopoElement<Long> implements TopoVertex {


    public SimpleTopoVertex(final Long gid) {
        super(gid);
    }

}
