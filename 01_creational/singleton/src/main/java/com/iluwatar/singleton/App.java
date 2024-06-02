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

package com.iluwatar.singleton;

import com.iluwatar.singleton.eagerly.enums.EnumIvoryTower;
import com.iluwatar.singleton.eagerly.instance.IvoryTower;
import com.iluwatar.singleton.lazily.BillPughImplementation;
import com.iluwatar.singleton.lazily.InitializingOnDemandHolderIdiom;
import com.iluwatar.singleton.on.demand.ThreadSafeDoubleCheckLocking;
import com.iluwatar.singleton.on.demand.ThreadSafeLazyLoadedIvoryTower;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Singleton pattern ensures that the class can have only one existing instance per Java
 * classloader instance and provides global access to it.</p>
 *
 * <p>One of the risks of this pattern is that bugs resulting from setting a singleton up in a
 * distributed environment can be tricky to debug since it will work fine if you debug with a
 * single classloader. Additionally, these problems can crop up a while after the implementation of
 * a singleton, since they may start synchronous and only become async with time, so it may
 * not be clear why you are seeing certain changes in behavior.</p>
 *
 * <p>There are many ways to implement the Singleton. The first one is the eagerly initialized
 * instance in {@link IvoryTower}. Eager initialization implies that the implementation is thread
 * safe. If you can afford to give up control of the instantiation moment, then this implementation
 * will suit you fine.</p>
 *
 * <p>The other option to implement eagerly initialized Singleton is enum-based Singleton. The
 * example is found in {@link EnumIvoryTower}. At first glance, the code looks short and simple.
 * However, you should be aware of the downsides including committing to implementation strategy,
 * extending the enum class, serializability, and restrictions to coding. These are extensively
 * discussed in Stack Overflow: http://programmers.stackexchange.com/questions/179386/what-are-the-downsides-of-implementing
 * -a-singleton-with-javas-enum</p>
 *
 * <p>{@link ThreadSafeLazyLoadedIvoryTower} is a Singleton implementation that is initialized on
 * demand. The downside is that it is very slow to access since the whole access method is
 * synchronized.</p>
 *
 * <p>Another Singleton implementation that is initialized on demand is found in
 * {@link ThreadSafeDoubleCheckLocking}. It is somewhat faster than {@link
 * ThreadSafeLazyLoadedIvoryTower} since it doesn't synchronize the whole access method but only the
 * method internals on specific conditions.</p>
 *
 * <p>Yet another way to implement thread-safe lazily initialized Singleton can be found in
 * {@link InitializingOnDemandHolderIdiom}. However, this implementation requires at least Java 8
 * API level to work.</p>
 */

