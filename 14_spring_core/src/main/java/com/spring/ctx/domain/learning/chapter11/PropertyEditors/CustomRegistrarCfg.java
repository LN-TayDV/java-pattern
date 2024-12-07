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

package com.spring.ctx.domain.learning.chapter11.PropertyEditors;

import com.spring.ctx.domain.learning.chapter11.AppConfig;
import java.time.LocalDate;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter11.PropertyEditors") // Quét thư mục chứa các editor
@Import(AppConfig.class) // Nhập cấu hình từ AppConfig
public class CustomRegistrarCfg {

    // Định nghĩa bean PropertyEditorRegistrar để đăng ký editor tùy chỉnh cho LocalDate
    @Bean
    public PropertyEditorRegistrar registrar() {
        return registry -> {
            // Đăng ký LocalDatePropertyEditor cho kiểu dữ liệu LocalDate
            registry.registerCustomEditor(LocalDate.class, new LocalDatePropertyEditor());
        };
    }

    // Định nghĩa bean CustomEditorConfigurer để cấu hình sử dụng các PropertyEditor
    @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        var cus = new CustomEditorConfigurer(); // Tạo đối tượng CustomEditorConfigurer

        var registrars = new PropertyEditorRegistrar[1]; // Khởi tạo mảng PropertyEditorRegistrar

        registrars[0] = registrar(); // Đăng ký PropertyEditorRegistrar vào mảng

        cus.setPropertyEditorRegistrars(registrars); // Cấu hình registrar cho CustomEditorConfigurer

        return cus; // Trả về CustomEditorConfigurer
    }
}

