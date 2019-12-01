package org.jpmc.config;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
public class EmbeddedDbConfig {

    @PostConstruct
    public void launchDbConsole(){
        String showConsole = System.getenv("SHOW_DB_CONSOLE");
        if (null != showConsole && showConsole.equals("true")){

            DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:db0", "--user", "sa", "--password", ""});
            DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:db1", "--user", "sa", "--password", ""});
        }
    }

    @Bean
    public EmbeddedDatabase db0Ds(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("db0");

        return builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("/db/sql/create-schema-0.sql")
                .addScript("/db/sql/load-data-0.sql")
                .build();
    }

    @Bean
    public EmbeddedDatabase db1Ds(){
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setName("db1");

        return builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("/db/sql/create-schema-1.sql")
                .addScript("/db/sql/load-data-1.sql")
                .build();
    }

}
