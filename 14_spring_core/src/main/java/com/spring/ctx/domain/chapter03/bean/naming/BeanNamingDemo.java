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
package com.spring.ctx.domain.chapter03.bean.naming;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

/**
 * @Component:
 *
 * @Component là một annotation chung để khai báo một class là một bean.
 * Các subclass của @Component như @Service, @Repository, và @Controller
 * cung cấp ý nghĩa ngữ cảnh cụ thể hơn và có thể được sử dụng để phân biệt các lớp
 * khác nhau dựa trên chức năng của chúng trong ứng dụng.
 *
 * @Configuration:
 *
 * @Configuration là một loại đặc biệt của @Component
 * được sử dụng để khai báo một class là một class cấu hình (configuration class).
 * Các class này thường chứa các phương thức với @Bean để định nghĩa và cấu hình các bean khác.
 */
@Slf4j
public class BeanNamingDemo {

    private static final Logger log = LOGGER;

    public static void main(String... args) {

        var ctx = new AnnotationConfigApplicationContext(BeanNamingCfg.class);

        //Arrays.stream(ctx.getBeanDefinitionNames()).forEach(beanName -> log.info(beanName));

        var simpleBeans = ctx.getBeansOfType(SimpleBean.class);
        simpleBeans.forEach((k,v) -> {
            var aliases = ctx.getAliases(k);
            if(aliases.length > 0) {
                log.info("Aliases for {} ", k);
                Arrays.stream(aliases).forEach(a -> log.info("\t {}", a));
            }
        });
    }
}
