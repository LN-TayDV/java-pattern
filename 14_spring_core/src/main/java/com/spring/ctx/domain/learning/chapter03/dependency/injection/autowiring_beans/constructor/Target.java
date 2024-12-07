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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component  // Đánh dấu lớp này là một Spring Bean, nghĩa là Spring sẽ quản lý vòng đời đối tượng của lớp này.
@Lazy  // Đánh dấu đối tượng này sẽ chỉ được khởi tạo khi thực sự cần thiết, giúp giảm thời gian khởi động ứng dụng.
public class Target {

    // Tạo một đối tượng logger để ghi log vào file.
    private static final Logger logger = LoggerFactory.getLogger(Target.class);

    // Các thuộc tính mà Spring sẽ inject vào đối tượng này.
    Foo fooOne;  // Thuộc tính fooOne kiểu Foo.
    Foo fooTwo;  // Thuộc tính fooTwo kiểu Foo.
    Bar bar;     // Thuộc tính bar kiểu Bar.

    // Constructor mặc định, được gọi khi Spring khởi tạo đối tượng mà không có tham số.
    public Target() {
        logger.info(" --> Target() called");  // Ghi log khi constructor mặc định được gọi.
    }

    // Constructor có tham số Foo, khi Spring tạo đối tượng Target với tham số là đối tượng Foo.
    public Target(Foo foo) {
        this.fooOne = foo;  // Gán giá trị cho thuộc tính fooOne.
        logger.info(" --> Target(Foo) called");  // Ghi log khi constructor này được gọi.
    }

    // Constructor có tham số Foo và Bar, khi Spring tạo đối tượng Target với tham số là đối tượng Foo và Bar.
    public Target(Foo foo, Bar bar) {
        this.fooOne = foo;  // Gán giá trị cho thuộc tính fooOne.
        this.bar = bar;     // Gán giá trị cho thuộc tính bar.
        logger.info(" --> Target(Foo, Bar) called");  // Ghi log khi constructor này được gọi.
    }

    // Phương thức setter cho fooOne, sẽ được gọi khi Spring inject đối tượng Foo vào.
    @Autowired
    public void setFooOne(Foo fooOne) {
        logger.info(" --> Target#setFooOne(Foo) called");  // Ghi log khi phương thức này được gọi.
        this.fooOne = fooOne;  // Gán giá trị cho thuộc tính fooOne.
    }

    // Phương thức setter cho fooTwo, sẽ được gọi khi Spring inject đối tượng Foo vào.
    @Autowired
    public void setFooTwo(Foo fooTwo) {
        logger.info(" --> Target#setFooTwo(Foo) called");  // Ghi log khi phương thức này được gọi.
        this.fooTwo = fooTwo;  // Gán giá trị cho thuộc tính fooTwo.
    }

    // Phương thức setter cho bar, sẽ được gọi khi Spring inject đối tượng Bar vào.
    @Autowired
    public void setBar(Bar bar) {
        logger.info(" --> Target#setBar(Bar) called");  // Ghi log khi phương thức này được gọi.
        this.bar = bar;  // Gán giá trị cho thuộc tính bar.
    }

    // Lớp Foo được đánh dấu là @Component để Spring quản lý nó như một Spring Bean.
    @Component
    static class Foo {
        // Thuộc tính id của Foo, sẽ tự động sinh một UUID và rút gọn nó để tạo một giá trị id duy nhất.
        String id = UUID.randomUUID().toString().replace("-","").substring(0,8);
    }

    // Lớp Bar cũng được đánh dấu là @Component để Spring quản lý nó như một Spring Bean.
    @Component
    static class Bar {}
}
