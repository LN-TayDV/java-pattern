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

import com.spring.ctx.domain.chapter11.validation.validation.constraints.JakartaValidationCfg;
import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerDomain;
import com.spring.ctx.domain.chapter11.validation.validation.constraints.SingerValidationService;
import jakarta.validation.ConstraintViolation;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Đánh dấu lớp này là một lớp sử dụng SLF4J để ghi log
@Slf4j
public class JakartaValidationTest {

    public static void main(String[] args) {
        // Gọi phương thức kiểm tra validate của singer
        testCountrySingerTwoValidation();
    }

    // Phương thức kiểm tra xác nhận tính hợp lệ của đối tượng SingerDomain
    static void testCountrySingeValidation() {
        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {

            // Lấy bean SingerValidationService từ context Spring
            var singerBeanValidationService = ctx.getBean(SingerValidationService.class);

            // Tạo một đối tượng SingerDomain và thiết lập các thuộc tính
            SingerDomain singer = new SingerDomain();
            singer.setFirstName("John");  // Thiết lập tên
            singer.setLastName("Mayer");  // Thiết lập họ
            singer.setGenre(SingerDomain.Genre.COUNTRY); // Thiết lập thể loại nhạc
            singer.setGender(null); // Không thiết lập giới tính (sẽ gây lỗi)

            // Kiểm tra các vi phạm khi validate đối tượng singer
            var violations = singerBeanValidationService.validateSinger(singer);
            // Ghi thông tin số lỗi vi phạm (kỳ vọng có 2 lỗi)
            LOGGER.info(" assertEquals : {}", 2 == violations.size());

            // Liệt kê chi tiết các lỗi vi phạm
            listViolations(violations);
        }
    }

    // Phương thức kiểm tra xác nhận tính hợp lệ của đối tượng SingerDomainTwo
    static void testCountrySingerTwoValidation() {
        try (var ctx = new AnnotationConfigApplicationContext(JakartaValidationCfg.class)) {
            // Lấy bean SingerTwoValidationService từ context Spring
            var singerBeanValidationService = ctx.getBean(SingerTwoValidationService.class);

            // Tạo một đối tượng SingerDomainTwo và thiết lập các thuộc tính
            var singer = new SingerDomainTwo();
            singer.setFirstName("John");  // Thiết lập tên
            singer.setLastName("Mayer");  // Thiết lập họ
            singer.setGenre(SingerDomain.Genre.COUNTRY); // Thiết lập thể loại nhạc
            singer.setGender(null); // Không thiết lập giới tính (sẽ gây lỗi)

            // Kiểm tra các vi phạm khi validate đối tượng singer
            var violations = singerBeanValidationService.validateSinger(singer);
            // Ghi thông tin số lỗi vi phạm (kỳ vọng có 1 lỗi)
            LOGGER.info(" assertEquals : {}", 1 == violations.size());

            // Ghi chi tiết các lỗi vi phạm cho từng thuộc tính
            violations.forEach(violation ->
                LOGGER.info(
                    "Validation error for property: {} with value: {} with error message: {}",
                    violation.getPropertyPath(), // Đường dẫn thuộc tính bị lỗi
                    violation.getInvalidValue(),  // Giá trị không hợp lệ
                    violation.getMessage()        // Thông điệp lỗi
                )
            );
        }
    }

    // Phương thức để liệt kê tất cả các lỗi vi phạm trong quá trình xác nhận
    private static void listViolations(Set<ConstraintViolation<SingerDomain>> violations) {
        violations.forEach(violation ->
            LOGGER.info(
                "Validation error for property: {} with value: {} with error message: {}",
                violation.getPropertyPath(), // Đường dẫn thuộc tính bị lỗi
                violation.getInvalidValue(),  // Giá trị không hợp lệ
                violation.getMessage()        // Thông điệp lỗi
            )
        );
    }
}
