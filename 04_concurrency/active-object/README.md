---
title: Active Object
category: Concurrency
language: en
tag:
    - Asynchronous
    - Decoupling
    - Messaging
    - Synchronization
    - Thread management
---

## Intent

The Active Object design pattern provides a safe and reliable way to implement asynchronous behavior in concurrent systems. It achieves this by encapsulating tasks within objects that have their own thread and message queue. This separation keeps the main thread responsive and avoids issues like direct thread manipulation or shared state access.

## Explanation

Real-world example

> Imagine a busy restaurant where customers place orders with waiters. Instead of the waiters going to the kitchen to prepare the food themselves, they write the orders on slips and hand them to a dispatcher. The dispatcher manages a pool of chefs who prepare the meals asynchronously. Once a chef is free, they pick up the next order from the queue, prepare the dish, and notify the waiter when it's ready for serving.
>
> In this analogy, the waiters represent the client threads, the dispatcher represents the scheduler, and the chefs represent the method execution in separate threads. This setup allows the waiters to continue taking orders without being blocked by the food preparation process, much like the Active Object pattern decouples method invocation from execution to enhance concurrency.

In plain words

> The Active Object pattern decouples method execution from method invocation to improve concurrency and responsiveness in multithreaded applications.

Wikipedia says

> The active object design pattern decouples method execution from method invocation for objects that each reside in their own thread of control.[1] The goal is to introduce concurrency, by using asynchronous method invocation and a scheduler for handling requests.
>
> The pattern consists of six elements:
>
> * A proxy, which provides an interface towards clients with publicly accessible methods.
> * An interface which defines the method request on an active object.
> * A list of pending requests from clients.
> * A scheduler, which decides which request to execute next.
> * The implementation of the active object method.
> * A callback or variable for the client to receive the result.

**Programmatic Example**

The Orcs are known for their wildness and untameable soul. It seems like they have their own thread of control based on previous behavior. To implement a creature that has its own thread of control mechanism and expose its API only and not the execution itself, we can use the Active Object pattern.

```java
public abstract class ActiveCreature {
    private final Logger logger = LoggerFactory.getLogger(ActiveCreature.class.getName());

    private BlockingQueue<Runnable> requests;

    private String name;

    private Thread thread;

    public ActiveCreature(String name) {
        this.name = name;
        this.requests = new LinkedBlockingQueue<Runnable>();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        requests.take().run();
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage());
                    }
                }
            }
        }
        );
        thread.start();
    }

    public void eat() throws InterruptedException {
        requests.put(new Runnable() {
                         @Override
                         public void run() {
                             logger.info("{} is eating!", name());
                             logger.info("{} has finished eating!", name());
                         }
                     }
        );
    }

    public void roam() throws InterruptedException {
        requests.put(new Runnable() {
                         @Override
                         public void run() {
                             logger.info("{} has started to roam the wastelands.", name());
                         }
                     }
        );
    }

    public String name() {
        return this.name;
    }
}
```

We can see that any class that will extend the ActiveCreature class will have its own thread of control to invoke and execute methods.

For example, the Orc class:

```java
public class Orc extends ActiveCreature {

    public Orc(String name) {
        super(name);
    }

}
```

Now, we can create multiple creatures such as Orcs, tell them to eat and roam, and they will execute it on their own thread of control:

```java
public class App implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(App.class.getName());

    private static final int NUM_CREATURES = 3;

    public static void main(String[] args) {
        var app = new App();
        app.run();
    }

    @Override
    public void run() {
        List<ActiveCreature> creatures = new ArrayList<>();
        try {
            for (int i = 0; i < NUM_CREATURES; i++) {
                creatures.add(new Orc(Orc.class.getSimpleName() + i));
                creatures.get(i).eat();
                creatures.get(i).roam();
            }
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            for (int i = 0; i < NUM_CREATURES; i++) {
                creatures.get(i).kill(0);
            }
        }
    }
}
```

Program output:

```
09:00:02.501 [Thread-0] INFO com.iluwatar.activeobject.ActiveCreature -- Orc0 is eating!
09:00:02.501 [Thread-2] INFO com.iluwatar.activeobject.ActiveCreature -- Orc2 is eating!
09:00:02.501 [Thread-1] INFO com.iluwatar.activeobject.ActiveCreature -- Orc1 is eating!
09:00:02.504 [Thread-0] INFO com.iluwatar.activeobject.ActiveCreature -- Orc0 has finished eating!
09:00:02.504 [Thread-1] INFO com.iluwatar.activeobject.ActiveCreature -- Orc1 has finished eating!
09:00:02.504 [Thread-0] INFO com.iluwatar.activeobject.ActiveCreature -- Orc0 has started to roam in the wastelands.
09:00:02.504 [Thread-2] INFO com.iluwatar.activeobject.ActiveCreature -- Orc2 has finished eating!
09:00:02.504 [Thread-1] INFO com.iluwatar.activeobject.ActiveCreature -- Orc1 has started to roam in the wastelands.
09:00:02.504 [Thread-2] INFO com.iluwatar.activeobject.ActiveCreature -- Orc2 has started to roam in the wastelands.
```

