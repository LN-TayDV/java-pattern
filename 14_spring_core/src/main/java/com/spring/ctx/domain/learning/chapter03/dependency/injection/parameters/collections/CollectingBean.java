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
package com.spring.ctx.domain.learning.chapter03.dependency.injection.parameters.collections;

import com.spring.ctx.domain.learning.chapter03.dependency.injection.parameters.nesting.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component // Đánh dấu lớp là một Spring Bean được quản lý bởi Spring Container.
public class CollectingBean {

    @Autowired
    @Qualifier("list") // Tiêm bean cụ thể (bean `list` được định nghĩa trong cấu hình).
    List<Song> songList1;

    @Autowired // Tiêm tất cả các bean cùng kiểu `Song` vào một danh sách.
    List<Song> songList2;

    public void printCollections() {
        // In ra danh sách bài hát được tiêm bởi `@Qualifier("list")`.
        System.out.println("@Qualifier(\"list\") : ");
        songList1.forEach(s -> System.out.println(s.getTitle()));

        // In ra danh sách bài hát được tiêm tự động mà không dùng `@Qualifier`.
        System.out.println("None @Qualifier(\"list\") : ");
        songList2.forEach(s -> System.out.println(s.getTitle()));
    }
}