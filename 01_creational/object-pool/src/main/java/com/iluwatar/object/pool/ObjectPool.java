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

import java.util.HashSet;
import java.util.Set;

/**
 * Generic object pool.
 *
 * @param <T> Type T of Object in the Pool
 */
/**
 * 
 * Lớp ObjectPool
 * Lớp ObjectPool là một lớp trừu tượng tổng quát (generic) quản lý một nhóm các
 * đối tượng kiểu T.
 * Nó đảm bảo rằng các đối tượng được tái sử dụng một cách hiệu quả thay vì được
 * tạo ra và hủy bỏ thường xuyên.
 * 
 * Các Trường (Fields)
 * 
 * available: Một tập hợp các đối tượng hiện có sẵn để sử dụng.
 * inUse: Một tập hợp các đối tượng hiện đang được sử dụng.
 * Các Phương thức (Methods)
 * 
 * create: Một phương thức trừu tượng mà các lớp con cần triển khai để định
 * nghĩa cách thức tạo đối tượng mới.
 * 
 * checkOut: Một phương thức đồng bộ để lấy một đối tượng từ bể (pool).
 * 
 * Nếu không có đối tượng nào có sẵn, một đối tượng mới sẽ được tạo và thêm vào
 * tập hợp available.
 * Một đối tượng có sẵn sẽ được lấy ra khỏi tập hợp, xóa khỏi available, thêm
 * vào inUse, và trả về.
 * checkIn: Một phương thức đồng bộ để trả lại một đối tượng vào bể.
 * 
 * Đối tượng được xóa khỏi inUse và thêm trở lại vào available.
 * 
 * toString: Một phương thức đồng bộ để cung cấp một biểu diễn chuỗi của trạng
 * thái hiện tại của bể,
 * cho thấy số lượng đối tượng có sẵn và đang sử dụng.
 */
public abstract class ObjectPool<T> {

    private final Set<T> available = new HashSet<>();
    private final Set<T> inUse = new HashSet<>();

    protected abstract T create();

    /**
     * Checkout object from pool.
     */
    public synchronized T checkOut() {
        if (available.isEmpty()) {
            available.add(create());
        }
        var instance = available.iterator().next();
        available.remove(instance);
        inUse.add(instance);
        return instance;
    }

    public synchronized void checkIn(T instance) {
        inUse.remove(instance);
        available.add(instance);
    }

    @Override
    public synchronized String toString() {
        return String.format("Pool available=%d inUse=%d", available.size(), inUse.size());
    }
}
