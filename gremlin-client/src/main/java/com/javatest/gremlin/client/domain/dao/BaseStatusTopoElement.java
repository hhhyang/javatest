package com.javatest.gremlin.client.domain.dao;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
public interface BaseStatusTopoElement<ElementID extends Serializable, ID extends Serializable>
        extends BaseTopoElement<ElementID, ID> {

    String getOperationStatus();

}
