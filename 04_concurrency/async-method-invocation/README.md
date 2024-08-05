### Mẫu Thiết Kế: Gọi Phương Thức Bất Đồng Bộ

**Mục đích**

Mẫu gọi phương thức bất đồng bộ giúp luồng gọi không bị chặn khi chờ kết quả của các tác vụ. Mẫu này cho phép xử lý song song nhiều tác vụ độc lập và nhận kết quả qua callback hoặc chờ cho đến khi mọi việc hoàn tất.

**Cũng được biết đến với**

* Cuộc gọi phương thức bất đồng bộ (Asynchronous Procedure Call)

**Giải thích**

**Ví dụ thực tế**

> Trong bối cảnh tên lửa vũ trụ, mẫu gọi phương thức bất đồng bộ có thể thấy trong giao tiếp giữa trung tâm điều khiển nhiệm vụ và các hệ thống trên tên lửa. Khi trung tâm điều khiển gửi lệnh cho tên lửa điều chỉnh quỹ đạo hoặc thực hiện kiểm tra hệ thống, họ không chờ đợi tên lửa hoàn thành nhiệm vụ và phản hồi ngay lập tức. Thay vào đó, trung tâm điều khiển tiếp tục giám sát các khía cạnh khác của nhiệm vụ và quản lý các công việc khác. Tên lửa thực hiện lệnh một cách bất đồng bộ và gửi cập nhật trạng thái hoặc kết quả trở lại trung tâm điều khiển khi hoàn thành. Điều này cho phép trung tâm điều khiển quản lý nhiều hoạt động đồng thời mà không bị chặn bởi một nhiệm vụ cụ thể.

**Nói đơn giản**

> Gọi phương thức bất đồng bộ bắt đầu xử lý tác vụ và trả về ngay lập tức trước khi tác vụ sẵn sàng. Kết quả của tác vụ được trả về cho người gọi sau đó.

**Wikipedia nói**

> Trong lập trình đa luồng, gọi phương thức bất đồng bộ (AMI), còn được gọi là cuộc gọi phương thức bất đồng bộ hoặc mẫu bất đồng bộ, là một mẫu thiết kế trong đó vị trí gọi không bị chặn khi chờ đợi mã đã gọi hoàn thành. Thay vào đó, luồng gọi được thông báo khi phản hồi đến. Việc kiểm tra phản hồi là một tùy chọn không mong muốn.

**Ví dụ chương trình**

Dưới đây là ví dụ về việc phóng tên lửa và triển khai các rover mặt trăng:

* Các lớp `AsyncResult`, `AsyncCallback`, và `AsyncExecutor` cho phép thực hiện các tác vụ bất đồng bộ.
* `ThreadAsyncExecutor` là một triển khai của `AsyncExecutor`, xử lý các tác vụ bất đồng bộ trên các luồng riêng biệt.

```java
public interface AsyncResult<T> {
    boolean isCompleted();
    T getValue() throws ExecutionException;
    void await() throws InterruptedException;
}

public interface AsyncCallback<T> {
    void onComplete(T value);
    void onError(Exception ex);
}

public interface AsyncExecutor {
    <T> AsyncResult<T> startProcess(Callable<T> task);
    <T> AsyncResult<T> startProcess(Callable<T> task, AsyncCallback<T> callback);
    <T> T endProcess(AsyncResult<T> asyncResult) throws ExecutionException, InterruptedException;
}
```

**Tính ứng dụng**

Sử dụng mẫu gọi phương thức bất đồng bộ khi:

* Các hoạt động không cần hoàn thành trước khi tiếp tục với các bước tiếp theo trong chương trình.
* Các nhiệm vụ tốn tài nguyên hoặc mất thời gian, như thao tác IO, yêu cầu mạng, hoặc tính toán phức tạp.
* Trong các ứng dụng GUI để ngăn chặn việc đóng băng hoặc không phản hồi.
* Trong các ứng dụng web cho các thao tác IO không chặn.

**Ứng dụng đã biết**

* Máy chủ web xử lý yêu cầu HTTP bất đồng bộ.
* Ứng dụng máy tính để bàn và di động sử dụng luồng nền cho các hoạt động mất thời gian.
* Kiến trúc microservices thực hiện giao tiếp bất đồng bộ qua hàng đợi tin nhắn hoặc luồng sự kiện.

**Hậu quả**

* **Lợi ích:**
    * Cải thiện phản hồi của ứng dụng.
    * Sử dụng tài nguyên hiệu quả hơn.
    * Dễ mở rộng ứng dụng.

* **Nhược điểm:**
    * Tăng độ phức tạp của mã nguồn.
    * Quản lý tài nguyên đòi hỏi cẩn thận.
    * Xử lý lỗi trở nên phức tạp hơn.

**Các mẫu liên quan**

* [Command](https://java-design-patterns.com/patterns/command/): Thực hiện các lệnh bất đồng bộ.
* [Observer](https://java-design-patterns.com/patterns/observer/): Thông báo cho các quan sát viên một cách bất đồng bộ.
* [Promise](https://java-design-patterns.com/patterns/promise/): Đại diện cho một giá trị có thể chưa có sẵn.

---

Nếu cần thêm thông tin hoặc có câu hỏi khác, cứ cho mình biết nhé!