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
 * <p>
 *     Biểu tượng giữa các thuật ngữ là một cách an toàn để tạo một thể hiện singleton
 *     được khởi tạo lười (lazy-initialized) trong Java.
 * </p>
 *
 * <p>
 *     Kỹ thuật này là lười nhất có thể và hoạt động trong tất cả các phiên bản Java đã biết.
 *      Nó tận dụng các bảo đảm về khởi tạo lớp trong ngôn ngữ,
 *      và do đó sẽ hoạt động đúng trong tất cả các trình biên dịch và máy ảo Java tuân thủ chuẩn.
 * </p>
 *
 * <p>
 *     Lớp bên trong không được tham chiếu trước (và do đó không được tải trước bởi bộ tải lớp)
 *      cho đến thời điểm phương thức getInstance() được gọi.
 *      Do đó, giải pháp này là an toàn với luồng mà không cần các cấu trúc ngôn ngữ đặc biệt
 *      (ví dụ: volatile hoặc synchronized).
 * </p>
 */
public final class InitializingOnDemandHolderIdiom {

    /**
     * Constructor riêng tư.
     */
    private InitializingOnDemandHolderIdiom() {
    }

    /**
     * Thể hiện singleton.
     */
    public static InitializingOnDemandHolderIdiom getInstance() {
        return HelperHolder.INSTANCE;
    }

    /**
     * Cung cấp thể hiện Singleton được tải lười (lazy-loaded).
     */
    private static class HelperHolder {
        /**
         * Thể hiện Singleton của lớp.
         */
        private static final InitializingOnDemandHolderIdiom INSTANCE =
            new InitializingOnDemandHolderIdiom();
    }
}

