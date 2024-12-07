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

package com.spring.ctx.domain.chapter11.type.conversion;

import com.spring.ctx.domain.chapter11.AppConfig;
import java.util.HashSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ConversionServiceFactoryBean;

@Configuration
@ComponentScan(basePackages = {
    "com.spring.ctx.domain.chapter11.type.conversion"
})
@Import(AppConfig.class)
public class ConverterCfg {

    // Định nghĩa bean ConversionService để cấu hình các converter tùy chỉnh
    @Bean
    public ConversionServiceFactoryBean conversionService() {

        // Tạo đối tượng ConversionServiceFactoryBean, nơi chứa các converter tùy chỉnh
        var conversionServiceFactoryBean = new ConversionServiceFactoryBean();

        // Khởi tạo một Set để chứa các converter
        var convs = new HashSet<>();

        // Thêm các converter vào Set
        convs.add(new LocalDateConverter()); // Thêm LocalDateConverter tùy chỉnh
        convs.add(new SimpleBlogger.BloggerToSimpleBloggerConverter()); // Thêm SimpleBlogger.BloggerToSimpleBloggerConverter tùy chỉnh

        // Thiết lập các converter đã thêm vào ConversionServiceFactoryBean
        conversionServiceFactoryBean.setConverters(convs);

        // Gọi phương thức afterPropertiesSet để khởi tạo bean sau khi tất cả các thuộc tính đã được thiết lập
        conversionServiceFactoryBean.afterPropertiesSet();

        // Trả về ConversionServiceFactoryBean đã được cấu hình
        return conversionServiceFactoryBean;
    }
}
