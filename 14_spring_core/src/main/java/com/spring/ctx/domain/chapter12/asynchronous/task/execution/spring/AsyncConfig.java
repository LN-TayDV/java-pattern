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
package com.spring.ctx.domain.chapter12.asynchronous.task.execution.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.spring.ctx.domain.chapter12.asynchronous.task.execution.spring")
public class AsyncConfig implements AsyncConfigurer {

    @Bean
    public AsyncService asyncService() {
        return new AsyncServiceImpl();
    }

    /**
     * `AsyncConfigurer` trong Spring được sử dụng để cấu hình các tùy chọn cho xử lý bất đồng bộ. Khi bạn triển khai `AsyncConfigurer`, bạn có thể tùy chỉnh `TaskExecutor` và các thuộc tính liên quan đến cách các nhiệm vụ bất đồng bộ được xử lý.
     *
     * ### Phương thức `getAsyncExecutor()`
     *
     * Phương thức `getAsyncExecutor()` trong `AsyncConfigurer` cho phép bạn định nghĩa một `TaskExecutor` tùy chỉnh mà Spring sẽ sử dụng để thực thi các phương thức bất đồng bộ. Đây là nơi bạn có thể cấu hình các thuộc tính như số lượng luồng tối thiểu, tối đa, và các tham số khác liên quan đến quản lý luồng.
     *
     * #### 1. `ThreadPoolTaskExecutor`
     *
     * - `ThreadPoolTaskExecutor` là một implementation của `TaskExecutor` trong Spring,
     * cho phép bạn cấu hình một pool các luồng để xử lý các nhiệm vụ bất đồng bộ.
     *
     * #### 2. `setCorePoolSize(2)`
     *
     * - **Core Pool Size (Kích Thước Pool Cơ Bản):
     * ** Số lượng luồng tối thiểu mà pool sẽ giữ ngay cả khi không có nhiệm vụ nào.
     * Trong trường hợp này, pool sẽ luôn có ít nhất 2 luồng hoạt động.
     *
     * #### 3. `setMaxPoolSize(10)`
     * - **Max Pool Size (Kích Thước Pool Tối Đa):
     * ** Số lượng luồng tối đa mà pool có thể mở rộng.
     * Nếu có nhiều nhiệm vụ hơn số lượng luồng cơ bản và hàng đợi đã đầy, pool có thể mở rộng đến tối đa 10 luồng.
     *
     * #### 4. `setThreadNamePrefix("tpte2-")`
     * - **Thread Name Prefix (Tiền Tố Tên Luồng):
     * ** Tiền tố sẽ được thêm vào tên của các luồng mà pool tạo ra.
     * Điều này giúp dễ dàng nhận diện các luồng thuộc về pool này trong log hoặc công cụ giám sát.
     *
     * #### 5. `setQueueCapacity(5)`
     * - **Queue Capacity (Dung Lượng Hàng Đợi):
     * ** Số lượng nhiệm vụ có thể được đợi trong hàng đợi trước khi các luồng thêm được tạo ra.
     * Nếu hàng đợi đầy và số lượng luồng tối đa chưa đạt, các nhiệm vụ sẽ được xử lý bởi các luồng mới.
     *
     * #### 6. `initialize()`
     * - **Initialize:** Khởi tạo `ThreadPoolTaskExecutor` với các cấu hình đã thiết lập.
     *
     * ### Sử dụng trong Spring
     *
     * Khi bạn triển khai `AsyncConfigurer` và cung cấp một `TaskExecutor` tùy chỉnh,
     * Spring sẽ sử dụng cấu hình của bạn để xử lý các nhiệm vụ bất đồng bộ.
     * Điều này cho phép bạn kiểm soát số lượng luồng,
     * cách quản lý hàng đợi và các thuộc tính khác liên quan đến hiệu suất của các nhiệm vụ bất đồng bộ
     * trong ứng dụng của bạn.
     */

    @Override
    public Executor getAsyncExecutor() {
        var tpts = new ThreadPoolTaskExecutor();
        tpts.setCorePoolSize(2);
        tpts.setMaxPoolSize(5);
        tpts.setThreadNamePrefix("tpte2-");
        tpts.setQueueCapacity(5);
        tpts.initialize();
        return tpts;
    }
}
