package org.jpmc.feature.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.jpmc.config.AbstractMybatisConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScans({
        @MapperScan(basePackages = {"org.jpmc.feature.repository"}, sqlSessionFactoryRef = "db1SqlSessionFactory")
})
public class FeatureMybatisConfig extends AbstractMybatisConfig {

    private final DataSource dataSource;
    private final String mapperLocations;
    private final String typeAliasPackage;

    public FeatureMybatisConfig(@Qualifier("db1DataSource") DataSource dataSource,
                                @Value("${mappers.feature:classpath:**/feature/repository/*.xml}") String mapperLocations,
                                @Value("${typealiaspackage.feature:org.jpmc.feature}") String typeAliasPackage) {
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

    @Bean("db1SqlSessionFactory")
    @Override
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        return createSqlSessionFactory();
    }
}
