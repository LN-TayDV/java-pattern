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

import com.spring.ctx.domain.chapter03.bean.naming.styles.SimpleBeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Đoạn này giải thích về cách đặt tên bean trong Spring khi sử dụng @Configuration.
 * Khi bạn sử dụng @Configuration để đánh dấu một class là một class cấu hình (configuration class) trong Spring,
 * class này tự động được đánh dấu là một bean bằng cách sử dụng @Component.
 * Do đó, bất kỳ class cấu hình nào cũng chính là một định nghĩa bean.
 *
 * Cụ thể, tên của bean này sẽ mặc định là tên đơn giản của class cấu hình đó
 * (simple name of the configuration class).
 * Ví dụ, nếu bạn có một class cấu hình tên là BeanNamingCfg, thì tên của bean này sẽ là "beanNamingCfg".
 *
 * Điều này có nghĩa là bạn có thể sử dụng tên bean "beanNamingCfg"
 * để tham chiếu đến bean đó trong các phần khác của ứng dụng Spring của bạn.
 */

@Configuration// cũng là bean
@ComponentScan(
        nameGenerator = SimpleBeanNameGenerator.class,
        basePackages = "com.spring.ctx.domain.chapter03.bean.naming"
)
public class BeanNamingCfg {

    /**
     * As you can see, an entry named anotherSimpleBean is listed,
     * which means that a SimpleBean was created and named as the method that created it.
     */
    @Bean("simpleBeanTwo")
    public SimpleBean anotherSimpleBean(){
        return new SimpleBean();
    }

    @Bean({"simpleBeanThree", "three", "numero_tres"})
    public SimpleBean simpleBean3(){
        return new SimpleBean();
    }
}
