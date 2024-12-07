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

import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.connection.SpringDatasourceCfg;
import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.dao.entity.PlainSingerDao;
import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.dao.entity.Singer;
import com.spring.ctx.domain.learning.database.access.chapter06.jdbc.dao.entity.SingerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

@Slf4j
public class PlainJdbcDemo {

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Problem loading DB Driver!", ex);
        }
    }

    private static SingerDao singerDao = new PlainSingerDao();

    public static void main(String... args) {

        test01();

    }

    public static void test01 () {
        LOGGER.info("Listing initial singer data:");
        listAllSingers();

        LOGGER.info("-------------------------------------------------------------------");
        LOGGER.info("Insert a new singer");
        Singer singer = new Singer(
            UUID.randomUUID().toString(),
            "Ed",
            "Sheeran",
            LocalDate.of(1991, 2, 21),
            new HashSet<>()
        );
        singerDao.insert(singer);
        //LOGGER.info("The singer has ID now: {}", singer.getId());

        test02(singer.getId());

        /*LOGGER.info("----------------------------------------------------------------");
        LOGGER.info("Listing singer data after new singer created:");
        listAllSingers();*/

        /*LOGGER.info("-----------------------------------------------------------------");
        LOGGER.info("Deleting the previous created singer");
        singerDao.delete(singer.getId());
        LOGGER.info("Listing singer data after new singer deleted:");
        listAllSingers();*/
    }

    public static void test02 (String id) {
        var ctx = new AnnotationConfigApplicationContext(SpringDatasourceCfg.class);

        singerDao = ctx.getBean("singerDao", SingerDao.class);

        LOGGER.info("Listing singer data after new singer created:");
        LOGGER.info(singerDao.findNameById(id).orElse("EMPTY"));
    }
    private static void listAllSingers() {
        var singers = singerDao.findAll();
        for (Singer singer: singers) {
            LOGGER.info(singer.toString());
        }
    }
}