/**
 * Mẫu Singleton đảm bảo rằng lớp chỉ có thể có một thể hiện duy nhất cho mỗi bộ tải lớp Java
 * và cung cấp quyền truy cập toàn cục cho nó.
 *
 * Một trong những rủi ro của mẫu này là lỗi xuất hiện khi thiết lập Singleton trong môi trường phân tán
 * có thể khó gỡ lỗi vì nó sẽ hoạt động tốt nếu bạn gỡ lỗi với một bộ tải lớp duy nhất.
 * Ngoài ra, những vấn đề này có thể xuất hiện một thời gian sau khi triển khai Singleton,
 * vì chúng có thể bắt đầu đồng bộ và chỉ trở thành không đồng bộ theo thời gian,
 * do đó có thể không rõ tại sao bạn lại thấy những thay đổi nhất định trong hành vi.
 *
 * Có nhiều cách để triển khai Singleton. Cách đầu tiên là khởi tạo instance sớm trong {@link IvoryTower}.
 * Khởi tạo sớm ngụ ý rằng triển khai này an toàn với luồng (thread-safe).
 * Nếu bạn có thể chấp nhận việc từ bỏ quyền kiểm soát thời điểm khởi tạo, thì triển khai này sẽ phù hợp với bạn.
 *
 * Lựa chọn khác để triển khai Singleton khởi tạo sớm là Singleton dựa trên enum.
 * Ví dụ có thể tìm thấy trong {@link EnumIvoryTower}.
 * Ban đầu, mã trông ngắn gọn và đơn giản.
 * Tuy nhiên, bạn nên biết về những nhược điểm bao gồm việc cam kết với chiến lược triển khai,
 * mở rộng lớp enum, khả năng tuần tự hóa, và các hạn chế trong việc mã hóa.
 * Những điều này được thảo luận rộng rãi trên
 * Stack Overflow: http://programmers.stackexchange.com/questions/179386/what-are-the-downsides-of-implementing-a-singleton-with-javas-enum
 *
 * {@link ThreadSafeLazyLoadedIvoryTower} là một triển khai Singleton được khởi tạo khi cần.
 * Nhược điểm là truy cập rất chậm vì toàn bộ phương thức truy cập được đồng bộ hóa.
 *
 * Một triển khai Singleton khác được khởi tạo khi cần có thể được tìm thấy trong {@link ThreadSafeDoubleCheckLocking}.
 * Nó nhanh hơn một chút so với {@link ThreadSafeLazyLoadedIvoryTower} vì nó không đồng bộ hóa toàn bộ phương thức truy cập
 * mà chỉ đồng bộ hóa các phần nội bộ của phương thức dựa trên các điều kiện cụ thể.
 *
 * Một cách khác để triển khai Singleton được khởi tạo lười biếng và an toàn với luồng
 * có thể được tìm thấy trong {@link InitializingOnDemandHolderIdiom}.
 * Tuy nhiên, triển khai này yêu cầu ít nhất Java API cấp 8 để hoạt động.
 */
@Slf4j
public class App {

    /**
     * Program entry point.
     *
     * @param args command line args
     */
    public static void main(String[] args) {

        // eagerly initialized singleton
        var ivoryTower1 = IvoryTower.getInstance();
        var ivoryTower2 = IvoryTower.getInstance();
        LOGGER.info("ivoryTower1={}", ivoryTower1);
        LOGGER.info("ivoryTower2={}", ivoryTower2);

        // lazily initialized singleton
        var threadSafeIvoryTower1 = ThreadSafeLazyLoadedIvoryTower.getInstance();
        var threadSafeIvoryTower2 = ThreadSafeLazyLoadedIvoryTower.getInstance();
        LOGGER.info("threadSafeIvoryTower1={}", threadSafeIvoryTower1);
        LOGGER.info("threadSafeIvoryTower2={}", threadSafeIvoryTower2);

        // enum singleton
        var enumIvoryTower1 = EnumIvoryTower.INSTANCE;
        var enumIvoryTower2 = EnumIvoryTower.INSTANCE;
        LOGGER.info("enumIvoryTower1={}", enumIvoryTower1);
        LOGGER.info("enumIvoryTower2={}", enumIvoryTower2);

        // double-checked locking
        var dcl1 = ThreadSafeDoubleCheckLocking.getInstance();
        LOGGER.info(dcl1.toString());
        var dcl2 = ThreadSafeDoubleCheckLocking.getInstance();
        LOGGER.info(dcl2.toString());

        // initialize on demand holder idiom
        var demandHolderIdiom = InitializingOnDemandHolderIdiom.getInstance();
        LOGGER.info(demandHolderIdiom.toString());
        var demandHolderIdiom2 = InitializingOnDemandHolderIdiom.getInstance();
        LOGGER.info(demandHolderIdiom2.toString());

        // initialize singleton using Bill Pugh's implementation
        var billPughSingleton = BillPughImplementation.getInstance();
        LOGGER.info(billPughSingleton.toString());
        var billPughSingleton2 = BillPughImplementation.getInstance();
        LOGGER.info(billPughSingleton2.toString());
    }
}
