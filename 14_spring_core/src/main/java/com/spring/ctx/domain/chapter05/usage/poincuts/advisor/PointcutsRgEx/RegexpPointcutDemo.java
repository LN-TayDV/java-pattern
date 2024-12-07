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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.PointcutsRgEx;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;

public class RegexpPointcutDemo {

    public static void main(String... args) { // Phương thức chính để chạy chương trình

        // Tạo một đối tượng Guitarist (nghệ sĩ guitar)
        Guitarist johnMayer = new Guitarist();

        // Tạo một đối tượng "JdkRegexpMethodPointcut" để xác định các phương thức sẽ bị áp dụng tư vấn
        // Sử dụng biểu thức chính quy để khớp với các phương thức có tên chứa "sing"
        var pc = new JdkRegexpMethodPointcut();
        pc.setPattern(".*sing.*"); // Mẫu khớp: bất kỳ phương thức nào có "sing" trong tên

        // Tạo một Advisor kết hợp giữa Pointcut (điều kiện khớp) và Advice (logic xử lý tư vấn)
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        // Tạo một ProxyFactory để tạo proxy cho đối tượng mục tiêu
        ProxyFactory pf = new ProxyFactory();

        // Đặt đối tượng mục tiêu (target) cho proxy là "johnMayer"
        pf.setTarget(johnMayer);

        // Thêm Advisor vào ProxyFactory - điều này sẽ thêm logic tư vấn vào các phương thức khớp
        pf.addAdvisor(advisor);

        // Tạo proxy từ ProxyFactory và ép kiểu nó thành "Guitarist"
        Guitarist proxy = (Guitarist) pf.getProxy();

        // Gọi phương thức "sing()" trên proxy
        // Vì tên phương thức "sing" khớp với biểu thức chính quy, logic trong Advice sẽ được áp dụng
        proxy.sing();

        // Gọi phương thức "sing2()" trên proxy
        // Phương thức này cũng chứa "sing" trong tên, nên cũng khớp với Pointcut và sẽ áp dụng logic từ Advice
        proxy.sing2();

        // Gọi phương thức "rest()" trên proxy
        // Vì phương thức này không chứa "sing" trong tên, nó không khớp với Pointcut nên logic từ Advice sẽ không áp dụng
        proxy.rest();
    }

}
