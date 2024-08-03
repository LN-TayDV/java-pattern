package com.spring.ctx.domain.database.access.chapter06.jdbc.connection.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiTemplate;
import javax.sql.DataSource;

@Configuration
@Slf4j
public class JndiDataSourceCfg {

    @Bean
    public DataSource dataSource() {
        try {
            return (DataSource) new JndiTemplate().lookup("java:comp/env/jdbc/musicdb");
        } catch (Exception e) {
            LOGGER.error("JNDI DataSource bean cannot be created!", e);
            return null;
        }
    }
}
