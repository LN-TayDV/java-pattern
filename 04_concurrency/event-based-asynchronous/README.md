Dưới đây là bản dịch của cơ chế **Event-Based Asynchronous** sang tiếng Việt:

---

## Cũng được biết đến với

* Xử lý Sự kiện Bất đồng bộ

## Mục đích

Mẫu thiết kế Bất đồng bộ Dựa trên Sự kiện cho phép hệ thống xử lý các tác vụ có thể mất một thời gian để hoàn thành mà không làm tắc nghẽn việc thực thi chương trình. Nó giúp sử dụng tài nguyên tốt hơn bằng cách giải phóng một luồng mà nếu không sẽ bị chặn lại khi chờ tác vụ hoàn tất.

## Giải thích

**Ví dụ thực tế**

> Một ví dụ thực tế về mẫu thiết kế Bất đồng bộ Dựa trên Sự kiện là cách một nhà hàng hoạt động. Khi một khách hàng đặt món, người phục vụ ghi lại đơn hàng và chuyển nó cho nhà bếp. Thay vì chờ đợi ở nhà bếp cho món ăn được chuẩn bị, người phục vụ tiếp tục phục vụ các bàn khác. Khi nhà bếp hoàn tất đơn hàng, họ gửi tín hiệu (sự kiện) cho người phục vụ, người sau đó giao món ăn cho khách hàng. Điều này cho phép người phục vụ xử lý nhiều nhiệm vụ hiệu quả mà không phải chờ đợi không cần thiết, tương tự như cách lập trình bất đồng bộ xử lý các tác vụ song song, nâng cao hiệu suất và khả năng phản hồi tổng thể.

**Nói đơn giản**

> Mẫu thiết kế Bất đồng bộ Dựa trên Sự kiện cho phép các tác vụ được thực hiện ở nền, thông báo cho chương trình chính thông qua các sự kiện khi hoàn tất, từ đó nâng cao hiệu suất và khả năng phản hồi của hệ thống mà không làm tắc nghẽn các hoạt động đang diễn ra.

**Ví dụ lập trình**

Mẫu thiết kế Bất đồng bộ Dựa trên Sự kiện cho phép các tác vụ được thực hiện ở nền, thông báo cho chương trình chính thông qua các sự kiện khi hoàn tất. Điều này nâng cao hiệu suất và khả năng phản hồi của hệ thống mà không làm tắc nghẽn các hoạt động đang diễn ra.

Trong mã ví dụ được cung cấp, chúng ta có một số lớp chính triển khai mẫu này:

- `App`: Lớp chính chạy ứng dụng. Nó tương tác với `EventManager` để tạo, bắt đầu, dừng và kiểm tra trạng thái của các sự kiện.
- `EventManager`: Quản lý vòng đời của các sự kiện, bao gồm việc tạo, bắt đầu, dừng và kiểm tra trạng thái của các sự kiện. Nó duy trì một bản đồ từ ID sự kiện đến các đối tượng `Event`.
- `Event`: Một lớp trừu tượng đại diện cho một sự kiện. Nó có hai lớp con cụ thể: `AsyncEvent` và `SyncEvent`.
- `AsyncEvent` và `SyncEvent`: Đại diện cho các sự kiện bất đồng bộ và đồng bộ tương ứng.
- Các ngoại lệ tùy chỉnh: Được ném ra bởi `EventManager` khi các điều kiện nhất định không được đáp ứng.

Dưới đây là ví dụ mã đơn giản về cách các lớp này tương tác:

```java
// Tạo một EventManager
EventManager eventManager = new EventManager();

// Tạo một sự kiện bất đồng bộ chạy trong 60 giây
int asyncEventId = eventManager.createAsync(Duration.ofSeconds(60));

// Bắt đầu sự kiện bất đồng bộ
eventManager.start(asyncEventId);

// Kiểm tra trạng thái của sự kiện bất đồng bộ
eventManager.status(asyncEventId);

// Dừng sự kiện bất đồng bộ
eventManager.cancel(asyncEventId);
```

Trong ví dụ này, lớp `App` tạo một `EventManager`, sau đó sử dụng nó để tạo, bắt đầu, kiểm tra trạng thái của và dừng một sự kiện bất đồng bộ. `EventManager` tạo một đối tượng `AsyncEvent`, bắt đầu nó trong một luồng riêng, kiểm tra trạng thái của nó và dừng nó khi yêu cầu.

Lớp `EventManager` là phần cốt lõi của việc triển khai mẫu Bất đồng bộ Dựa trên Sự kiện. Nó quản lý vòng đời của các sự kiện, bao gồm việc tạo, bắt đầu, dừng và kiểm tra trạng thái của các sự kiện. Nó duy trì một bản đồ từ ID sự kiện đến các đối tượng `Event`. Dưới đây là đoạn mã về cách nó tạo một sự kiện bất đồng bộ:

```java
public int createAsync(Duration runtime) throws MaxNumOfEventsAllowedException, LongRunningEventException {
  int id = counter.incrementAndGet();
  events.put(id, new AsyncEvent(id, runtime));
  return id;
}
```

