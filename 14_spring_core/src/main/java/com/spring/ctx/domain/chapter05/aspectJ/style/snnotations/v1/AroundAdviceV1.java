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

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

// Đánh dấu lớp này là một Spring Component để Spring quản lý
@Component
// Đánh dấu lớp này là một Aspect, nghĩa là nó chứa logic AOP
@Aspect
// Sử dụng Lombok để tự động tạo Logger
@Slf4j
public class AroundAdviceV1 {

    // Định nghĩa gói cơ sở để áp dụng cho các phương thức trong lớp này
    private static final String BASE_PACKAGE = "com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items";

    /**
     * Định nghĩa Pointcut:
     * Xác định các phương thức nào sẽ được áp dụng Aspect.
     * Ở đây, nó áp dụng cho tất cả các phương thức bắt đầu bằng `sing` trong `BASE_PACKAGE`
     * và có tham số là đối tượng kiểu `Guitar`.
     */
    @Pointcut("execution(* " + BASE_PACKAGE + "..sing*(" + BASE_PACKAGE + ".Guitar))")
    public void singExecution() {
        // Đây chỉ là định danh của Pointcut, không cần logic thực thi.
    }

    /**
     * Định nghĩa Advice kiểu `@Around`:
     * - Advice này sẽ bao quanh phương thức mục tiêu (cả trước và sau khi thực thi).
     * - Sử dụng `ProceedingJoinPoint` để có thể tiếp tục luồng xử lý của phương thức gốc.
     *
     * @param pjp: Đối tượng `ProceedingJoinPoint` chứa thông tin về phương thức được gọi.
     * @return Giá trị trả về từ phương thức mục tiêu.
     * @throws Throwable: Nếu phương thức mục tiêu ném ra exception, Advice cũng cần chuyển tiếp nó.
     */
    @Around("singExecution()")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        // Lấy thông tin về chữ ký của phương thức (vd: tên phương thức, kiểu trả về, v.v.)
        var signature = (MethodSignature) pjp.getSignature();

        // Log thông tin trước khi thực thi phương thức mục tiêu
        LOGGER.info(" > Before Executing: {} from {}",
            signature.getName(), // Tên phương thức (vd: "singWithGuitar")
            signature.getDeclaringTypeName()); // Tên lớp chứa phương thức

        // Tiếp tục thực thi phương thức mục tiêu
        Object retVal = pjp.proceed();

        // Log thông tin sau khi thực thi phương thức mục tiêu
        LOGGER.info(" > After Executing: {} from {}",
            signature.getName(),
            signature.getDeclaringTypeName());

        // Trả lại giá trị trả về của phương thức mục tiêu
        return retVal;
    }
}
