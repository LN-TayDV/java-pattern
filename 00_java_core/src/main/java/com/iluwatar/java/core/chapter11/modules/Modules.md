- Modules (Các module)
    - Modular JDK (JDK có tính module)
        - JDK Modules (Các module trong JDK)
            - java.base
            - java.desktop
            - java.sql
            - java.logging
            - ...
        - Modular JAR Files (Các file JAR có tính module)
            - Định nghĩa module-info.java
            - Export và Import Packages
            - Mối quan hệ giữa các module
    - Declaring Modules (Khai báo module)
        - module-info.java
            - `module` statement
            - `requires` directive
            - `exports` directive
            - `opens` directive
            - `provides` và `uses` directives (cho Service Providers)
    - Enabling Access Between Modules (Cho phép truy cập giữa các module)
        - Tạo và quản lý `module-path`
        - Sử dụng `--module-path` và `--module` khi biên dịch và chạy ứng dụng
    - Compiling and Running Modular Projects (Biên dịch và chạy dự án modular)
        - Sử dụng `javac` để biên dịch module
        - Sử dụng `java` để chạy ứng dụng module

### Ví dụ minh họa

#### 1. Khai báo module-info.java

```java
module com.example.mymodule {
    requires java.base;
    requires java.sql;
    exports com.example.mymodule.services;
}
