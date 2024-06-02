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

package com.iluwatar.singleton.on.demand;

/**
 * <p>Lớp Singleton an toàn với luồng.
 * Thể hiện (instance) được khởi tạo một cách lười biếng và do đó cần cơ chế đồng bộ hóa.</p>
 */
public final class ThreadSafeLazyLoadedIvoryTower {

    /**
     * Thể hiện Singleton của lớp, được khai báo là volatile để đảm bảo truy cập nguyên tử bởi nhiều luồng.
     *
     * `private` chỉ ra rằng biến chỉ có thể được truy cập bên trong lớp.
     * `static` biến này thuộc về lớp, không phải đối tượng của lớp.
     * `volatile` đảm bảo mọi thay đổi đối với biến này sẽ được nhìn thấy ngay lập tức bởi tất cả các luồng.
     */
    private static volatile ThreadSafeLazyLoadedIvoryTower instance;

    /**
     * Constructor riêng tư để ngăn chặn việc khởi tạo từ bên ngoài lớp.
     * Ngăn chặn việc tạo đối tượng từ bên ngoài lớp để đảm bảo chỉ có một thể hiện duy nhất.
     * Kiểm tra `instance` để bảo vệ chống lại việc khởi tạo thông qua reflection.
     * Nếu `instance` đã tồn tại, ném ra ngoại lệ `IllegalStateException`.
     */
    private ThreadSafeLazyLoadedIvoryTower() {
        if (instance != null) {
            throw new IllegalStateException("Đã được khởi tạo.");
        }
    }

    /**
     * Thể hiện không được tạo ra cho đến khi phương thức này được gọi lần đầu tiên.
     * `public static synchronized` để đảm bảo phương thức có thể được truy cập từ bất kỳ đâu và
     * chỉ một luồng có thể thực thi nó tại một thời điểm.
     *
     * Kiểm tra `instance`, nếu nó là `null`, tạo một thể hiện mới và gán cho `instance`.
     *
     * Trả về thể hiện duy nhất của lớp.
     */
    public static synchronized ThreadSafeLazyLoadedIvoryTower getInstance() {
        if (instance == null) {
            instance = new ThreadSafeLazyLoadedIvoryTower();
        }
        return instance;
    }
}
