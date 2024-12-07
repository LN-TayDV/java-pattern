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
package com.spring.ctx.domain.chapter11.validation.validation.constraints.custom;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// Đánh dấu annotation này để chỉ định rằng nó sẽ được sử dụng tại thời gian chạy (Runtime)
@Retention(RetentionPolicy.RUNTIME)
// Đánh dấu annotation này có thể được sử dụng cho lớp (class)
@Target(ElementType.TYPE)
// Chỉ định rằng annotation này sẽ sử dụng class `CountrySingerValidator` để xác thực
@Constraint(validatedBy = CountrySingerValidator.class)
// Đảm bảo rằng annotation này được đưa vào tài liệu API
@Documented
public @interface CheckCountrySinger {

    // Mặc định thông báo lỗi nếu vi phạm
    String message() default "Country Singer should have gender and last name defined";

    // Định nghĩa nhóm xác nhận (có thể sử dụng cho các mục đích phân nhóm khi xác thực)
    Class<?>[] groups() default {};

    // Định nghĩa payload có thể kèm theo thông tin bổ sung (thường sử dụng cho các thông tin bổ sung liên quan đến lỗi)
    Class<? extends Payload>[] payload() default {};
}
