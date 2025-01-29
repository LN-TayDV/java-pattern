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
import com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.types.Singer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import java.lang.reflect.Method;

@Slf4j
public class DynamicMethodMatcherPointcutMain {

    public static void main(String... args) { // Phương thức chính để chạy chương trình

        // Tạo đối tượng "GoodGuitarist" làm đối tượng mục tiêu
        GoodGuitarist target = new GoodGuitarist();

        // Tạo một Advisor kết hợp giữa Pointcut động (SimpleDynamicPointcut) và Advice (SimpleAroundAdvice)
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointcut(), new SimpleAroundAdvice());

        // Tạo một ProxyFactory để tạo proxy cho đối tượng mục tiêu
        ProxyFactory pf = new ProxyFactory();

        // Đặt đối tượng mục tiêu (target) cho proxy là "target" (GoodGuitarist)
        pf.setTarget(target);

        // Thêm Advisor vào ProxyFactory - logic tư vấn sẽ được áp dụng cho các phương thức khớp với Pointcut
        pf.addAdvisor(advisor);

        // Tạo proxy từ ProxyFactory và ép kiểu nó thành giao diện "Singer"
        Singer proxy = (Singer) pf.getProxy();

        // Gọi phương thức "sing(String)" với các giá trị tham số khác nhau
        proxy.sing("C"); // Khớp với Pointcut động nếu điều kiện được đáp ứng
        proxy.sing("c"); // Xem xét có khớp với Pointcut động hay không
        proxy.sing("E"); // Tương tự, kiểm tra điều kiện của Pointcut động
        proxy.sing();    // Phương thức này không có tham số, cần kiểm tra xem có bị áp dụng tư vấn không
    }


    static class SimpleDynamicPointcut extends DynamicMethodMatcherPointcut {

        @Override
        public ClassFilter getClassFilter() {
            return  cls -> (cls == GoodGuitarist.class);
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return ("sing".equals(method.getName()));
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            LOGGER.info("Dynamic check for " + method.getName());

            if(args.length == 0) {
                return false;
            }

            var key = (String) args[0];

            return key.equalsIgnoreCase("C");
        }
    }
}
