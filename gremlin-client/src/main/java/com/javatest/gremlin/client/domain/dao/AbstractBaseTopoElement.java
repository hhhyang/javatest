package com.javatest.gremlin.client.domain.dao;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractBaseTopoElement<ElementID extends Serializable, ID extends Serializable>
        extends SimpleTopoElement<ID>
        implements BaseTopoElement<ElementID, ID> {

    private String source;

    // 基线
    private Boolean baseline;
    // 更新时间戳
    private Long timestamp;


    public AbstractBaseTopoElement<ElementID, ID> copy(final AbstractBaseTopoElement<ElementID, ID> base,
                                                       final String source,
                                                       final Long timestamp) {

        super.copy(base);

        if (base == null) {
            this.source = source;
            this.baseline = false;
            this.timestamp = timestamp;
        } else {
            this.source = source;
            this.baseline = base.getBaseline();
            this.timestamp = base.getTimestamp();
        }

        return this;

    }

}
