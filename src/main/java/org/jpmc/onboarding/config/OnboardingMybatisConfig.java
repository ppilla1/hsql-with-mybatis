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
    private final String typeAliasPackage;

    public OnboardingMybatisConfig(@Qualifier("db0DataSource") DataSource dataSource,
                                   @Value("${mappers.onboarding:classpath:**/onboarding/repository/*.xml}") String mapperLocations,
                                   @Value("${typealiaspackage.onboarding:org.jpmc.onboarding}") String typeAliasPackage) {
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

    @Primary
    @Bean("db0SqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return createSqlSessionFactory();
    }
}
