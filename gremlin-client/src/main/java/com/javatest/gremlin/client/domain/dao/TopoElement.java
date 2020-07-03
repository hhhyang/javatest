package com.javatest.gremlin.client.domain.dao;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
public interface TopoElement<ID extends Serializable> {

    ID getGid();

}
