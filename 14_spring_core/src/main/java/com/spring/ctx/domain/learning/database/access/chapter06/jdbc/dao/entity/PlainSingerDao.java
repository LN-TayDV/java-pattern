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
package com.spring.ctx.domain.learning.database.access.chapter06.jdbc.dao.entity;

import lombok.extern.slf4j.Slf4j;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class PlainSingerDao implements SingerDao {
    @Override
    public Set<Singer> findAll() {
        Set<Singer> result = new HashSet<>();
        try (var connection = getConnection();
             var statement = connection.prepareStatement("select * from SINGER");
             var resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                var singer = new Singer();
                singer.setId(resultSet.getString("id"));
                singer.setFirstName(resultSet.getString("first_name"));
                singer.setLastName(resultSet.getString("last_name"));
                singer.setBirthDate(resultSet.getDate("birth_date").toLocalDate());
                result.add(singer);
            }
        } catch (SQLException ex) {
            LOGGER.error("Problem when executing SELECT!",ex);
        }
        return result;
    }

    @Override
    public Optional<String> findNameById(String id) {
        return Optional.empty();
    }

    @Override
    public Singer insert(Singer singer) {

        try ( var connection = getConnection()){
            var statement = connection.prepareStatement(
                "insert into SINGER (id, first_name, last_name, birth_date) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            var generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                singer.setId(generatedKeys.getString(1));
            }

            statement.setString(1, singer.getId());
            statement.setString(2, singer.getFirstName());
            statement.setString(3, singer.getLastName());
            statement.setDate(4, java.sql.Date.valueOf(singer.getBirthDate()));

            statement.execute();

            return singer;
        } catch (SQLException ex) {
            LOGGER.error("Problem executing INSERT", ex);
        }
        return null;

    }

    @Override
    public void delete(String singerId) {
        try (var connection = getConnection();
             var statement = connection.prepareStatement("delete from SINGER where id=?")) {
            statement.setString(1, singerId);
            statement.execute();

        } catch (SQLException ex) {
            LOGGER.error("Problem executing DELETE", ex);
        }
    }
}
