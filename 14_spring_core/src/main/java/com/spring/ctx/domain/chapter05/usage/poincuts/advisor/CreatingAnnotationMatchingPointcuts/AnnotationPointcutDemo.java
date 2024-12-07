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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAnnotationMatchingPointcuts;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching.GrammyGuitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

public class AnnotationPointcutDemo {

    public static void main(String... args) {
        // Tạo một đối tượng AnnotatedGuitarist (đối tượng mục tiêu).
        var johnMayer = new AnnotatedGuitarist();

        // Tạo một Pointcut để khớp với các phương thức có annotation @AdviceRequired.
        var pc = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);

        // Tạo một Advisor kết hợp Pointcut và Advice (logic xử lý cắt ngang).
        var advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        // Tạo một ProxyFactory để tạo proxy.
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(johnMayer); // Đặt đối tượng mục tiêu là johnMayer.
        pf.addAdvisor(advisor); // Thêm Advisor để áp dụng AOP logic.

        // Tạo proxy từ ProxyFactory và ép kiểu về AnnotatedGuitarist.
        AnnotatedGuitarist proxy = (AnnotatedGuitarist) pf.getProxy();

        // Gọi các phương thức của proxy:
        proxy.sing(); // Gọi phương thức sing() (nếu có annotation @AdviceRequired thì áp dụng Advice).
        proxy.sing(new GrammyGuitarist.Guitar()); // Gọi phương thức sing(Guitar).
        proxy.rest(); // Gọi phương thức rest() (không bị áp dụng Advice vì không khớp Pointcut).
    }


}