## Class diagram

![Active Object](./etc/active-object.urm.png "Active Object class diagram")

## Applicability

* When you need to perform long-running operations without blocking the main thread.
* When you need to interact with external resources asynchronously.
* When you want to improve the responsiveness of your application.
* When you need to manage concurrent tasks in a modular and maintainable way.

## Tutorials

* [Android and Java Concurrency: The Active Object Pattern(Douglas Schmidt)](https://www.youtube.com/watch?v=Cd8t2u5Qmvc)

## Known Uses

* Real-time trading systems where transaction requests are handled asynchronously.
* GUIs where long-running tasks are executed in the background without freezing the user interface.
* Game programming to handle concurrent updates to game state or AI computations.

## Consequences

Benefits:

* Improves responsiveness of the main thread.
* Encapsulates concurrency concerns within objects.
* Promotes better code organization and maintainability.
* Provides thread safety and avoids shared state access problems.

Trade-offs:

* Introduces additional overhead due to message passing and thread management.
* May not be suitable for all types of concurrency problems.

## Related patterns

* [Command](https://java-design-patterns.com/patterns/command/): Encapsulates a request as an object, similarly to how the Active Object pattern encapsulates method calls.
* [Promise](https://java-design-patterns.com/patterns/promise/): Provides a means to retrieve the result of an asynchronous method call, often used in conjunction with Active Object.
* [Proxy](https://java-design-patterns.com/patterns/proxy/): The Active Object pattern can use a proxy to handle method invocations asynchronously.

## Credits

* [Design Patterns: Elements of Reusable Object Software](https://amzn.to/3HYqrBE)
* [Concurrent Programming in Java: Design Principles and Patterns](https://amzn.to/498SRVq)
* [Java Concurrency in Practice](https://amzn.to/4aRMruW)
* [Learning Concurrent Programming in Scala](https://amzn.to/3UE07nV)
* [Pattern Languages of Program Design 3](https://amzn.to/3OI1j61)
* [Pattern-Oriented Software Architecture Volume 2: Patterns for Concurrent and Networked Objects](https://amzn.to/3UgC24V)



## Áp dụng

* Khi bạn cần thực hiện các hoạt động chạy lâu mà không chặn luồng chính.
* Khi bạn cần tương tác với các nguồn tài nguyên bên ngoài một cách bất đồng bộ.
* Khi bạn muốn cải thiện tính phản hồi của ứng dụng của bạn.
* Khi bạn cần quản lý các nhiệm vụ đồng thời một cách modul và dễ bảo trì.

## Hướng dẫn

* [Android và Java Concurrency: Mẫu Active Object(Douglas Schmidt)](https://www.youtube.com/watch?v=Cd8t2u5Qmvc)

## Các ứng dụng đã biết

* Hệ thống giao dịch thời gian thực nơi các yêu cầu giao dịch được xử lý bất đồng bộ.
* Giao diện người dùng đồ họa nơi các tác vụ chạy lâu được thực hiện trong nền mà không làm đóng băng giao diện người dùng.
* Lập trình game để xử lý cập nhật đồng thời của trạng thái trò chơi hoặc tính toán trí tuệ nhân tạo.

## Hậu quả

Lợi ích:

* Cải thiện tính phản hồi của luồng chính.
* Đóng gói các vấn đề về đồng thời trong các đối tượng.
* Thúc đẩy tổ chức mã tốt hơn và dễ bảo trì.
* Cung cấp tính an toàn luồng và tránh các vấn đề truy cập trạng thái chia sẻ.

Nhược điểm:

* Giới thiệu chi phí thêm do truyền tin và quản lý luồng.
* Có thể không phù hợp cho tất cả các loại vấn đề về đồng thời.

## Các mẫu tương quan

* [Lệnh](https://java-design-patterns.com/patterns/command/): Đóng gói một yêu cầu thành một đối tượng, tương tự như cách mẫu Active Object đóng gói các cuộc gọi phương thức.
* [Promise](https://java-design-patterns.com/patterns/promise/): Cung cấp một phương tiện để nhận kết quả của một cuộc gọi phương thức bất đồng bộ, thường được sử dụng cùng với Active Object.
* [Proxy](https://java-design-patterns.com/patterns/proxy/): Mẫu Active Object có thể sử dụng một proxy để xử lý các cuộc gọi phương thức một cách bất đồng bộ.