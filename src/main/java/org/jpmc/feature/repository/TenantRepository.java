package org.jpmc.feature.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TenantRepository {
    void add(Map<String, Object> params);
    void update(Map<String, Object> params);
    void delete(Map<String, Object> params);
    List<Map<String, Object>> fetchAll(Map<String, Object> filter);
    Map<String, Object> fetchById(Map<String, Object> filter);
}
