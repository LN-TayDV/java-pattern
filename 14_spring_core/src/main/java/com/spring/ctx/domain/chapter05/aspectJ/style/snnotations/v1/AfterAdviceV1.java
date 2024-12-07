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
package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.v1;

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// Sử dụng Lombok để tự động tạo Logger
@Slf4j
// Đánh dấu lớp này là một Aspect cho AOP
@Aspect
// Đăng ký lớp này là một Spring Bean để Spring quản lý
@Component
public class AfterAdviceV1 {

    // Base package chứa các thành phần mà Aspect sẽ áp dụng
    private static final String BASE_PACKAGE = "com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items";

    /**
     * Định nghĩa Pointcut:
     * Chỉ định các phương thức nào sẽ được Aspect áp dụng.
     * Ở đây, Pointcut áp dụng cho tất cả các phương thức `sing*`
     * trong lớp `PretentiosGuitarist` với tham số là một `Guitar`.
     */
    @Pointcut("execution(* " + BASE_PACKAGE + ".PretentiosGuitarist.sing*(" + BASE_PACKAGE + ".Guitar)) && args(value)")
    public void singExecution(Guitar value) {
        // Phương thức này chỉ để đặt tên cho Pointcut, không cần logic.
    }

    /**
     * Định nghĩa một Advice:
     * Sử dụng Annotation @After để chỉ định hành động sẽ xảy ra
     * sau khi phương thức phù hợp với Pointcut được thực thi.
     *
     * @param joinPoint: Chứa thông tin về phương thức được gọi.
     * @param guitar: Đối tượng Guitar được truyền vào phương thức.
     */
    @After(value = "singExecution(guitar)", argNames = "joinPoint,guitar")
    public void simpleAfterAdvice(JoinPoint joinPoint, Guitar guitar) {
        // Lấy thông tin chữ ký (method signature) của phương thức được gọi
        var signature = (MethodSignature) joinPoint.getSignature();

        // Ghi log thông tin về phương thức được gọi và đối tượng Guitar
        LOGGER.info(" > Executed: {} from {} with guitar {} ",
            signature.getName(), // Tên phương thức (vd: "singWithGuitar")
            signature.getDeclaringTypeName(), // Tên lớp chứa phương thức
            guitar.getBrand()); // Lấy thông tin brand của Guitar
    }
}
