package com.javatest.gremlin.client.domain.dao;

import com.baidu.spring.data.gremlin.annotation.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

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
public class SimpleTopoElement<ID extends Serializable> implements TopoElement<ID> {

    @Id
    @GeneratedValue
    private ID gid;


    public SimpleTopoElement<ID> copy(final SimpleTopoElement<ID> base) {
        if (base != null) {
            this.gid = base.getGid();
        }

        return this;
    }

}
