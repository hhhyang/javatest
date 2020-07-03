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
public abstract class BaseTopoVertex<ElementID extends Serializable> extends AbstractBaseTopoElement<ElementID, Long>
        implements TopoVertex {


    @Override
    public abstract ElementID getElementId();

}
