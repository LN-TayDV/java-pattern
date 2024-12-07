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
package com.spring.ctx.domain.learning.chapter11.validation.validation.constraints;

import jakarta.validation.ConstraintViolation;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class JakartaValidationTest {

    public static void main(String[] args) {

        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {

            // Lấy bean `SingerValidationService` từ Spring context
            var singerBeanValidationService = ctx.getBean(SingerValidationService.class);

            // Tạo đối tượng `SingerDomain` và thiết lập các giá trị
            SingerDomain singer = new SingerDomain();
            singer.setFirstName("J");
            singer.setLastName("Mayer");
            singer.setGenre(null);  // Genre không được định nghĩa
            singer.setGender(null); // Gender không được định nghĩa

            // Tiến hành validate đối tượng `singer` thông qua service
            var violations = singerBeanValidationService.validateSinger(singer);

            // Kiểm tra và log số lượng vi phạm
            LOGGER.info(" assertEquals : {}", 2 == violations.size());

            // Liệt kê các vi phạm (nếu có)
            listViolations(violations);
        }

    }

    // Phương thức này in ra các thông tin vi phạm của đối tượng `SingerDomain`
    private static void listViolations(Set<ConstraintViolation<SingerDomain>> violations) {
        violations.forEach(violation ->
            LOGGER.info(
                "Validation error for property: {} with value: {} with error message: {}",
                violation.getPropertyPath(), // Đường dẫn thuộc tính bị vi phạm
                violation.getInvalidValue(),   // Giá trị của thuộc tính bị vi phạm
                violation.getMessage()         // Thông điệp lỗi validation
            )
        );
    }
}
