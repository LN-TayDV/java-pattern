/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 *
 * The MIT License
 * Copyright © 2014-2022 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.spring.ctx.domain.learning.database.access.chapter06.jdbc;

import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.connection.dataSource.JndiDataSourceCfg;
import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.connection.dataSource.SimpleDataSourceCfg;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class DataSourceConfigTest {

    @SneakyThrows
    public static void main(String[] args) {
        testJndiDataSource();
    }

    public static  void testSimpleDataSource() throws SQLException {
        var ctx = new AnnotationConfigApplicationContext(SimpleDataSourceCfg.class);

        var dataSource = ctx.getBean("dataSource", DataSource.class);

        if(dataSource != null) {
            testDataSource(dataSource);
        }

        LOGGER.info("connection close");
        ctx.close();
    }

    public static  void testJndiDataSource() throws SQLException {
        var ctx = new AnnotationConfigApplicationContext(JndiDataSourceCfg.class);

        var dataSource = ctx.getBean("dataSource", DataSource.class);

        if(dataSource != null) {
            testDataSource(dataSource);
        }

        LOGGER.info("connection close");
        ctx.close();
    }

    private static void  testDataSource(DataSource dataSource) throws SQLException{
        try (var connection = dataSource.getConnection();
             var statement = connection.prepareStatement("SELECT 1");
             var resultSet = statement.executeQuery()){
            while (resultSet.next()) {
                int mockVal = resultSet.getInt("1");

                if(mockVal == 1) {
                    LOGGER.info("mockVal == 1 ? => {}", mockVal == 1);
                }
            }
        } catch (Exception e) {
            LOGGER.debug("Something unexpected happened.", e);
        }
    }
}
