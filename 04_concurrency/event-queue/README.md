Dưới đây là bản dịch của mẫu thiết kế "Event Queue" từ tiếng Anh sang tiếng Việt:

---

## Tên gọi khác

* Dòng sự kiện
* Hàng đợi tin nhắn

## Mục đích

Mẫu thiết kế Hàng Đợi Sự Kiện được thiết kế để quản lý các tác vụ theo cách bất đồng bộ, cho phép các ứng dụng xử lý các hoạt động mà không làm tắc nghẽn các tương tác của người dùng hoặc các quy trình khác.

## Giải thích

Ví dụ thực tế

> Hệ thống email hiện đại là một ví dụ về quy trình cơ bản đứng sau mẫu thiết kế hàng đợi sự kiện. Khi một email được gửi, người gửi tiếp tục công việc hàng ngày của mình mà không cần phản hồi ngay lập tức từ người nhận. Thêm vào đó, người nhận có quyền truy cập và xử lý email vào thời gian rảnh của mình. Do đó, quy trình này phân tách người gửi và người nhận để họ không cần tương tác với hàng đợi cùng lúc.

Nói một cách đơn giản

> Bộ đệm giữa người gửi và người nhận cải thiện khả năng bảo trì và mở rộng của hệ thống. Các hàng đợi sự kiện thường được sử dụng để tổ chức và thực hiện giao tiếp giữa các quá trình (IPC).

Wikipedia nói

> Hàng đợi tin nhắn (còn gọi là hàng đợi sự kiện) thực hiện một mẫu giao tiếp bất đồng bộ giữa hai hoặc nhiều quá trình/luồng mà bên gửi và bên nhận không cần tương tác với hàng đợi cùng lúc.

**Ví dụ lập trình**

Ví dụ này cho thấy một ứng dụng sử dụng hệ thống hàng đợi sự kiện để xử lý phát lại âm thanh một cách bất đồng bộ.

Lớp `App` thiết lập một phiên bản của `Audio`, phát hai âm thanh và chờ người dùng nhập để thoát. Nó minh họa cách một hàng đợi sự kiện có thể được sử dụng để quản lý các hoạt động bất đồng bộ trong một ứng dụng phần mềm.

```java
public class App {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException,
            InterruptedException {
        var audio = Audio.getInstance();
        audio.playSound(audio.getAudioStream("./etc/Bass-Drum-1.wav"), -10.0f);
        audio.playSound(audio.getAudioStream("./etc/Closed-Hi-Hat-1.wav"), -8.0f);

        LOGGER.info("Nhấn phím Enter để dừng chương trình...");
        try (var br = new BufferedReader(new InputStreamReader(System.in))) {
            br.read();
        }
        audio.stopService();
    }
}
```

Lớp `Audio` giữ việc triển khai mẫu đơn (singleton), quản lý hàng đợi các yêu cầu phát âm thanh, và điều khiển các hoạt động của luồng để xử lý bất đồng bộ.

```java
public class Audio {
    private static final Audio INSTANCE = new Audio();

    private static final int MAX_PENDING = 16;

    private int headIndex;

    private int tailIndex;

    private volatile Thread updateThread = null;

    private final PlayMessage[] pendingAudio = new PlayMessage[MAX_PENDING];

    Audio() {}

    public static Audio getInstance() {
        return INSTANCE;
    }
}
```

Những phương thức này quản lý vòng đời của luồng được sử dụng để xử lý các sự kiện âm thanh. Các phương thức `init` và `startThread` đảm bảo rằng luồng được khởi tạo và chạy đúng cách.

```java
public synchronized void stopService() throws InterruptedException {
    if(updateThread != null) {
        updateThread.interrupt();
        updateThread.join();
        updateThread = null;
    }
}

public synchronized boolean isServiceRunning() {
    return updateThread != null && updateThread.isAlive();
}

public void init() {
    if(updateThread == null) {
        updateThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                update();
            }
        });
        startThread();
    }
}

private synchronized void startThread() {
    if (!updateThread.isAlive()) {
        updateThread.start();
        headIndex = 0;
        tailIndex = 0;
    }
}
```

