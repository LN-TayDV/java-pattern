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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAdviceProgrammatically.detection;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.framework.ProxyFactory;

@Slf4j
public class IntroductionDemo {

    public static void main(String... args) {
        // Tạo đối tượng Contact và thiết lập tên cho đối tượng này.
        Contact target = new Contact();
        target.setName("John Mayer");

        // Tạo Advisor, đây là một phần của AOP (Aspect-Oriented Programming) để bổ sung hành vi cho đối tượng.
        IntroductionAdvisor advisor = new IsModifiedAdvisor();

        // Tạo ProxyFactory, là công cụ để tạo đối tượng proxy với hành vi bổ sung.
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(target); // Đặt đối tượng target (Contact) là đối tượng gốc.
        pf.addAdvisor(advisor); // Thêm Advisor vào ProxyFactory, Advisor sẽ bổ sung hành vi (IsModified).
        pf.setOptimize(true); // Kích hoạt tối ưu hóa đối với proxy (cải thiện hiệu suất).

        // Tạo proxy từ target (Contact) với các hành vi bổ sung từ Advisor.
        Contact proxy = (Contact) pf.getProxy();

        // Lấy interface IsModified từ proxy. Proxy sẽ cung cấp các phương thức của IsModified (ví dụ: isModified()).
        IsModified proxyInterface = (IsModified) proxy;

        // Kiểm tra xem proxy có phải là đối tượng Contact không.
        LOGGER.info("Is Contact? => {} ", (proxy instanceof Contact));

        // Kiểm tra xem proxy có phải là đối tượng IsModified không.
        LOGGER.info("Is IsModified? => {} ", (proxy instanceof IsModified));

        // Kiểm tra xem đối tượng có bị thay đổi (modified) hay không.
        LOGGER.info("Has been modified? => {} ", proxyInterface.isModified());

        // Thiết lập lại tên cho Contact (không thay đổi giá trị nên không bị modified).
        proxy.setName("John Mayer");
        LOGGER.info("Has been modified? => {} ", proxyInterface.isModified());

        // Thay đổi tên của Contact, đây là sự thay đổi thực sự, vì vậy proxy sẽ bị modified.
        proxy.setName("Ben Barnes");
        LOGGER.info("Has been modified? => {} ", proxyInterface.isModified());
    }

}
