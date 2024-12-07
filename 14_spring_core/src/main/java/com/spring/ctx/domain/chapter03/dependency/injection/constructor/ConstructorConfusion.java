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
package com.spring.ctx.domain.chapter03.dependency.injection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component // Đánh dấu lớp này là một Spring Bean, để Spring có thể quản lý và khởi tạo đối tượng của lớp này.
public class ConstructorConfusion {

    private final String someValue;  // Thuộc tính để lưu trữ giá trị chuỗi, sẽ được gán từ constructor.

    /**
     * Constructor này nhận vào một tham số kiểu String và gán giá trị này cho thuộc tính someValue.
     * Khi Spring khởi tạo đối tượng này bằng constructor này, thông báo sẽ được in ra.
     * Tuy nhiên, constructor này không có annotation @Autowired, vì vậy Spring sẽ không sử dụng constructor này để khởi tạo bean mà thay vào đó sẽ sử dụng constructor có annotation @Autowired.
     */
    public ConstructorConfusion(String someValue) {
        System.out.println("ConstructorConfusion(String) called");
        this.someValue = someValue;
    }

    /**
     * Constructor này được đánh dấu với annotation @Autowired, điều này nói với Spring rằng Spring sẽ sử dụng constructor này để khởi tạo bean.
     * @Value("90") cung cấp giá trị tĩnh cho tham số someValue trong constructor này, giá trị này sẽ là "90".
     * Nếu không có annotation @Autowired, Spring không thể quyết định được constructor nào sẽ được gọi khi tạo bean và sẽ gây ra lỗi.
     */
    @Autowired
    public ConstructorConfusion(@Value("90") int someValue) {
        System.out.println("ConstructorConfusion(int) called");

        // Chuyển đổi giá trị int sang chuỗi và gán cho thuộc tính someValue.
        this.someValue = "Number: " + someValue;
    }

    /**
     * Phương thức main() là điểm khởi đầu của chương trình. Nó sẽ khởi tạo Spring application context,
     * đăng ký lớp ConstructorConfusion như một bean và khởi tạo bean này.
     * Sau đó, nó in thông tin của bean ConstructorConfusion vừa được khởi tạo.
     */
    public static void main(String... args) {
        // Tạo một Spring context sử dụng AnnotationConfigApplicationContext
        var ctx = new AnnotationConfigApplicationContext();

        // Đăng ký lớp ConstructorConfusion vào Spring context
        ctx.register(ConstructorConfusion.class);

        // Khởi động Spring context
        ctx.refresh();

        // Lấy bean ConstructorConfusion từ Spring context
        var cc = ctx.getBean(ConstructorConfusion.class);

        // In thông tin của bean ConstructorConfusion
        System.out.println("Does this work? " + cc);
    }

    /**
     * Phương thức này trả về giá trị của thuộc tính someValue,
     * giúp hiển thị thông tin về đối tượng khi gọi print.
     */
    public String toString() {
        return someValue;
    }
}
