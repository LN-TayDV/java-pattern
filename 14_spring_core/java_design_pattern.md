## 🎯 Câu hỏi phỏng vấn về Java Design Patterns từ cơ bản đến nâng cao (Tiếng Việt kèm ví dụ mã nguồn)

---

### 🌱 **Cơ bản**

**1. Design Pattern là gì? Có mấy loại?**
- **Đáp án:**
  Design Pattern là các giải pháp thiết kế được tổng hợp lại nhằm giải quyết các vấn đề lặp đi lặp lại trong phát triển phần mềm. Có 3 nhóm chính:
    - Creational (Khởi tạo)
    - Structural (Cấu trúc)
    - Behavioral (Hành vi)

**2. Singleton Pattern là gì? Dùng khi nào?**
- **Đáp án:**
  Đảm bảo một class chỉ có duy nhất một instance và cung cấp điểm truy cập toàn cục. Dùng cho các tài nguyên chung như logger, config.
    - **Mã ví dụ:**
  ```java
  public class Singleton {
      private static Singleton instance;
      private Singleton() {}
      public static synchronized Singleton getInstance() {
          if (instance == null) {
              instance = new Singleton();
          }
          return instance;
      }
  }
  ```

**3. Factory Method Pattern là gì?**
- **Đáp án:**
  Cung cấp interface để tạo đối tượng nhưng để lớp con quyết định khởi tạo đối tượng cụ thể nào.
    - **Mã ví dụ:**
  ```java
  interface Product {
      void use();
  }
  class ConcreteProductA implements Product {
      public void use() { System.out.println("Product A"); }
  }
  class Factory {
      public static Product createProduct(String type) {
          if ("A".equals(type)) return new ConcreteProductA();
          return null;
      }
  }
  ```

**4. Builder Pattern là gì?**
- **Đáp án:**
  Giúp tạo các đối tượng phức tạp bằng cách chia nhỏ quá trình khởi tạo thành nhiều bước.
    - **Mã ví dụ:**
  ```java
  class Person {
      private String name;
      private int age;
      public static class Builder {
          private String name;
          private int age;
          public Builder name(String name) { this.name = name; return this; }
          public Builder age(int age) { this.age = age; return this; }
          public Person build() { return new Person(name, age); }
      }
      private Person(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }
  ```

**5. Prototype Pattern là gì?**
- **Đáp án:**
  Tạo đối tượng mới bằng cách sao chép từ đối tượng đã có.
    - **Mã ví dụ:**
  ```java
  class Prototype implements Cloneable {
      public int value;
      public Prototype clone() throws CloneNotSupportedException {
          return (Prototype) super.clone();
      }
  }
  ```

---

### ⚙️ **Trung bình**

**1. Adapter Pattern là gì?**
- **Đáp án:**
  Chuyển đổi interface của một lớp thành interface khác mà client mong muốn.
    - **Mã ví dụ:**
  ```java
  interface Target {
      void request();
  }
  class Adaptee {
      void specificRequest() {
          System.out.println("Request from Adaptee");
      }
  }
  class Adapter implements Target {
      private Adaptee adaptee = new Adaptee();
      public void request() {
          adaptee.specificRequest();
      }
  }
  ```

**2. Observer Pattern là gì?**
- **Đáp án:**
  Một đối tượng thay đổi → tất cả đối tượng phụ thuộc được thông báo.
    - **Mã ví dụ:**
  ```java
  interface Observer {
      void update(String msg);
  }
  class Subject {
      private List<Observer> observers = new ArrayList<>();
      void addObserver(Observer o) { observers.add(o); }
      void notifyAll(String msg) {
          for (Observer o : observers) o.update(msg);
      }
  }
  ```

**3. Strategy Pattern là gì?**
- **Đáp án:**
  Cho phép chọn thuật toán tại runtime bằng cách đóng gói các thuật toán như là object.
    - **Mã ví dụ:**
  ```java
  interface Strategy {
      int operate(int a, int b);
  }
  class AddStrategy implements Strategy {
      public int operate(int a, int b) { return a + b; }
  }
  class Context {
      private Strategy strategy;
      public Context(Strategy strategy) { this.strategy = strategy; }
      public int execute(int a, int b) { return strategy.operate(a, b); }
  }
  ```

**4. Template Method Pattern là gì?**
- **Đáp án:**
  Xác định khung xử lý trong method, một phần để subclass override.
    - **Mã ví dụ:**
  ```java
  abstract class Game {
      abstract void start();
      abstract void end();
      public void play() {
          start();
          System.out.println("Playing...");
          end();
      }
  }
  class Football extends Game {
      void start() { System.out.println("Start football"); }
      void end() { System.out.println("End football"); }
  }
  ```

**5. Decorator Pattern là gì?**
- **Đáp án:**
  Thêm hành vi cho object mà không thay đổi cấu trúc.
    - **Mã ví dụ:**
  ```java
  interface Component {
      void operation();
  }
  class ConcreteComponent implements Component {
      public void operation() { System.out.println("Base operation"); }
  }
  class Decorator implements Component {
      private Component component;
      public Decorator(Component component) { this.component = component; }
      public void operation() {
          component.operation();
          System.out.println("Extra behavior");
      }
  }
  ```

---

👉 **Tiếp tục cập nhật phần nâng cao kèm mã nguồn? Cứ nhắn nhé!**

