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
package com.spring.ctx.domain.chapter05.framework.services.AOP;

import com.spring.ctx.domain.chapter05.usage.poincuts.advisor.SimpleNameMatching.GrammyGuitarist;
import org.aopalliance.aop.Advice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 1/ Tạo Advice
 * 2/ Đăng kí advice vào bean proxy
 * 3/ Sủ dụng Advice
 */
@Configuration // Đánh dấu lớp này là một cấu hình Spring, nơi khai báo và quản lý các bean.
public class AopConfig implements BeanFactoryAware {

    private BeanFactory beanFactory; // Đối tượng BeanFactory để quản lý các bean trong ứng dụng.

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        // Lưu lại tham chiếu đến BeanFactory để sử dụng trong các bean khác.
        this.beanFactory = beanFactory;
    }

    @Bean
    public GrammyGuitarist johnMayer() {
        // Khai báo một bean GrammyGuitarist đại diện cho đối tượng mục tiêu (target) của AOP.
        return new GrammyGuitarist();
    }

    @Bean
    public Advice advice() {
        // Khai báo một bean Advice, là logic xử lý cắt ngang.
        // Ở đây sử dụng AuditAdvice để thực thi logic quanh các phương thức mục tiêu.
        return new AuditAdvice();
    }

    @Bean
    public DefaultPointcutAdvisor advisor() {
        // Khai báo một Advisor kết hợp Advice với Pointcut để định nghĩa phạm vi áp dụng AOP.

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(); // Tạo một Advisor mặc định.
        advisor.setAdvice(advice()); // Thiết lập logic cắt ngang (advice) từ bean advice().

        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        // Sử dụng AspectJExpressionPointcut để chỉ định các phương thức được áp dụng advice.
        pc.setExpression("execution(* talk*(..))");
        // Biểu thức này chỉ định áp dụng AOP cho tất cả các phương thức bắt đầu bằng "talk".
        advisor.setPointcut(pc); // Gán Pointcut vào Advisor.

        return advisor; // Trả về Advisor hoàn chỉnh.
    }

    @Bean
    public GrammyGuitarist proxyOne() {
        // Tạo một proxy cho GrammyGuitarist sử dụng ProxyFactoryBean.
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setProxyTargetClass(true); // Sử dụng proxy dựa trên lớp (CGLIB), không phải giao diện.
        pfb.setTarget(johnMayer()); // Đặt đối tượng mục tiêu là bean johnMayer.
        pfb.setInterceptorNames("advice");
        // Chỉ định interceptor (advice) sẽ được áp dụng, sử dụng tên bean "advice".
        pfb.setBeanFactory(beanFactory); // Thiết lập BeanFactory để quản lý bean này.
        pfb.setFrozen(true);
        // Đóng băng proxy để ngăn thay đổi thêm sau khi cấu hình xong.
        return (GrammyGuitarist) pfb.getObject(); // Trả về proxy dưới dạng GrammyGuitarist.
    }

    @Bean
    public GrammyGuitarist proxyTwo() {
        // Tương tự proxyOne nhưng sử dụng advisor thay vì trực tiếp sử dụng advice.
        ProxyFactoryBean pfb = new ProxyFactoryBean();
        pfb.setProxyTargetClass(true); // Sử dụng proxy dựa trên lớp (CGLIB).
        pfb.setTarget(johnMayer()); // Đặt đối tượng mục tiêu là bean johnMayer.
        pfb.setInterceptorNames("advisor");
        // Chỉ định advisor sẽ được áp dụng, sử dụng tên bean "advisor".
        pfb.setBeanFactory(beanFactory); // Thiết lập BeanFactory.
        pfb.setFrozen(true);
        // Đóng băng proxy để ngăn thay đổi thêm sau khi cấu hình xong.
        return (GrammyGuitarist) pfb.getObject(); // Trả về proxy dưới dạng GrammyGuitarist.
    }

    @Bean
    public Documentarist documentaristOne() {
        // Tạo một bean Documentarist sử dụng proxyOne làm dependency.
        Documentarist documentarist = new Documentarist();
        documentarist.setDep(proxyOne()); // Đặt proxyOne làm đối tượng phụ thuộc.
        return documentarist; // Trả về Documentarist.
    }

    @Bean
    public Documentarist documentaristTwo() {
        // Tạo một bean Documentarist sử dụng proxyTwo làm dependency.
        Documentarist documentarist = new Documentarist();
        documentarist.setDep(proxyTwo()); // Đặt proxyTwo làm đối tượng phụ thuộc.
        return documentarist; // Trả về Documentarist.
    }
}
