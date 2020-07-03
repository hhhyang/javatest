package com.javatest.gremlin.client.domain.dao;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class BaseStatusTopoVertex<ElementID extends Serializable> extends BaseTopoVertex<ElementID>
        implements BaseStatusTopoElement<ElementID, Long>, TopoVertex {


    @Override
    public abstract ElementID getElementId();

    @Override
    public abstract String getOperationStatus();


}
