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

package com.spring.ctx.domain.chapter11;

import com.spring.ctx.domain.chapter11.PropertyEditors.Blogger;
import java.net.URL;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// Annotation @PropertySource để chỉ định nguồn tài nguyên chứa các giá trị cấu hình (properties file)
// Tệp properties này nằm trong classpath và có tên là "blogger.properties"
@PropertySource("classpath:blogger.properties")
@Configuration
public class AppConfig {

    // Phương thức này tạo ra một Bean Blogger cho awsBlogger và sử dụng các giá trị mặc định được chỉ định trong @Value
    @Bean
    public Blogger awsBlogger(@Value("Alex") String firstName,
                              @Value("DeBrie") String lastName,
                              @Value("https://www.alexdebrie.com") URL personalSite,
                              @Value("1980-01-02") LocalDate birthDate) throws Exception {
        // Tạo và trả về đối tượng Blogger sử dụng các giá trị đã chỉ định
        return new Blogger(firstName, lastName, birthDate, personalSite);
    }

    // Phương thức này tạo ra một Bean Blogger cho springBlogger và sử dụng các giá trị cấu hình từ tệp properties
    @Bean
    public Blogger springBlogger(@Value("${springBlogger.firstName}") String firstName,
                                 @Value("${springBlogger.lastName}") String lastName,
                                 @Value("${springBlogger.personalSite}") URL personalSite,
                                 @Value("${springBlogger.birthDate}") LocalDate birthDate)
        throws Exception {
        // Tạo và trả về đối tượng Blogger sử dụng các giá trị từ tệp properties
        return new Blogger(firstName, lastName, birthDate, personalSite);
    }
}
