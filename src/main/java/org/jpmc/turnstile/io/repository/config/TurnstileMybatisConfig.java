package org.jpmc.turnstile.io.repository.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.jpmc.config.AbstractMybatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScans({
        @MapperScan(basePackages = {
                "org.jpmc.turnstile.io.repository"
        }, sqlSessionFactoryRef = "turnstileSqlSessionFactory")
})
public class TurnstileMybatisConfig extends AbstractMybatisConfig {

    private final DataSource dataSource;
    private final String mapperLocations;
    private final String typeAliasPackage;

    public TurnstileMybatisConfig(
            @Qualifier("db0DataSource") DataSource dataSource,
            @Value("${mappers.turnstile:classpath:**/turnstile/io/repository/*.xml}") String mapperLocations,
            @Value("${typealiaspackage.turnstile:org.jpmc.turnstile.core.model}") String typeAliasPackage) {
        this.dataSource = dataSource;
        this.mapperLocations = mapperLocations;
        this.typeAliasPackage = typeAliasPackage;
    }

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    @Override
    public String getMapperLocations() {
        return mapperLocations;
    }

    @Override
    public String getTypeAliasesPackage() {
        return typeAliasPackage;
    }

    @Bean("turnstileSqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactory sqlSessionFactory = createSqlSessionFactory();
        return createSqlSessionFactory();
    }
}
