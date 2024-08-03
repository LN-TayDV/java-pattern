package com.spring.ctx.domain.database.access.chapter06.jdbc.connection;

import com.spring.ctx.domain.database.access.chapter06.jdbc.connection.JdbcSingerDao;
import com.spring.ctx.domain.database.access.chapter06.jdbc.connection.dataSource.BasicDataSourceCfg;
import com.spring.ctx.domain.database.access.chapter06.jdbc.dao.entity.SingerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

@Import(BasicDataSourceCfg.class)
@Configuration
public class SpringDatasourceCfg {

    @Autowired
    @Qualifier("basicDataSource")
    DataSource dataSource;

    @Bean
    public SingerDao singerDao(){
        JdbcSingerDao dao = new JdbcSingerDao();
        dao.setDataSource(dataSource);
        return dao;
    }

    @Bean public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }
}
