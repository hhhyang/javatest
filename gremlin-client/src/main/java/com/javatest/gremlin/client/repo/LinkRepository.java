package com.javatest.gremlin.client.repo;

import com.javatest.gremlin.client.domain.dao.Link;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 *
 * @author yangshengbing@baidu.com
 */
@NoRepositoryBean
public interface LinkRepository<T extends Link> extends BaseStatusTopoRepository<T, String> {

    void deleteByLinkId(final String linkid);

    T findByLinkId(final String linkid);

    @Override
    T updateOperationStatus(final String linkid, final String status, final Long timestamp);

}
