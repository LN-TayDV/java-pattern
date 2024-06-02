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

package com.iluwatar.singleton.lazily;

/**
 * <p>Triển khai Singleton theo Bill Pugh.</p>
 *
 * <p>Triển khai này của mẫu thiết kế singleton tận dụng các đảm bảo của mô hình bộ nhớ Java về khởi tạo lớp.
 * Mỗi lớp chỉ được khởi tạo một lần, khi nó được sử dụng lần đầu tiên. Nếu lớp chưa được sử dụng, nó sẽ không
 * được tải vào bộ nhớ và không có bộ nhớ nào sẽ được cấp phát cho một thể hiện tĩnh. Điều này làm cho thể hiện
 * singleton được tải lười (lazy-loaded) và an toàn với luồng.</p>
 */
public final class BillPughImplementation {

    /**
     * Constructor riêng tư để ngăn chặn việc khởi tạo từ bên ngoài lớp.
     *
     * Ngăn chặn việc tạo đối tượng từ bên ngoài lớp để đảm bảo chỉ có một thể hiện duy nhất.
     */
    private BillPughImplementation() {
        // constructor riêng tư
    }

    /**
     * Phương thức công khai để truy cập thể hiện singleton.
     *
     * <p>
     * Khi phương thức này được gọi, InstanceHolder sẽ được tải vào bộ nhớ và tạo ra thể hiện Singleton.
     * Phương thức này cung cấp một điểm truy cập toàn cục cho thể hiện singleton.
     * </p>
     *
     * @return thể hiện của lớp.
     */
    // điểm truy cập toàn cục
    public static BillPughImplementation getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * InstanceHolder là một lớp tĩnh bên trong, và nó giữ thể hiện Singleton.
     * Nó không được tải vào bộ nhớ cho đến khi phương thức getInstance() được gọi.
     */
    private static class InstanceHolder {
        /**
         * Thể hiện Singleton của lớp.
         *
         * Biến tĩnh này được khởi tạo khi InstanceHolder được tải vào bộ nhớ, đảm bảo rằng chỉ có một
         * thể hiện duy nhất của BillPughImplementation được tạo ra.
         */
        private static final BillPughImplementation instance = new BillPughImplementation();
    }
}

