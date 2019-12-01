package org.jpmc.onboarding.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jpmc.config.AbstractMybatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@MapperScans({
        @MapperScan(basePackages = {"org.jpmc.onboarding.repository"}, sqlSessionFactoryRef = "db0SqlSessionFactory")
})
public class OnboardingMybatisConfig extends AbstractMybatisConfig {

    private final DataSource dataSource;
    private final String mapperLocations;

    public OnboardingMybatisConfig(@Qualifier("db0DataSource") DataSource dataSource,
                                   @Value("${mappers.feature:classpath:**/onboarding/repository/*.xml}") String mapperLocations) {
        this.dataSource = dataSource;
        this.mapperLocations = mapperLocations;
    }

    @Override
    public DataSource getDatasource() {
        return dataSource;
    }

    @Override
    public String getMapperLocations() {
        return mapperLocations;
    }

    @Primary
    @Bean("db0SqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return createSqlSessionFactory();
    }
}
