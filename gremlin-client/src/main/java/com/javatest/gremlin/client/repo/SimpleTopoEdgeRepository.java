package com.javatest.gremlin.client.repo;

import com.javatest.gremlin.client.domain.dao.TopoEdge;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@NoRepositoryBean
public interface SimpleTopoEdgeRepository<T extends TopoEdge> extends TopoRepository<T, String> {


    T findByHeadAndTail(final String headId, final String tailId);

    void deleteByHeadAndTail(final String headId, final String tailId);

}
