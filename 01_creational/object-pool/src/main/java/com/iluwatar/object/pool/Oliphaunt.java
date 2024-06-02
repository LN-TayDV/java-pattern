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

package com.iluwatar.object.pool;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

/**
 * Oliphaunts are expensive to create.
 */

/**
 * Lớp Oliphaunt
 * Lớp Oliphaunt đại diện cho một loại đối tượng cụ thể có thể được quản lý bởi
 * bể.
 * 
 * Các Trường (Fields)
 * 
 * counter: Một biến đếm kiểu AtomicInteger để đếm số lượng đối tượng Oliphaunt
 * được tạo ra.
 * id: Một ID duy nhất cho mỗi đối tượng Oliphaunt.
 * Các Phương thức (Methods)
 * 
 * Oliphaunt: Hàm khởi tạo cho Oliphaunt.
 * 
 * ID của đối tượng được tăng tự động bằng cách sử dụng counter.
 * Gây ra một độ trễ 1 giây (giả lập việc tạo đối tượng đắt đỏ).
 * Nếu xảy ra lỗi gián đoạn, lỗi sẽ được ghi lại bằng logger.
 * getId: Trả về ID của đối tượng Oliphaunt.
 * 
 * toString: Trả về một biểu diễn chuỗi của đối tượng Oliphaunt, bao gồm ID của
 * nó.
 */
@Slf4j
public class Oliphaunt {

    private static final AtomicInteger counter = new AtomicInteger(0);

    private final int id;

    /**
     * Constructor.
     */
    public Oliphaunt() {
        id = counter.incrementAndGet();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("Error occurred: ", e);
        }
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("Oliphaunt id=%d", id);
    }
}
