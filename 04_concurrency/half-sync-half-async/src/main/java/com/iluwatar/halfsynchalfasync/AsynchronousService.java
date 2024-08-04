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

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * Đây là lớp không đồng bộ, không chặn khi một yêu cầu mới đến. Nó chỉ chuyển
 * yêu cầu tới lớp đồng bộ bao gồm một hàng đợi {@link BlockingQueue} và một
 * nhóm các luồng {@link ThreadPoolExecutor}. Trong nhóm các luồng làm việc này,
 * một trong các luồng sẽ nhận nhiệm vụ và thực hiện nó đồng bộ trong nền và kết quả
 * sẽ được gửi lại cho caller thông qua callback.
 */
@Slf4j
public class AsynchronousService {
    /**
     * Đây là lớp đại diện cho lớp hàng đợi cũng như lớp đồng bộ của mẫu thiết kế. Nhóm luồng
     * chứa các luồng làm việc thực hiện các nhiệm vụ theo cách chặn/đồng bộ. Các nhiệm vụ dài hạn
     * nên được thực hiện trong nền để không ảnh hưởng đến hiệu suất của luồng chính.
     */
    private final ExecutorService service;

    /**
     * Tạo một dịch vụ không đồng bộ sử dụng {@code workQueue} làm kênh giao tiếp giữa
     * lớp không đồng bộ và lớp đồng bộ. Các loại hàng đợi khác nhau như hàng đợi ưu tiên có thể
     * được sử dụng để kiểm soát mẫu giao tiếp giữa các lớp.
     */
    public AsynchronousService(BlockingQueue<Runnable> workQueue) {
        service = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, workQueue);
    }

    /**
     * Một phương thức không chặn thực hiện nhiệm vụ được cung cấp trong nền và trả về ngay lập tức.
     *
     * <p>Khi nhiệm vụ hoàn thành thành công, kết quả sẽ được gửi lại bằng phương thức callback {@link
     * AsyncTask#onPostCall(Object)}, nếu thực hiện nhiệm vụ không thể hoàn thành bình thường do một số
     * ngoại lệ thì lý do lỗi sẽ được gửi lại bằng phương thức callback {@link
     * AsyncTask#onError(Throwable)}.
     *
     * <p>CHÚ Ý: Kết quả được gửi lại trong ngữ cảnh của luồng nền trong triển khai này.
     */
    public <T> void execute(final AsyncTask<T> task) {
        try {
            // Một số nhiệm vụ nhỏ như xác thực có thể được thực hiện ở đây.
            task.onPreCall();
        } catch (Exception e) {
            task.onError(e);
            return;
        }

        service.submit(new FutureTask<>(task) {
            @Override
            protected void done() {
                super.done();
                try {
                    /*
                     * Được gọi trong ngữ cảnh của luồng nền. Có một biến thể khác có thể xảy ra
                     * khi kết quả được gửi lại và nằm trong hàng đợi của luồng caller, sau đó
                     * được nhận để xử lý. Một ví dụ về hệ thống như vậy là hệ điều hành Android,
                     * nơi các phần tử giao diện người dùng chỉ có thể được cập nhật bằng luồng UI.
                     * Vì vậy, kết quả phải được gửi lại trong luồng UI.
                     */
                    task.onPostCall(get());
                } catch (InterruptedException e) {
                    // không nên xảy ra
                } catch (ExecutionException e) {
                    task.onError(e.getCause());
                }
            }
        });
    }

    /**
     * Dừng nhóm các luồng làm việc. Đây là một gọi chặn để chờ tất cả các nhiệm vụ hoàn thành.
     */
    public void close() {
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            LOGGER.error("Lỗi khi chờ đóng dịch vụ executor!");
        }
    }
}
