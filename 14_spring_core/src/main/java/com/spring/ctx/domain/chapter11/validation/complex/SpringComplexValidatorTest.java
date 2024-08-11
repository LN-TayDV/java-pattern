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
package com.spring.ctx.domain.chapter11.validation.complex;

import com.spring.ctx.domain.chapter11.AppConfig;
import com.spring.ctx.domain.chapter11.FormattingServiceCfg;
import java.time.LocalDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ValidationUtils;

@Slf4j
public class SpringComplexValidatorTest {

    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(
            AppConfig.class,
            FormattingServiceCfg.class,
            AddressValidator.class,
            BloggerWithAddressValidator.class
        )) {
            var address = new Address("221B", "UK");

            var blogger =
                new BloggerWithAddress(null, "Mazzie", LocalDate.of(1973, 1, 1), null, address);

            var bloggerValidator = ctx.getBean(BloggerWithAddressValidator.class);

            var result = new BeanPropertyBindingResult(blogger, "blogger");

            ValidationUtils.invokeValidator(bloggerValidator, blogger, result);

            var errors = result.getAllErrors();

            LOGGER.info(" assertEquals : {}", 2 == errors.size());

            errors.forEach(e -> LOGGER.info("Error Code: {}", e.getCode()));
        }

    }
}