Lớp `Event` là một lớp trừu tượng đại diện cho một sự kiện. Nó có hai lớp con cụ thể: `AsyncEvent` và `SyncEvent`. Một `Event` có một ID, thời gian chạy (bao lâu nó nên chạy) và trạng thái (nó đang chạy, đã hoàn thành, hoặc sẵn sàng để bắt đầu). Nó cũng có các phương thức để bắt đầu và dừng sự kiện. Dưới đây là đoạn mã về cách một `AsyncEvent` bắt đầu:

```java
@Override
public void start() {
  Thread thread = new Thread(() -> {
    try {
      handleRunStart();
      Thread.sleep(getRuntime().toMillis());
      handleRunComplete();
    } catch (InterruptedException e) {
      handleRunFailure(e.getMessage());
    }
  });
  thread.start();
}
```

Trong đoạn mã này, khi một `AsyncEvent` được bắt đầu, nó chạy trong một luồng riêng mà không làm tắc nghẽn luồng chính.

Một sự kiện đồng bộ được tạo và quản lý tương tự như một sự kiện bất đồng bộ. Dưới đây là đoạn mã về cách tạo và quản lý một sự kiện đồng bộ:

```java
// Tạo một EventManager
EventManager eventManager = new EventManager();

// Tạo một sự kiện đồng bộ chạy trong 60 giây
int syncEventId = eventManager.create(Duration.ofSeconds(60));

// Bắt đầu sự kiện đồng bộ
eventManager.start(syncEventId);

// Kiểm tra trạng thái của sự kiện đồng bộ
eventManager.status(syncEventId);

// Dừng sự kiện đồng bộ
eventManager.cancel(syncEventId);
```

Trong lớp `EventManager`, một sự kiện đồng bộ được tạo bằng cách sử dụng phương thức `create`:

```java
public int create(Duration runtime) throws MaxNumOfEventsAllowedException, LongRunningEventException {
  int id = counter.incrementAndGet();
  events.put(id, new SyncEvent(id, runtime));
  return id;
}
```

Lớp `SyncEvent` là một lớp con của `Event` đại diện cho một sự kiện đồng bộ. Khi một `SyncEvent` được bắt đầu, nó chạy trên luồng chính và làm tắc nghẽn nó cho đến khi sự kiện hoàn tất. Đây là sự khác biệt so với một `AsyncEvent`, mà chạy trong một luồng riêng mà không làm tắc nghẽn luồng chính.

Đây là các phần chính của mẫu thiết kế Bất đồng bộ Dựa trên Sự kiện như được triển khai trong mã này. Mẫu thiết kế này cho phép các tác vụ được thực hiện ở nền, thông báo cho chương trình chính thông qua các sự kiện khi hoàn tất, từ đó nâng cao hiệu suất và khả năng phản hồi của hệ thống mà không làm tắc nghẽn các hoạt động đang diễn ra.

## Sơ đồ lớp

![Event-Based Asynchronous](./etc/event-asynchronous.png "Event-Based Asynchronous")

## Tính khả dụng

* Khi nhiều tác vụ có thể được xử lý song song và độc lập.
* Hệ thống yêu cầu khả năng phản hồi và không thể để các luồng bị chặn chờ đợi một thao tác hoàn tất.
* Trong các ứng dụng GUI nơi khả năng phản hồi của giao diện người dùng là rất quan trọng.
* Các hệ thống phân tán có liên quan đến các hoạt động mạng dài hạn.

## Sử dụng đã biết

* Thư viện GUI trong Java (ví dụ: JavaFX, Swing với SwingWorker).
* Java Message Service (JMS) để xử lý tin nhắn bất đồng bộ.
* CompletableFuture trong Java và các Framework Dựa trên Sự kiện khác.

## Hậu quả

Lợi ích:

* Cải thiện khả năng mở rộng và khả năng phản hồi của ứng dụng.
* Giảm lãng phí tài nguyên trên các luồng sẽ chỉ chờ đợi các hoạt động I/O.
* Tăng cường khả năng chống lỗi thông qua việc cách ly quá trình thực thi.

Nhược điểm:

* Tăng độ phức tạp của việc xử lý lỗi vì lỗi có thể xảy ra ở các luồng khác nhau hoặc vào các thời điểm khác nhau.
* Có thể dẫn đến mã khó theo dõi hơn và khó gỡ lỗi do tính không tuyến tính của việc thực thi mã bất đồng bộ.

Các mẫu liên quan

* [Observer](https://java-design-patterns.com/patterns/observer/): Thường được sử dụng kết hợp với mẫu này, nơi người quan sát phản ứng với các sự kiện khi chúng xảy ra.
* Publish/Subscribe: Liên quan đến các cơ chế xử lý sự kiện, đặc biệt là cho việc phân phối tin nhắn và sự kiện giữa các thành phần.
* [Command](https://java-design-patterns.com/patterns/command/): Hữu ích cho việc đóng gói tất cả thông tin cần thiết để thực hiện một hành động hoặc kích hoạt một sự kiện.

## Tín dụng

* [Java Concurrency in Practice](https://amzn.to/4c