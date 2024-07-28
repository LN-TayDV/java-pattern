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
package com.spring.ctx.domain.chapter03.parameters.injection.nesting;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ContextNestingDemo {

    public static void main(String... args) {
        var parentCtx = new AnnotationConfigApplicationContext();
        parentCtx.register(ParentConfig.class);
        parentCtx.refresh();

        var childCtx = new AnnotationConfigApplicationContext();
        childCtx.register(ChildConfig.class);
        childCtx.setParent(parentCtx);
        childCtx.refresh();

        Song song1 = (Song) childCtx.getBean("song1");
        Song song2 = (Song) childCtx.getBean("song2");
        Song song3 = (Song) childCtx.getBean("song3");

        System.out.println("from parent ctx: " + song1.getTitle());
        System.out.println("from child ctx: " + song2.getTitle());
        System.out.println("from child ctx: " + song3.getTitle());
    }

}
