package org.jpmc.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DsConfig {

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "datasources.onboarding")
    public HikariDataSource db0DataSource(){
        return new HikariDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix = "datasources.feature")
    public HikariDataSource db1DataSource(){
        return new HikariDataSource();
    }

}
