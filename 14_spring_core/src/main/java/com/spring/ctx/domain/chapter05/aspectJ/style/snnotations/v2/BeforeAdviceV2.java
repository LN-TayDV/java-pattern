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
package com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.v2;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component // Đánh dấu lớp này là một Spring Bean để Spring có thể quản lý nó
@Aspect // Đánh dấu lớp này là một Aspect trong Spring AOP
@Slf4j // Annotation Lombok tự động tạo đối tượng Logger cho lớp này
public class BeforeAdviceV2 {

    // Định nghĩa Pointcut để xác định các phương thức cần áp dụng Advice
    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar))")
    public void singExecution() {
        // Pointcut không cần cài đặt logic, chỉ cần khai báo nơi phương thức này sẽ được áp dụng
    }

    // Định nghĩa Before Advice để can thiệp vào phương thức trước khi nó thực thi
    @Before("singExecution()") // Áp dụng Advice cho các phương thức được chọn bởi Pointcut singExecution()
    public void simpleBeforeAdvice(JoinPoint joinPoint) {

        // Lấy chữ ký của phương thức đang được gọi (tên phương thức, tên lớp)
        var signature = (MethodSignature) joinPoint.getSignature();

        // Ghi log thông tin về phương thức đang được gọi trước khi nó thực thi
        LOGGER.info(" > Executing: {} from {}", signature.getName(), signature.getDeclaringTypeName());
    }
}

