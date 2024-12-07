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
package com.spring.ctx.domain.chapter03.dependency.injection.autowiring_beans.byType;

import com.spring.ctx.domain.chapter03.dependency.injection.autowiring_beans.item.Bar;
import com.spring.ctx.domain.chapter03.dependency.injection.autowiring_beans.item.Foo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * When there are no constructors declared, but there are setters annotated with @Autowired,
 * Spring will use them and will identify the beans to be injected based on their type.
 * Listing 3-57 shows a class named AnotherTarget that is pretty similar to the Target class,
 * but the properties are injected using setters.
 */
@Component  // Đánh dấu lớp này là một Spring Bean, nghĩa là Spring sẽ quản lý vòng đời đối tượng của lớp này.
@Lazy  // Đánh dấu đối tượng này sẽ chỉ được khởi tạo khi thực sự cần thiết, giúp giảm thời gian khởi động ứng dụng.
@Slf4j  // Lombok annotation tự động tạo logger có tên là LOGGER để log các thông tin trong lớp này.
public class AnotherTarget {

    // Định nghĩa một logger để ghi lại thông tin log.
    private static final Logger log = LOGGER;

    // Các thuộc tính sẽ được inject thông qua các phương thức setter.
    Foo fooOne;  // Đối tượng fooOne thuộc kiểu Foo.
    Foo fooTwo;  // Đối tượng fooTwo thuộc kiểu Foo.
    Bar bar;     // Đối tượng bar thuộc kiểu Bar.

    // Phương thức setter để inject đối tượng Foo vào fooOne.
    // Annotation @Autowired giúp Spring tự động inject đối tượng Foo vào đây khi tạo đối tượng AnotherTarget.
    @Autowired
    public void setFooOne(Foo fooOne) {
        // Ghi lại thông tin log khi phương thức này được gọi.
        log.info(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;  // Gán đối tượng Foo vào thuộc tính fooOne.
    }

    // Phương thức setter để inject đối tượng Foo vào fooTwo.
    @Autowired
    public void setFooTwo(Foo fooTwo) {
        // Ghi lại thông tin log khi phương thức này được gọi.
        log.info(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;  // Gán đối tượng Foo vào thuộc tính fooTwo.
    }

    // Phương thức setter để inject đối tượng Bar vào bar.
    @Autowired
    public void setBar(Bar bar) {
        // Ghi lại thông tin log khi phương thức này được gọi.
        log.info(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;  // Gán đối tượng Bar vào thuộc tính bar.
    }
}

