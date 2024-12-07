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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.AspectJExpression;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.PointcutsRgEx.Guitarist;
import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class PointcutsAspectJExpression {

    public static void main(String... args) { // Phương thức chính để chạy chương trình

        // Tạo một đối tượng Guitarist (nghệ sĩ guitar) làm đối tượng mục tiêu
        Guitarist johnMayer = new Guitarist();

        // Tạo một Pointcut sử dụng AspectJExpressionPointcut
        var pc = new AspectJExpressionPointcut();
        // Định nghĩa một biểu thức AspectJ để xác định các phương thức cần áp dụng
        // Ở đây, bất kỳ phương thức nào có tên bắt đầu bằng "sing" sẽ khớp
        pc.setExpression("execution(* sing*(..))");

        // Tạo một Advisor kết hợp giữa Pointcut và Advice
        var advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        // Tạo một ProxyFactory để tạo proxy cho đối tượng mục tiêu
        ProxyFactory pf = new ProxyFactory();

        // Đặt đối tượng mục tiêu (target) cho proxy là "johnMayer"
        pf.setTarget(johnMayer);

        // Thêm Advisor vào ProxyFactory để áp dụng logic tư vấn
        pf.addAdvisor(advisor);

        // Tạo proxy từ ProxyFactory và ép kiểu nó thành "Guitarist"
        Guitarist proxy = (Guitarist) pf.getProxy();

        // Gọi phương thức "sing()" trên proxy
        // Vì tên phương thức bắt đầu bằng "sing", nó khớp với Pointcut và logic trong Advice sẽ được thực thi
        proxy.sing();

        // Gọi phương thức "sing2()" trên proxy
        // Phương thức này cũng bắt đầu bằng "sing", nên cũng khớp với Pointcut và áp dụng logic từ Advice
        proxy.sing2();

        // Gọi phương thức "rest()" trên proxy
        // Vì tên phương thức không bắt đầu bằng "sing", nó không khớp với Pointcut nên không áp dụng logic từ Advice
        proxy.rest();
    }

}
