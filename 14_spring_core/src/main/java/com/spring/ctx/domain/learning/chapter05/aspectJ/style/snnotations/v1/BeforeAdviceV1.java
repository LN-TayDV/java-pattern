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
package com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v1;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// Đánh dấu lớp này là một Spring Component để Spring quản lý.
@Component
// Đánh dấu lớp này là một Aspect chứa logic AOP (Aspect-Oriented Programming).
@Aspect
// Sử dụng Lombok để tự động tạo logger (`LOGGER`) cho việc ghi log.
@Slf4j
public class BeforeAdviceV1 {

    /**
     * Định nghĩa Advice kiểu `@Before`:
     * - Logic trong phương thức này sẽ được thực thi **trước** khi phương thức mục tiêu được gọi.
     *
     * @param joinPoint: Đối tượng JoinPoint chứa thông tin về phương thức đang được gọi.
     */
    @Before("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void simpleBeforeAdvice(JoinPoint joinPoint) {
        // Lấy thông tin chữ ký của phương thức, bao gồm tên và kiểu lớp khai báo.
        var signature = (MethodSignature) joinPoint.getSignature();

        // Ghi log thông tin về phương thức sắp được thực thi.
        LOGGER.info(" > Executing: {} from {}",
            signature.getName(),              // Tên phương thức (vd: "singWithGuitar").
            signature.getDeclaringTypeName()  // Tên lớp chứa phương thức (vd: "PretentiousGuitarist").
        );
    }
}