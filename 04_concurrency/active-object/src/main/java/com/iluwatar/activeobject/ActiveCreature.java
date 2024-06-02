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
     */
    protected ActiveCreature(String name) {
        this.name = name; // Gán tên của sinh vật.
        this.status = 0; // Khởi tạo trạng thái của luồng thực thi.
        this.requests = new LinkedBlockingQueue<>(); // Khởi tạo một hàng đợi để lưu trữ các nhiệm vụ.
        thread = new Thread(() -> { // Tạo một luồng mới.
            boolean infinite = true; // Cờ để kiểm soát vòng lặp.
            while (infinite) { // Vòng lặp vô hạn.
                try {
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
