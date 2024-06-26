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

package com.iluwatar.monostate;

import java.util.ArrayList;
import java.util.List;

/**
 * The LoadBalancer class. This implements the MonoState pattern. It holds a series of servers. Upon
 * receiving a new Request, it delegates the call to the servers in a Round Robin Fashion. Since all
 * instances of the class share the same state, all instances will delegate to the same server on
 * receiving a new Request.
 */

public class LoadBalancer {
    private static final List<Server> SERVERS = new ArrayList<>();
    private static int lastServedId;

    static {
        var id = 0;
        for (var port : new int[] {8080, 8081, 8082, 8083, 8084}) {
            SERVERS.add(new Server("localhost", port, ++id));
        }
    }

    /**
     * Tại sao sử dụng synchronized?
     * Tránh điều kiện race (race condition):
     * Điều kiện race xảy ra khi nhiều luồng truy cập và thay đổi cùng một tài nguyên chia sẻ
     * mà không được đồng bộ hóa đúng cách, dẫn đến các kết quả không mong muốn.
     *
     * Đảm bảo tính nhất quán của dữ liệu:
     * Synchronized giúp đảm bảo rằng dữ liệu không bị thay đổi đồng thời bởi nhiều luồng,
     * giữ cho dữ liệu luôn nhất quán.
     *
     * Đảm bảo an toàn cho luồng (thread-safe):
     * Đồng bộ hóa các phương thức và khối mã giúp đảm bảo rằng chỉ một luồng có thể
     * truy cập tài nguyên chia sẻ tại một thời điểm, ngăn chặn các vấn đề về đồng bộ hóa.
     */

    /**
     * 1. Sử dụng Collections Synchronized Wrappers
     * Java cung cấp các phương thức trong lớp Collections để tạo ra các collection đồng bộ hóa từ các collection không đồng bộ.
     * Các phương thức này bao gồm synchronizedList(), synchronizedSet(), synchronizedMap(), v.v.
     *
     * 2. Sử dụng các lớp trong java.util.concurrent
     * Java cung cấp các lớp trong gói java.util.concurrent được thiết kế để làm việc trong môi trường đa luồng.
     * Một số lớp thông dụng bao gồm:
     *
     * ConcurrentHashMap:
     * Một triển khai thread-safe của HashMap.
     *
     * CopyOnWriteArrayList:
     * Một triển khai thread-safe của ArrayList.
     *
     * CopyOnWriteArraySet:
     * Một triển khai thread-safe của HashSet.
     *
     * BlockingQueue:
     * Một hàng đợi thread-safe với các triển khai như ArrayBlockingQueue, LinkedBlockingQueue,
     * PriorityBlockingQueue, v.v.
     */
    public final void addServer(Server server) {
        synchronized (SERVERS) {
            SERVERS.add(server);
        }
    }

    public final int getNoOfServers() {
        return SERVERS.size();
    }

    public int getLastServedId() {
        return lastServedId;
    }

    /**
     * 1. Synchronized Method:
     *     - Phương thức `serverRequest` trong lớp `LoadBalancer` được đánh dấu là `synchronized`,
     *     nghĩa là chỉ một luồng có thể thực hiện nó tại một thời điểm.
     *     Khi một luồng đang thực hiện phương thức này, các luồng khác cần phải chờ đợi
     *     cho đến khi khóa `synchronized` được giải phóng trước khi chúng có thể truy cập vào phương thức.
     *
     * 2. Chia Sẻ Biến `lastServedId`:
     *     - Biến `lastServedId` được khai báo là biến static,
     *     nghĩa là nó được chia sẻ giữa tất cả các thể hiện của lớp `LoadBalancer`.
     *     Khi một thể hiện thay đổi giá trị của `lastServedId`,
     *     thay đổi này sẽ ảnh hưởng đến tất cả các thể hiện khác.
     *
     * 3. Vấn Đề Thực Hiện Đồng Thời:
     *     - Trong phương thức `main`, bạn đã tạo hai thể hiện của `LoadBalancer`
     *     và gọi phương thức `serverRequest` từ mỗi thể hiện.
     *     Dù là hai thể hiện, nhưng do phương thức `serverRequest` là `synchronized`,
     *     nên chỉ một trong số chúng được phép thực hiện phương thức này tại một thời điểm.
     *
     *     - Khi một thể hiện của `LoadBalancer` thực hiện phương thức `serverRequest`và đang giữ khóa `synchronized`,
     *     thì thể hiện khác sẽ phải chờ đợi cho đến khi khóa được giải phóng
     *     trước khi nó có thể truy cập vào phương thức `serverRequest`.
     *
     * 4. Kết Quả:
     *     - Do chỉ một thể hiện có thể thực hiện phương thức `serverRequest` tại một thời điểm,
     *     nên khi một thể hiện tăng giá trị của `lastServedId`,
     *     thì giá trị mới của `lastServedId` sẽ được sử dụng bởi thể hiện khác khi nó được kích hoạt tiếp theo.
     *
     *     - Điều này dẫn đến việc `lastServedId` có thể tăng tiếp theo bắt đầu từ vị trí mà thể hiện trước đó đã kết thúc.
     *
     *    Do đó, kết quả là các yêu cầu sẽ được phân phối lần lượt cho các máy chủ theo trình tự của `lastServedId`,
     *    dù bạn có nhiều thể hiện của lớp `LoadBalancer`.
     */
    public synchronized void serverRequest(Request request) {
        if (lastServedId >= SERVERS.size()) {
            lastServedId = 0;
        }
        var server = SERVERS.get(lastServedId++);
        server.serve(request);
    }

}
