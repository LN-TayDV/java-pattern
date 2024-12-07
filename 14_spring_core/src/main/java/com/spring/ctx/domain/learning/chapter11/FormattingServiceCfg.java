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
package com.spring.ctx.domain.learning.chapter11;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

// Đánh dấu lớp này là một lớp cấu hình cho Spring
@Configuration
public class FormattingServiceCfg {

    // Định nghĩa một bean của loại FormattingConversionService.
    // Bean này cung cấp dịch vụ có thể định dạng và phân tích các đối tượng, ví dụ như LocalDate.
    @Bean
    public FormattingConversionService conversionService() {

        // Tạo một thể hiện mới của DefaultFormattingConversionService.
        // Tham số "true" chỉ ra rằng dịch vụ này sẽ được khởi tạo với một bộ các định dạng mặc định.
        var formattingConversionServiceBean = new DefaultFormattingConversionService(true);

        // Thêm một formatter tùy chỉnh cho LocalDate.
        formattingConversionServiceBean.addFormatter(localDateFormatter());

        // Trả về FormattingConversionService đã được cấu hình.
        return formattingConversionServiceBean;
    }

    // Định nghĩa một formatter tùy chỉnh cho LocalDate. Formatter này chịu trách nhiệm phân tích và định dạng đối tượng LocalDate.
    protected Formatter<LocalDate> localDateFormatter() {

        // Tạo một implementation của Formatter cho LocalDate.
        return new Formatter<LocalDate>() {
            // Phương thức parse nhận một chuỗi (source) và chuyển đổi nó thành đối tượng LocalDate.
            @Override
            public LocalDate parse(String source, Locale locale) throws ParseException {
                // Phân tích chuỗi thành LocalDate sử dụng phương thức getDateTimeFormatter().
                return LocalDate.parse(source, getDateTimeFormatter());
            }

            // Phương thức print nhận một đối tượng LocalDate và chuyển đổi nó thành chuỗi đã định dạng.
            @Override
            public String print(LocalDate source, Locale locale) {
                // Chuyển đối tượng LocalDate thành chuỗi sử dụng phương thức getDateTimeFormatter().
                return source.format(getDateTimeFormatter());
            }

            // Một phương thức trợ giúp trả về DateTimeFormatter với định dạng mong muốn.
            // Trong trường hợp này, trả về định dạng "yyyy-MM-dd".
            protected DateTimeFormatter getDateTimeFormatter() {
                return DateTimeFormatter.ofPattern("yyyy-MM-dd");
            }
        };
    }
}

