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
package com.spring.ctx.domain.chapter05.usage.poincuts.advisor.CreatingAdviceProgrammatically;

import static java.time.Duration.ofMillis;

import jakarta.annotation.Nonnull;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.util.StopWatch;

@Slf4j
public class Main  implements Runnable {

    public static void main(String... args) {
        var test = new Main();
        test.run();
    }

    @Override
    public void run() {
        Concert concert = new Concert();
        ProxyFactory pf = new ProxyFactory();
        //1
        pf.addAdvice(new SimpleBeforeAdvice());
        //2
        pf.addAdvice(new SimpleAroundAdvice());
        //4
        pf.addAdvice(new SimpleAfterAdvice());
        pf.setTarget(concert);
        Performance proxy = (Performance) pf.getProxy();
        //3
        proxy.execute();
        //5 SimpleAroundAdvice.invoke
    }


    public class SimpleBeforeAdvice implements MethodBeforeAdvice {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            LOGGER.info("Before: set up concert hall.");
        }
    }

    public class SimpleAfterAdvice implements AfterReturningAdvice {

        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
            LOGGER.info("After: offer standing invocation .");
        }
    }

    public class SimpleAroundAdvice implements MethodInterceptor {

        @Override
        public Object invoke(@Nonnull MethodInvocation invocation) throws Throwable {
            LOGGER.info("Around: starting timer");

            StopWatch sw = new StopWatch();

            sw.start(invocation.getMethod().getName());

            Object returnValue = invocation.proceed();

            sw.stop();

            LOGGER.info("Around: concert duration = {}", sw.getTotalTimeMillis());

            return returnValue;
        }
    }

    public interface Performance {
        void execute();
    }

    public class Concert implements Performance {

        @Override
        public void execute() {
            LOGGER.info(" ... La la la la laaaa ...");
            try {
                Thread.sleep(ofMillis(2000).toMillis());
            }catch (InterruptedException e) {

            }
        }
    }

}
