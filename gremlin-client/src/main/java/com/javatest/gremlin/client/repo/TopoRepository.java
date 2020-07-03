package com.javatest.gremlin.client.repo;

import com.baidu.spring.data.gremlin.repository.GremlinRepository;
import com.javatest.gremlin.client.domain.dao.TopoElement;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface TopoRepository<T extends TopoElement, ID extends Serializable> extends GremlinRepository<T, ID> {
}
