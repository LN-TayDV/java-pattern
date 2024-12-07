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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.pickle;

import com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.item.Bar;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@AllArgsConstructor  // Lombok annotation để tự động tạo constructor có tham số cho tất cả các thuộc tính của lớp.
@Setter  // Lombok annotation để tự động tạo phương thức setter cho tất cả các thuộc tính.
@Slf4j  // Lombok annotation để tự động tạo logger cho lớp.
public class TrickyTarget {

    // Tạo một đối tượng logger để ghi log vào file.
    private static final Logger log = LOGGER;

    // Các thuộc tính mà Spring sẽ inject vào đối tượng này.
    Foo fooOne;  // Thuộc tính fooOne kiểu Foo.
    Foo fooTwo;  // Thuộc tính fooTwo kiểu Foo.
    Bar bar;     // Thuộc tính bar kiểu Bar.

    // Constructor mặc định, được gọi khi Spring khởi tạo đối tượng mà không có tham số.
    public TrickyTarget() {
        log.info(" --> TrickyTarget() called");  // Ghi log khi constructor mặc định được gọi.
    }

    // Phương thức setter cho fooOne, được gọi khi Spring inject đối tượng Foo có tên "fooImplOne" vào.
    @Autowired  // Spring sẽ tự động inject đối tượng vào thuộc tính fooOne.
    @Qualifier("fooImplOne")  // Chỉ định rõ ràng rằng đối tượng fooImplOne sẽ được inject vào.
    public void setFooOne(Foo fooOne) {
        this.fooOne = fooOne;  // Gán giá trị cho thuộc tính fooOne.
        log.info(" --> Property fooOne set");  // Ghi log khi thuộc tính fooOne được set giá trị.
    }

    // Phương thức setter cho fooTwo, được gọi khi Spring inject đối tượng Foo có tên "fooImplTwo" vào.
    @Autowired  // Spring sẽ tự động inject đối tượng vào thuộc tính fooTwo.
    @Qualifier("fooImplTwo")  // Chỉ định rõ ràng rằng đối tượng fooImplTwo sẽ được inject vào.
    public void setFooTwo(Foo foo) {
        this.fooTwo = foo;  // Gán giá trị cho thuộc tính fooTwo.
        log.info(" --> Property fooTwo set");  // Ghi log khi thuộc tính fooTwo được set giá trị.
    }

    // Phương thức setter cho bar, được gọi khi Spring inject đối tượng Bar vào.
    @Autowired  // Spring sẽ tự động inject đối tượng vào thuộc tính bar.
    public void setBar(Bar bar) {
        this.bar = bar;  // Gán giá trị cho thuộc tính bar.
        log.info(" --> Property bar set");  // Ghi log khi thuộc tính bar được set giá trị.
    }
}

