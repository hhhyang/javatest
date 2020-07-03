package com.javatest.gremlin.client.repo;

import com.javatest.gremlin.client.domain.dao.BaseTopoElement;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@NoRepositoryBean
public interface BaseTopoRepository<T extends BaseTopoElement, ID extends Serializable> extends TopoRepository<T, ID> {

    T findByElementId(final String objectId);

    void deleteByElementId(final String objectId);

    List<T> findBySource(final String source);

}
