package com.spring.ctx.domain.database.access.chapter06.jdbc.connection.dataSource;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db/jdbc.properties")
@Slf4j
public class BasicDataSourceCfg {

    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${url}")
    private String url;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean(name = "basicDataSource", destroyMethod = "close")
    public DataSource dataSource() {
        if(username.equals("ADMIN")) {
            username = "postgres";
        }
        try {
            var dataSource = new BasicDataSource();
            dataSource.setDriverClassName(driverClassName);
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            LOGGER.info("dataSource: {}, {}, {}, {} ", driverClassName, url, username, password);

            return dataSource;
        } catch (Exception e) {
            LOGGER.error("DBCP DataSource bean cannot be created!", e);
            return null;
        }
    }
}
