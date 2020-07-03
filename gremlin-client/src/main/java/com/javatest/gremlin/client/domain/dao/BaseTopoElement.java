package com.javatest.gremlin.client.domain.dao;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
public interface BaseTopoElement<ElementID extends Serializable, ID extends Serializable> extends TopoElement<ID> {


    String getSource();

    Boolean getBaseline();

    Long getTimestamp();

    ElementID getElementId();

}
