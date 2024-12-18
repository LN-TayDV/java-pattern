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

package com.spring.ctx.domain.learning.chapter11.PropertyEditors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.spring.ctx.domain.learning.chapter11.AppConfig;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class ConvertersTest {

    private static final ObjectMapper mapper = new ObjectMapper(); // Tạo đối tượng ObjectMapper

    // Đăng ký module JavaTimeModule để xử lý các đối tượng thời gian (ví dụ LocalDate)
    static {
        mapper.registerModule(new JavaTimeModule());
    }

    @SneakyThrows
    public static void main(String[] args) {

        // Khởi tạo Spring ApplicationContext và tải các cấu hình
        try (var ctx = new AnnotationConfigApplicationContext(AppConfig.class,
            CustomRegistrarCfg.class)) {

            // Lấy bean Blogger từ context và chuyển nó thành chuỗi JSON
            var springBlogger = ctx.getBean("springBlogger", Blogger.class);
            String springBloggerJson = mapper.writeValueAsString(springBlogger);
            LOGGER.info("SpringBlogger info: {}", springBloggerJson);

            // Lấy bean AwsBlogger từ context và chuyển nó thành chuỗi JSON
            var awsBlogger = ctx.getBean("awsBlogger", Blogger.class);
            String awsBloggerJson = mapper.writeValueAsString(awsBlogger);
            LOGGER.info("AwsBlogger info: {}", awsBloggerJson);
        }
    }
}
