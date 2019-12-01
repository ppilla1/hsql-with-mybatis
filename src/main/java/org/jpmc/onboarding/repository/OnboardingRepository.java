package org.jpmc.onboarding.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface OnboardingRepository {
    void addOrUpdate(Map<String, Object> params);
    List<Map<String, Object>> fetch(Map<String, Object> filter);
}
