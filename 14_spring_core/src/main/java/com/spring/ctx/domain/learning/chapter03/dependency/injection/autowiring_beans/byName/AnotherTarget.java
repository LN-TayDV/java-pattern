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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.byName;

import com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.item.Bar;
import com.spring.ctx.domain.learning.chapter03.dependency.injection.autowiring_beans.item.Foo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Lazy
public class AnotherTarget {

    private static final Logger log = LOGGER;

    // Các thuộc tính được inject thông qua @Autowired
    Foo fooOne;
    Foo fooTwo;
    Bar bar;

    // Phương thức này sẽ được gọi để inject dependency cho fooOne
    @Autowired
    public void setFooOne(@Qualifier("foo") Foo fooOne) {
        log.info(" --> AnotherTarget#setFooOne(Foo) called");
        this.fooOne = fooOne;
    }

    // Phương thức này sẽ được gọi để inject dependency cho fooTwo
    @Autowired
    public void setFooTwo(@Qualifier("anotherFoo") Foo fooTwo) {
        log.info(" --> AnotherTarget#setFooTwo(Foo) called");
        this.fooTwo = fooTwo;
    }

    // Phương thức này sẽ được gọi để inject dependency cho bar
    @Autowired
    public void setBar(Bar bar) {
        log.info(" --> AnotherTarget#setBar(Bar) called");
        this.bar = bar;
    }
}

