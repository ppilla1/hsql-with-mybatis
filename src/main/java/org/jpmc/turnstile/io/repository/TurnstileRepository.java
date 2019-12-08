package org.jpmc.turnstile.io.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.jpmc.turnstile.core.model.Turnstile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface TurnstileRepository {
    void create(@Param("TURNSTILE") Turnstile params);
    void update(@Param("TURNSTILE") Turnstile params);
    void delete(@Param("TURNSTILE") Turnstile params);
    Turnstile readById(@Param("TURNSTILE") Turnstile params);
    List<Turnstile> readAll();
}
