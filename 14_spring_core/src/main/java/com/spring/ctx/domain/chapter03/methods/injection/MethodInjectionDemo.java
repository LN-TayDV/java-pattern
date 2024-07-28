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
package com.spring.ctx.domain.chapter03.methods.injection;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.StopWatch;

public class MethodInjectionDemo {

    public static void main(String... args) {
        var ctx = new AnnotationConfigApplicationContext(LookupConfig.class);

        var abstractLockOpener = ctx.getBean("abstractLockOpener", LockOpener.class);
        var standardLockOpener = ctx.getBean("standardLockOpener", LockOpener.class);

        displayInfo("abstractLockOpener", abstractLockOpener);
        displayInfo("standardLockOpener", standardLockOpener);
    }

    public static void displayInfo(String beanName, LockOpener lockOpener) {
        KeyHelper keyHelperOne = lockOpener.getMyKeyOpener();
        KeyHelper keyHelperTwo = lockOpener.getMyKeyOpener();

        System.out.println("[" + beanName + "]: KeyHelper Instances the Same? " + (keyHelperOne == keyHelperTwo));

        StopWatch stopWatch = new StopWatch();
        stopWatch.start("lookupDemo");
        for (int x = 0; x < 100_000; x++) {
            KeyHelper keyHelper = lockOpener.getMyKeyOpener();
            keyHelper.open();
        }
        stopWatch.stop();
        System.out.println("100000 gets took " + stopWatch.getTotalTimeMillis() + " ms");
    }
}
