package com.spring.ctx.domain.database.access.chapter06.jdbc.connection;

import com.spring.ctx.domain.database.access.chapter06.jdbc.dao.entity.Singer;
import com.spring.ctx.domain.database.access.chapter06.jdbc.dao.entity.SingerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class JdbcSingerDao implements SingerDao, InitializingBean {

    private DataSource dataSource;

//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        var jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        var errorTranslator = new PostgresDbErrorCodesTranslator();
        errorTranslator.setDataSource(dataSource);
        jdbcTemplate.setExceptionTranslator(errorTranslator);
        this.jdbcTemplate = jdbcTemplate;
    }
    //

    @Override
    public void afterPropertiesSet() throws Exception {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on SingerDao");
        }
    }

    @Override
    public Set<Singer> findAll() {
        return null;
    }

    @Override
    public Optional<String> findNameById(String id) {
        /*String sql = String.format(
            " select first_name, last_name from SINGER where id = '%s' ",
            id
        );
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                return Optional.of(
                    resultSet.getString("first_name") + " " + resultSet.getString("last_name")
                );
            }
        } catch (SQLException ex) {
            LOGGER.error("Problem when executing SELECT!",ex);
        }
        return Optional.empty();*/

        return Optional.ofNullable(
             jdbcTemplate.queryForObject("select CONCAT(first_name , ' ' , last_name) from SINGER where id = ?", String.class, id)
        );
    }

    @Override
    public Singer insert(Singer singer) {
        return null;
    }

    @Override
    public void delete(String singerId) {

    }

}
