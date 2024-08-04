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

package com.iluwatar.halfsynchalfasync;

import java.util.concurrent.Callable;

/**
 * Đại diện cho một số tính toán được thực hiện không đồng bộ và kết quả của nó. Tính toán
 * thường được thực hiện trong các luồng nền và kết quả được gửi lại dưới dạng callback. Callback
 * không triển khai {@code isComplete}, {@code cancel} vì nó không thuộc phạm vi của mẫu thiết kế này.
 *
 * @param <O> loại kết quả
 */
public interface AsyncTask<O> extends Callable<O> {
    /**
     * Được gọi trong ngữ cảnh của luồng caller trước khi gọi {@link #call()}. Các nhiệm vụ lớn không nên
     * được thực hiện trong phương thức này vì nó sẽ chặn luồng caller. Các nhiệm vụ nhỏ như xác thực
     * có thể được thực hiện ở đây để tránh chi phí chuyển ngữ cảnh trong trường hợp yêu cầu không hợp lệ.
     */
    void onPreCall();

    /**
     * Một callback được gọi sau khi kết quả được tính toán thành công bởi {@link #call()}. Trong triển khai
     * của chúng tôi, phương thức này được gọi trong ngữ cảnh của luồng nền nhưng trong một số biến thể, chẳng
     * hạn như Android nơi chỉ luồng UI có thể thay đổi trạng thái của các widget giao diện người dùng, phương
     * thức này được gọi trong ngữ cảnh của luồng UI.
     */
    void onPostCall(O result);

    /**
     * Một callback được gọi nếu việc tính toán nhiệm vụ dẫn đến một số ngoại lệ. Phương thức này được gọi khi
     * {@link #call()} hoặc {@link #onPreCall()} ném ra bất kỳ ngoại lệ nào.
     *
     * @param throwable nguyên nhân lỗi
     */
    void onError(Throwable throwable);

    /**
     * Đây là nơi tính toán của nhiệm vụ nên nằm. Phương thức này được gọi trong ngữ cảnh của
     * luồng nền.
     */
    @Override
    O call() throws Exception;
}
