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
package com.spring.ctx.domain.learning.chapter11.field.formatting;

import jakarta.annotation.PostConstruct;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Service;

@Service("conversionService")
@Slf4j
public class ApplicationConversionServiceFactoryBean extends FormattingConversionServiceFactoryBean {

    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";  // Mẫu định dạng ngày mặc định
    private final Set<Formatter<?>> formatters = new HashSet<>();  // Bộ sưu tập các formatter
    private DateTimeFormatter dateTimeFormatter;  // Định dạng thời gian
    private String datePattern = DEFAULT_DATE_PATTERN;  // Mẫu định dạng ngày mặc định

    // Getter cho datePattern
    public String getDatePattern() {
        return datePattern;
    }

    // Setter cho datePattern, có thể nhận giá trị từ các cấu hình Spring
    @Autowired(required = false)
    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    // Phương thức khởi tạo, được gọi sau khi tất cả các thuộc tính đã được thiết lập
    @PostConstruct
    public void init() {
        // Khởi tạo DateTimeFormatter với mẫu ngày đã được thiết lập
        dateTimeFormatter = DateTimeFormatter.ofPattern(datePattern);
        // Thêm formatter cho LocalDate vào danh sách formatters
        formatters.add(getDateTimeFormatter());
        // Gọi phương thức setFormatters để thiết lập các formatter cho service
        setFormatters(formatters);
    }

    // Phương thức tạo Formatter cho LocalDate
    public Formatter<LocalDate> getDateTimeFormatter() {
        return new Formatter<>() {
            // Phương thức phân tích cú pháp chuỗi ngày và chuyển đổi thành LocalDate
            @Override
            public LocalDate parse(String source, Locale locale) throws ParseException {
                LOGGER.info("Parsing date string: " + source);  // Log thông tin phân tích
                return LocalDate.parse(source, dateTimeFormatter);  // Phân tích chuỗi thành LocalDate
            }

            // Phương thức định dạng LocalDate thành chuỗi
            @Override
            public String print(LocalDate source, Locale locale) {
                LOGGER.info("Formatting datetime: " + source);  // Log thông tin định dạng
                return source.format(dateTimeFormatter);  // Định dạng LocalDate thành chuỗi
            }
        };
    }
}
