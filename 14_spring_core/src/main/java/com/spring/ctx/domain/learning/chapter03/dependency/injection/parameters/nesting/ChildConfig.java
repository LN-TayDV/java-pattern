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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.parameters.nesting;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Đánh dấu lớp này là một cấu hình Spring, tức là chứa các bean được đăng ký
public class ChildConfig implements ApplicationContextAware {

    public ApplicationContext applicationContext;  // Biến lưu trữ ApplicationContext của lớp này

    // Định nghĩa một bean với tên "childProvider", sẽ ghi đè lên bean cùng tên từ context cha
    @Bean
    public TitleProvider childProvider() {
        return TitleProvider.instance("No Such Thing");
    }

    // Định nghĩa bean "song1" lấy giá trị của "parentProvider.title" từ context cha
    @Bean
    public Song song1(@Value("#{parentProvider.title}") String title) {
        return new Song(title);
    }

    // Định nghĩa bean "song2", giá trị title lấy từ context cha thông qua ApplicationContext của childConfig
    @Bean
    public Song song2(@Value("#{childConfig.applicationContext.parent.getBean(\"childProvider\").title}") String title) {
        return new Song(title);
    }

    // Định nghĩa bean "song3", giá trị title lấy từ bean childProvider của context hiện tại (context con)
    @Bean
    public Song song3(@Value("#{childProvider.title}") String title) {
        return new Song(title);
    }

    // Phương thức này sẽ được gọi tự động để thiết lập ApplicationContext cho lớp này
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
