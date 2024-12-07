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
package com.spring.ctx.domain.chapter03.dependency.injection.bean_naming.styles;

import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

public class SimpleBeanNameGenerator extends AnnotationBeanNameGenerator {

    // Override phương thức để xây dựng tên bean mặc định
    @Override
    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        // Lấy tên lớp bean từ BeanDefinition
        var beanName = Objects
            .requireNonNull(definition.getBeanClassName())  // Đảm bảo rằng tên lớp không null
            .substring(definition.getBeanClassName().lastIndexOf(".") + 1)  // Lấy phần tên lớp sau dấu chấm (loại bỏ package)
            .toLowerCase(Locale.ROOT);  // Chuyển tên lớp sang chữ thường (ví dụ: MyBean -> mybean)

        // Tạo một UUID ngẫu nhiên, loại bỏ dấu '-' và lấy 8 ký tự đầu tiên
        var uid = UUID.randomUUID().toString().replace("-", "").substring(0, 8);

        // Kết hợp tên lớp và UUID để tạo ra tên bean cuối cùng
        return beanName + "-" + uid;
    }
}

