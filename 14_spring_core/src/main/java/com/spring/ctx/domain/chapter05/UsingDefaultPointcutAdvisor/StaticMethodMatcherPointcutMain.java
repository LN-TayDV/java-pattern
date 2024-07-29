package com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor;

import com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor.types.GoodGuitarist;
import com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor.types.GreatGuitarist;
import com.spring.ctx.domain.chapter05.UsingDefaultPointcutAdvisor.types.Singer;
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
        // Tạo các đối tượng GoodGuitarist và GreatGuitarist
        GoodGuitarist johnMayer = new GoodGuitarist();
        GreatGuitarist ericClapton = new GreatGuitarist();

        // Khai báo các biến proxy cho giao diện Singer
        Singer proxyOne;
        Singer proxyTwo;

        // Tạo một Pointcut để khớp với các phương thức theo tiêu chí nhất định
        Pointcut pc = new SimpleStaticPointcut();
        // Tạo một Advice chứa logic xử lý chéo để áp dụng
        Advice advice = new SimpleAroundAdvice();
        // Tạo một Advisor kết hợp Pointcut và Advice
        Advisor advisor = new DefaultPointcutAdvisor(pc, advice);

        // Tạo một ProxyFactory để tạo các proxy
        ProxyFactory pf = new ProxyFactory();
        // Thêm Advisor vào ProxyFactory
        pf.addAdvisor(advisor);
        // Đặt đối tượng đích của proxy là johnMayer (GoodGuitarist)
        pf.setTarget(johnMayer);
        // Tạo proxy và ép kiểu nó thành giao diện Singer
        proxyOne = (Singer) pf.getProxy();

        // Tạo một ProxyFactory mới cho proxy tiếp theo
        pf = new ProxyFactory();
        // Thêm cùng một Advisor vào ProxyFactory mới
        pf.addAdvisor(advisor);
        // Đặt đối tượng đích của proxy là ericClapton (GreatGuitarist)
        pf.setTarget(ericClapton);
        // Tạo proxy và ép kiểu nó thành giao diện Singer
        proxyTwo = (Singer) pf.getProxy();

        // Gọi phương thức sing() trên proxyOne
        proxyOne.sing();
        // Gọi phương thức sing() trên proxyTwo
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