Phương thức `playSound` kiểm tra xem âm thanh đã có trong hàng đợi chưa và cập nhật âm lượng hoặc thêm một yêu cầu mới vào hàng đợi, minh họa việc quản lý các tác vụ bất đồng bộ trong hàng đợi sự kiện.

```java
public void playSound(AudioInputStream stream, float volume) {
    init();
    for(var i = headIndex; i != tailIndex; i = (i + 1) % MAX_PENDING) {
        var playMessage = getPendingAudio()[i];
        if(playMessage.getStream() == stream) {
            playMessage.setVolume(Math.max(volume, playMessage.getVolume()));
            return;
        }
    }
    getPendingAudio()[tailIndex] = new PlayMessage(stream, volume);
    tailIndex = (tailIndex + 1) % MAX_PENDING;
}
```

## Khả năng áp dụng

Mẫu thiết kế này áp dụng trong các tình huống mà các tác vụ có thể được xử lý bất đồng bộ ngoài dòng chảy chính của ứng dụng, chẳng hạn như trong các ứng dụng GUI, xử lý sự kiện phía máy chủ, hoặc trong các hệ thống yêu cầu lập lịch tác vụ mà không thực hiện ngay lập tức. Cụ thể:

* Người gửi không yêu cầu phản hồi từ người nhận.
* Bạn muốn phân tách người gửi và người nhận.
* Bạn muốn xử lý các sự kiện bất đồng bộ.
* Bạn có tài nguyên hạn chế và quá trình bất đồng bộ là chấp nhận được để đạt được điều đó.

## Các ứng dụng đã biết

* Kiến trúc dựa trên sự kiện
* Các khung GUI trong Java (như Swing và JavaFX)
* Các ứng dụng máy chủ xử lý yêu cầu bất đồng bộ

## Hậu quả

Lợi ích:

* Giảm sự kết nối giữa các hệ thống.
* Tăng khả năng phản hồi của ứng dụng.
* Cải thiện khả năng mở rộng bằng cách cho phép xử lý sự kiện được phân phối qua nhiều luồng hoặc bộ xử lý.

Đánh đổi:

* Phức tạp trong việc quản lý hàng đợi sự kiện.
* Có thể gặp khó khăn trong việc theo dõi lỗi do hành vi bất đồng bộ.
* Chi phí duy trì tính toàn vẹn và hiệu suất của hàng đợi sự kiện.

## Các mẫu thiết kế liên quan

* [Command](https://java-design-patterns.com/patterns/command/) (cho việc đóng gói xử lý yêu cầu trong một đối tượng lệnh)
* [Observer](https://java-design-patterns.com/patterns/observer/) (cho việc đăng ký và thông báo thay đổi cho nhiều đối tượng quan sát)
* [Reactor](https://java-design-patterns.com/patterns/reactor/) (xử lý yêu cầu theo cách không chặn, dựa trên sự kiện, tương tự như Hàng Đợi Sự Kiện)

## Tài liệu tham khảo

* [Enterprise Integration Patterns: Designing, Building, and Deploying Messaging Solutions](https://amzn.to/3xzSlC2)
* [Game Programming Patterns](https://amzn.to/3K96fOn)
* [Java Concurrency in Practice](https://amzn.to/3Ji16mX)
* [Pattern-Oriented Software Architecture, Volume 2: Patterns for Concurrent and Networked Objects](https://amzn.to/3U2hlcy)
* [Patterns of Enterprise Application Architecture](https://amzn.to/3xtVtPJ)
* [Event Queue (Game Programming Patterns)](http://gameprogrammingpatterns.com/event-queue.html)

---

Hy vọng bản dịch này giúp bạn hiểu rõ hơn về mẫu thiết kế "Event Queue"!