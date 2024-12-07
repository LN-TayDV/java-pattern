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
package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.v4;

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class BeforeAdviceV4 {

    // Định nghĩa một Pointcut cho các phương thức có tên bắt đầu bằng 'sing' và nhận một tham số kiểu Guitar
    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void singExecution(Guitar value) {
    }

    // Định nghĩa một Pointcut khác kiểm tra xem bean có tên bắt đầu bằng 'john' hay không
    @Pointcut("bean(john*)")
    public void isJohn() {
    }

    // Phương thức Advice này sẽ được thực thi trước khi phương thức được gọi, nếu nó thỏa mãn cả hai Pointcut (singExecution và isJohn)
    @Before(value = "singExecution(guitar) && isJohn()", argNames = "joinPoint,guitar")
    public void simpleBeforeAdvice(JoinPoint joinPoint, Guitar guitar) {
        // Kiểm tra xem thương hiệu của guitar có phải là 'Gibson' không
        if (guitar.getBrand().equals("Gibson")) {
            // Nếu đúng, lấy thông tin về phương thức đang được gọi
            var signature = (MethodSignature) joinPoint.getSignature();
            LOGGER.info(" > Executing: {} from {}", signature.getName(),
                signature.getDeclaringTypeName());
        }
    }
}

