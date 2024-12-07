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
package com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor;

import com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.GoodGuitarist;
import com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.GreatGuitarist;
import com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import java.lang.reflect.Method;

public class StaticMethodMatcherPointcutMain {

    /**
     * Đoạn mã này tạo các proxy cho hai đối tượng GoodGuitarist và GreatGuitarist,
     * nhưng chỉ áp dụng Advice cho phương thức sing của GoodGuitarist.
     * Khi gọi phương thức sing trên proxyOne (proxy của johnMayer), Advice sẽ được áp dụng.
     * Tuy nhiên, khi gọi sing trên proxyTwo (proxy của ericClapton),
     * Advice sẽ không được áp dụng vì GreatGuitarist không khớp với ClassFilter.
     */

    public static void main(String... args) { // Phương thức chính để chạy chương trình
        // Tạo các đối tượng "GoodGuitarist" (nghệ sĩ guitar tốt) và "GreatGuitarist" (nghệ sĩ guitar xuất sắc)
        GoodGuitarist johnMayer = new GoodGuitarist();
        GreatGuitarist ericClapton = new GreatGuitarist();

        // Khai báo các biến "proxyOne" và "proxyTwo" để lưu trữ các proxy của giao diện "Singer"
        Singer proxyOne;
        Singer proxyTwo;

        // Tạo một Pointcut ("SimpleStaticPointcut") để xác định các phương thức cần được áp dụng logic tư vấn.
        Pointcut pc = new SimpleStaticPointcut();

        // Tạo một Advice ("SimpleAroundAdvice") chứa logic xử lý sẽ được áp dụng trước hoặc sau các phương thức được chỉ định.
        Advice advice = new SimpleAroundAdvice();

        // Kết hợp Pointcut và Advice để tạo thành một Advisor ("DefaultPointcutAdvisor")
        // Advisor giúp xác định phương thức nào sẽ được áp dụng logic tư vấn và cách áp dụng nó.
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        // Tạo một ProxyFactory để tạo proxy cho đối tượng đầu tiên (johnMayer)
        ProxyFactory pf = new ProxyFactory();

        // Thêm Advisor vào ProxyFactory - đây là logic tư vấn sẽ được áp dụng cho các phương thức khớp với Pointcut.
        pf.addAdvisor(advisor);

        // Đặt đối tượng mục tiêu (target) là "johnMayer"
        pf.setTarget(johnMayer);

        // Tạo proxy từ ProxyFactory và ép kiểu proxy thành giao diện "Singer"
        proxyOne = (Singer) pf.getProxy();

        // Tạo một ProxyFactory mới cho đối tượng thứ hai (ericClapton)
        pf = new ProxyFactory();

        // Thêm cùng một Advisor vào ProxyFactory này (sử dụng lại logic tư vấn đã định nghĩa trước đó).
        pf.addAdvisor(advisor);

        // Đặt đối tượng mục tiêu (target) là "ericClapton"
        pf.setTarget(ericClapton);

        // Tạo proxy từ ProxyFactory và ép kiểu proxy thành giao diện "Singer"
        proxyTwo = (Singer) pf.getProxy();

        // Gọi phương thức "sing()" trên "proxyOne" (proxy của johnMayer).
        // Nếu phương thức "sing()" khớp với Pointcut, logic trong Advice sẽ được áp dụng.
        proxyOne.sing();

        // Gọi phương thức "sing()" trên "proxyTwo" (proxy của ericClapton).
        // Tương tự, logic trong Advice sẽ được áp dụng nếu phương thức "sing()" khớp với Pointcut.
        proxyTwo.sing();
    }


    // Lớp SimpleStaticPointcut mở rộng từ StaticMethodMatcherPointcut
    static class SimpleStaticPointcut extends StaticMethodMatcherPointcut {
        @Override
        public boolean matches(Method method, Class<?> cls) {
            // Khớp nếu tên phương thức là "sing"
            return ("sing".equals(method.getName()));
        }

        @Override
        public ClassFilter getClassFilter() {
            // Chỉ khớp với lớp GoodGuitarist
            return cls -> (cls == GoodGuitarist.class);
        }
    }

}
