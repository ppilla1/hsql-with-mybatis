package org.jpmc.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
public abstract class AbstractMybatisConfig {

    public abstract DataSource getDatasource();
    public abstract String getMapperLocations();
    public abstract SqlSessionFactory sqlSessionFactory() throws Exception;

    protected SqlSessionFactory createSqlSessionFactory() throws Exception {
        SqlSessionFactory sqlSessionFactory = null;

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(getDatasource());

        if (null != getMapperLocations()){
            try {
                sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(getMapperLocations()));
            } catch (IOException e) {
                log.warn("{}", e.getMessage(), e);
            }
        }

        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }
}
