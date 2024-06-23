/*
 * This project is licensed under the MIT license. Module model-view-viewmodel is using ZK framework licensed under LGPL (see lgpl-3.0.txt).
 * Bản quyền của dự án này thuộc về giấy phép MIT. Module model-view-viewmodel đang sử dụng framework ZK được cấp phép theo LGPL (xem lgpl-3.0.txt).
 */

package com.iluwatar.activeobject;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActiveCreature class is the base of the active object example.
 * Lớp ActiveCreature là cơ sở của ví dụ về active object.
 */
public abstract class ActiveCreature {

    private static final Logger logger = LoggerFactory.getLogger(ActiveCreature.class.getName());

    private BlockingQueue<Runnable> requests; // A queue for storing tasks to be executed.
    // Một hàng đợi để lưu trữ các nhiệm vụ cần được thực thi.

    private String name; // The name of the creature.
    // Tên của sinh vật.

    private Thread thread; // Thread of execution.
    // Luồng thực thi.

    private int status; // status of the thread of execution.
    // Trạng thái của luồng thực thi.

    /**
     * Constructor and initialization.
     * Constructor và khởi tạo.
     *
     * Trong mỗi vòng lặp của bạn, bạn tạo ra một đối tượng ActiveCreature mới.
     * Mỗi đối tượng này chứa một hàng đợi (requests) để lưu trữ các nhiệm vụ (hoặc hành động) sắp được thực thi.
     * Khi bạn gọi phương thức eat() hoặc roam() trên một đối tượng ActiveCreature,
     * một nhiệm vụ tương ứng sẽ được tạo và đưa vào hàng đợi của đối tượng đó.
     *
     * Dưới đây là cách hoạt động cụ thể:
     *
     * Mỗi vòng lặp tạo một đối tượng mới: Mỗi khi vòng lặp chạy một đối tượng ActiveCreature mới được tạo ra.
     * Điều này đảm bảo rằng mỗi đối tượng sẽ có riêng một hàng đợi requests.
     *
     * Mỗi đối tượng sử dụng hàng đợi của riêng mình: Mỗi khi bạn gọi phương thức eat() hoặc roam()
     * trên một đối tượng ActiveCreature, một nhiệm vụ mới sẽ được tạo và thêm vào hàng đợi của đối tượng đó.
     * Do mỗi đối tượng có riêng một hàng đợi, nên các nhiệm vụ sẽ không bị nhầm lẫn giữa các đối tượng khác nhau.
     *
     * Luồng của mỗi đối tượng thực thi từ hàng đợi của riêng mình:
     * Mỗi luồng chạy của đối tượng ActiveCreature sẽ lặp qua hàng đợi của đối tượng đó
     * và thực thi các nhiệm vụ trong đúng thứ tự mà chúng được thêm vào hàng đợi.
     * Điều này đảm bảo rằng các hành động của mỗi đối tượng sẽ được thực hiện theo đúng thứ tự mà chúng được gọi.
     */
    protected ActiveCreature(String name) {
        this.name = name; // Gán tên của sinh vật.
        this.status = 0; // Khởi tạo trạng thái của luồng thực thi.
        this.requests = new LinkedBlockingQueue<>(); // Khởi tạo một hàng đợi để lưu trữ các nhiệm vụ.
        thread = new Thread(() -> { // Tạo một luồng mới.

            boolean infinite = true; // Cờ để kiểm soát vòng lặp.

            while (infinite) { // Vòng lặp vô hạn.

                try {
                    /**
                     * Trong mã của bạn, requests.take().run();
                     * được gọi trong một luồng riêng biệt, được tạo ra từ hàm khởi tạo của lớp ActiveCreature.
                     * Đoạn mã đó là một vòng lặp vô hạn trong luồng mới này, được bắt đầu khi bạn gọi thread.start().
                     *
                     * requests.take() là một phương thức của LinkedBlockingQueue, nó chờ và lấy một phần tử từ hàng đợi requests.
                     *
                     * Đợi và Lấy Nhiệm Vụ:
                     * Phương thức take() của LinkedBlockingQueue sẽ chờ đợi
                     * nếu hàng đợi đang trống và sẽ lấy ra và loại bỏ phần tử đầu tiên (nhiệm vụ) từ hàng đợi khi có sẵn.
                     *
                     * Thực Thi Nhiệm Vụ:
                     * Phần tử được lấy ra từ hàng đợi requests là một đối tượng Runnable(một hành động được đóng gói trong một đối tượng).
                     * Phương thức run() của đối tượng Runnable này sẽ được gọi,
                     * thực hiện hành động cụ thể mà đối tượng Runnable đại diện cho.
                     * Trong trường hợp này, các nhiệm vụ được thêm vào hàng đợi requests là các lambda expression biểu diễn các hành động eat() và roam().
                     */

                    requests.take().run(); // Lấy một nhiệm vụ từ hàng đợi và thực thi.

                } catch (InterruptedException e) { // Xử lý ngoại lệ khi luồng bị gián đoạn.

                    if (this.status != 0) { // Kiểm tra nếu trạng thái khác không.

                        logger.error("Thread was interrupted. --> {}", e.getMessage()); // Ghi log lỗi nếu luồng bị gián đoạn.
                    }

                    infinite = false; // Thoát khỏi vòng lặp.

                    Thread.currentThread().interrupt(); // Đánh dấu là luồng bị gián đoạn.
                }
            }
        });
        thread.start(); // Khởi động luồng mới.
    }

    /**
     * Eats the porridge.
     * Ăn thức ăn.
     *
     * @throws InterruptedException due to firing a new Runnable.
     * @throws InterruptedException do việc gửi một Runnable mới.
     */
    public void eat() throws InterruptedException {
        requests.put(() -> { // Put a new task into the queue.
            logger.info("{} is eating!", name()); // Log eating action.
            logger.info("{} has finished eating!", name()); // Log finished eating action.
        });
    }

    /**
     * Roam the wastelands.
     * Lang thang trong vùng đất hoang vu.
     *
     * @throws InterruptedException due to firing a new Runnable.
     * @throws InterruptedException do việc gửi một Runnable mới.
     */
    public void roam() throws InterruptedException {
        requests.put(() -> // Put a new task into the queue.
            logger.info("{} has started to roam in the wastelands.", name()) // Log roaming action.
        );
    }

    /**
     * Returns the name of the creature.
     * Trả về tên của sinh vật.
     *
     * @return the name of the creature.
     * @return tên của sinh vật.
     */
    public String name() {
        return this.name; // Return the name.
    }

    /**
     * Kills the thread of execution.
     * Dừng luồng thực thi.
     *
     * @param status of the thread of execution. 0 == OK, the rest is logging an error.
     * @param status của luồng thực thi. 0 == OK, còn lại là ghi log lỗi.
     */
    public void kill(int status) {
        this.status = status; // Set the status.
        this.thread.interrupt(); // Interrupt the thread.
    }

    /**
     * Returns the status of the thread of execution.
     * Trả về trạng thái của luồng thực thi.
     *
     * @return the status of the thread of execution.
     * @return trạng thái của luồng thực thi.
     */
    public int getStatus() {
        return this.status; // Return the status.
    }
}
