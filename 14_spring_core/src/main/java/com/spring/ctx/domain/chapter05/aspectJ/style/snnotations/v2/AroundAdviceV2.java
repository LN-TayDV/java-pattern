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

import com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class AroundAdviceV2 {

    // Định nghĩa một Pointcut để xác định các phương thức cần áp dụng Advice
    // Pointcut này sẽ chọn tất cả các phương thức có tên bắt đầu bằng "sing" và có tham số kiểu Guitar
    @Pointcut("execution(* com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items..sing*(com.spring.ctx.domain.chapter05.aspectJ.style.snnotations.items.Guitar)) && args(value)")
    public void singExecution(Guitar value) {
        // Pointcut không cần cài đặt, chỉ định nơi phương thức này sẽ được áp dụng
    }

    // Định nghĩa Around Advice để bao quanh phương thức mục tiêu
    // @Around cho phép chúng ta can thiệp vào phương thức trước và sau khi phương thức đó thực thi
    @Around(value = "singExecution(guitar)", argNames = "pjp,guitar")
    public Object simpleAroundAdvice(ProceedingJoinPoint pjp, Guitar guitar) throws Throwable {

        // Lấy thông tin chữ ký của phương thức mục tiêu
        var signature = (MethodSignature) pjp.getSignature();

        // Ghi log trước khi phương thức mục tiêu thực thi
        LOGGER.info(" > Before Executing: {} from {} with argument {}",
            signature.getName(), // Tên phương thức
            signature.getDeclaringTypeName(), // Tên lớp khai báo phương thức
            guitar.getBrand()); // Tên thương hiệu của cây đàn guitar

        // Tiến hành thực thi phương thức mục tiêu
        Object retVal = pjp.proceed(); // Gọi phương thức thực tế mà Pointcut chọn

        // Ghi log sau khi phương thức mục tiêu thực thi xong
        LOGGER.info(" > After Executing: {} from {} with argument {}",
            signature.getName(), // Tên phương thức
            signature.getDeclaringTypeName(), // Tên lớp khai báo phương thức
            guitar.getBrand()); // Thương hiệu của cây đàn guitar

        // Trả về kết quả từ phương thức mục tiêu
        return retVal;
    }
}
