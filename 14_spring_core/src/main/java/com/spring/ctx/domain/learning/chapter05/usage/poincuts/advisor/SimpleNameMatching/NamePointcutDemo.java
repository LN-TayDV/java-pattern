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
package com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.SimpleNameMatching;

import com.spring.ctx.domain.learning.chapter05.usage.poincuts.advisor.UsingDefaultPointcutAdvisor.SimpleAroundAdvice;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

public class NamePointcutDemo {

    public static void main(String... args) {
        // Tạo một đối tượng "GrammyGuitarist" - đây là đối tượng thực hiện các hành động như "sing", "talk", v.v.
        GrammyGuitarist johnMayer = new GrammyGuitarist();

        // Tạo một đối tượng "NameMatchMethodPointcut" để xác định các phương thức cần áp dụng tư vấn (advice).
        // Ở đây, chúng ta thêm hai phương thức "sing" và "talk" vào danh sách các phương thức sẽ được tư vấn.
        NameMatchMethodPointcut pc = new NameMatchMethodPointcut();
        pc.addMethodName("sing");
        pc.addMethodName("talk");

        // Tạo một "Advisor" kết hợp giữa Pointcut (điều kiện áp dụng) và Advice (logic tư vấn).
        // SimpleAroundAdvice là một lớp logic xử lý sẽ bao quanh (around) các phương thức được chỉ định bởi Pointcut.
        Advisor advisor = new DefaultPointcutAdvisor(pc, new SimpleAroundAdvice());

        // Tạo một ProxyFactory để tạo đối tượng proxy (đại diện cho đối tượng gốc và thêm các hành vi tư vấn).
        ProxyFactory pf = new ProxyFactory();

        // Đặt đối tượng mục tiêu (target) cho proxy - đây là đối tượng mà proxy sẽ đại diện.
        pf.setTarget(johnMayer);

        // Thêm Advisor vào ProxyFactory - điều này xác định rằng proxy sẽ áp dụng logic từ Advisor cho các phương thức chỉ định.
        pf.addAdvisor(advisor);

        // Lấy đối tượng proxy từ ProxyFactory.
        GrammyGuitarist proxy = (GrammyGuitarist) pf.getProxy();

        // Gọi phương thức "sing" trên đối tượng proxy.
        // Nếu phương thức này khớp với Pointcut, logic từ SimpleAroundAdvice sẽ được áp dụng.
        proxy.sing();

        // Gọi phương thức "sing" có tham số trên đối tượng proxy.
        // Phương thức này không được chỉ định trong Pointcut nên logic tư vấn sẽ không áp dụng.
        proxy.sing(new GrammyGuitarist.Guitar());

        // Gọi phương thức "rest" trên đối tượng proxy.
        // Phương thức này cũng không khớp với Pointcut nên không áp dụng tư vấn.
        proxy.rest();

        // Gọi phương thức "talk" trên đối tượng proxy.
        // Vì "talk" đã được thêm vào Pointcut nên logic tư vấn sẽ áp dụng cho phương thức này.
        proxy.talk();
    }

}
