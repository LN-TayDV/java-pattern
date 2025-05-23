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
package com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.v2;

import com.spring.ctx.domain.learning.chapter05.aspectJ.style.snnotations.items.Guitar;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AfterThrowingAdviceV2 {

    // Định nghĩa Pointcut để xác định phương thức mục tiêu.
    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.PretentiosGuitarist.sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..Guitar)) && args(value)")
    public void singExecution(Guitar value) {
    }

    // Định nghĩa Advice sử dụng @AfterThrowing
    @AfterThrowing(value = "singExecution(guitar) ", argNames = "joinPoint,guitar, ex", throwing = "ex")
    public void simpleAfterAdvice(JoinPoint joinPoint, Guitar guitar, IllegalArgumentException ex) {
        // Lấy thông tin chữ ký của phương thức mục tiêu đang gặp exception.
        var signature = (MethodSignature) joinPoint.getSignature();

        // Ghi log thông tin về phương thức và thông tin về guitar.
        LOGGER.info(" > Executed: {} from {} with guitar {} ",
            signature.getName(),
            signature.getDeclaringTypeName(), guitar.getBrand());

        // Nếu exception có thông báo chứa "Unacceptable guitar!", ném lại exception dưới dạng RuntimeException.
        if (ex.getMessage().contains("Unacceptable guitar!")) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}
