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
 * <p>Double check locking.</p>
 *
 * <p>http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html</p>
 *
 * <p>Broken under Java 1.4.</p>
 */
public final class ThreadSafeDoubleCheckLocking {

    /**
     * Thể hiện Singleton của lớp, được khai báo là volatile để đảm bảo truy cập nguyên tử bởi nhiều luồng.
     *
     * `private` chỉ ra rằng biến chỉ có thể được truy cập bên trong lớp.
     * `static` biến này thuộc về lớp, không phải đối tượng của lớp.
     * `volatile` đảm bảo mọi thay đổi đối với biến này sẽ được nhìn thấy ngay lập tức bởi tất cả các luồng.
     */
    private static volatile ThreadSafeDoubleCheckLocking instance;

    /**
     * Constructor riêng tư để ngăn chặn việc khởi tạo từ bên ngoài lớp.
     *
     * Ngăn chặn việc tạo đối tượng từ bên ngoài lớp để đảm bảo chỉ có một thể hiện duy nhất.
     * Nếu `instance` đã tồn tại, ném ra ngoại lệ `IllegalStateException` để bảo vệ chống lại việc khởi tạo thông qua reflection.
     */
    private ThreadSafeDoubleCheckLocking() {
        if (instance != null) {
            throw new IllegalStateException("Đã được khởi tạo.");
        }
    }

    /**
     * Phương thức công khai để truy cập thể hiện.
     *
     * Tăng hiệu suất lên 25% bằng cách sử dụng biến cục bộ (local variable)
     * theo Joshua Bloch "Effective Java, Second Edition", trang 283-284.
     *
     * @return thể hiện của lớp.
     */
    public static ThreadSafeDoubleCheckLocking getInstance() {
        // Biến cục bộ tăng hiệu suất lên 25%.
        // Joshua Bloch "Effective Java, Second Edition", trang 283-284
        var result = instance;

        // Kiểm tra xem thể hiện Singleton đã được khởi tạo chưa.
        // Nếu đã khởi tạo, trả về thể hiện.
        if (result == null) {
            // Chưa được khởi tạo, nhưng không thể chắc chắn vì có thể có luồng khác đã khởi tạo.
            // Để chắc chắn, cần khóa đối tượng để đạt được sự loại trừ lẫn nhau.
            synchronized (ThreadSafeDoubleCheckLocking.class) {
                // Lại gán thể hiện vào biến cục bộ để kiểm tra xem có luồng khác đã khởi tạo không
                // trong khi luồng hiện tại bị khóa.
                // Nếu đã khởi tạo, trả về thể hiện đã được tạo trước đó giống như kiểm tra null trước.
                result = instance;
                if (result == null) {
                    // Thể hiện vẫn chưa được khởi tạo, do đó có thể an toàn
                    // (không có luồng nào khác có thể vào vùng này)
                    // tạo một thể hiện và gán nó là thể hiện Singleton.
                    result = new ThreadSafeDoubleCheckLocking();
                    instance = result;
                }
            }
        }
        return result;
    }
}
