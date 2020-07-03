package com.javatest.gremlin.client.repo;

import com.javatest.gremlin.client.domain.dao.BaseStatusTopoElement;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@NoRepositoryBean
public interface BaseStatusTopoRepository<T extends BaseStatusTopoElement, ID extends Serializable>
        extends BaseTopoRepository<T, ID> {

    T updateOperationStatus(final String elementId, final String status, final Long timestamp);

}
