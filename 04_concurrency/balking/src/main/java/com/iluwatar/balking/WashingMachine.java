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

package com.iluwatar.balking;

import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Washing machine class.
 */
@Slf4j
public class WashingMachine {

    private final DelayProvider delayProvider;

    @Getter
    private WashingMachineState washingMachineState;

    /**
     * Creates a new instance of WashingMachine.
     * ----------------------------------------------
     * trong Java bạn có thể gọi một constructor từ một constructor khác trong cùng một lớp.
     * Quy trình này được gọi là "constructor chaining".
     * Điều này cho phép bạn tái sử dụng mã khởi tạo và giảm sự lặp lại trong mã của bạn.
     */
    public WashingMachine() {
        /* Cách 1 :*/
//        this(new DelayProvider() {
//            @Override
//            public void executeAfterDelay(long interval, TimeUnit timeUnit, Runnable task) {
//                try {
//                    Thread.sleep(timeUnit.toMillis(interval));
//                } catch (InterruptedException ie) {
//                    LOGGER.error("", ie);
//                    Thread.currentThread().interrupt();
//                }
//                task.run();
//            }
//        });

        /* Cách 2 : use lamda */
        this((interval, timeUnit, task) -> {
            try {
                Thread.sleep(timeUnit.toMillis(interval));
            } catch (InterruptedException ie) {
                LOGGER.error("", ie);
                Thread.currentThread().interrupt();
            }
            task.run();
        });
    }

    /**
     * Creates a new instance of WashingMachine using provided delayProvider. This constructor is used
     * only for unit testing purposes.
     */
    public WashingMachine(DelayProvider delayProvider) {
        this.delayProvider = delayProvider;
        this.washingMachineState = WashingMachineState.ENABLED;
    }

    /**
     * Method responsible for washing if the object is in appropriate state.
     * --------------------------------------------------------------------
     * Trong trường hợp đầu tiên, khi bạn tạo ra 3 instance của `WashingMachine`
     * và mỗi instance gọi phương thức `wash()` một lần,
     * mỗi instance sẽ có riêng của nó và việc gọi `wash()` trên mỗi instance sẽ xảy ra độc lập với các instance khác.
     * Khi một instance gọi `wash()`, nó sẽ đặt trạng thái của máy giặt thành "đang giặt"
     * và sau đó kết thúc việc giặt sau một khoảng thời gian nhất định.
     * Các instance khác không bị ảnh hưởng và vẫn có thể gọi `wash()` của chính mình một cách độc lập.
     *
     * Trong trường hợp thứ hai, khi bạn tạo ra một instance của `WashingMachine`
     * nhưng gọi phương thức `wash()` ba lần,
     * chỉ có một luồng có thể thực hiện một phương thức `wash()` tại một thời điểm do sự đồng bộ hóa.
     * Khi bạn gọi `wash()` lần đầu tiên, nó sẽ đặt trạng thái của máy giặt thành "đang giặt"
     * và sau đó kết thúc việc giặt sau một khoảng thời gian nhất định.
     * Cho đến khi `wash()` đầu tiên kết thúc, thì luồng mới có thể gọi `wash()` lần thứ hai.
     * Quy trình này tiếp tục cho đến khi tất cả các lần gọi `wash()` đã hoàn thành.
     *
     * Như vậy, trong cả hai trường hợp, việc đồng bộ hóa sẽ đảm bảo rằng các hoạt động `wash()`
     * trên mỗi instance hoặc trên cùng một instance sẽ được thực hiện một cách an toàn và theo đúng thứ tự.
     */
    public void wash() {
        synchronized (this) {
            var machineState = getWashingMachineState();
            LOGGER.info("{}: Actual machine state: {}", Thread.currentThread().getName(),
                machineState);
            if (this.washingMachineState == WashingMachineState.WASHING) {
                LOGGER.error("Cannot wash if the machine has been already washing!");
                return;
            }
            this.washingMachineState = WashingMachineState.WASHING;
        }
        LOGGER.info("{}: Doing the washing", Thread.currentThread().getName());

        this.delayProvider.executeAfterDelay(50, TimeUnit.MILLISECONDS, this::endOfWashing);
    }

    /**
     * Method is responsible for ending the washing by changing machine state.
     */
    public synchronized void endOfWashing() {
        washingMachineState = WashingMachineState.ENABLED;
        LOGGER.info("{}: Washing completed.", Thread.currentThread().getId());
    }

}
