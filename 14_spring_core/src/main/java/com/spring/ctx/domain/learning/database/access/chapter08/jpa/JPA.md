### Java Persistence API (JPA)

**JPA (Java Persistence API)** là một API trong Java EE (hiện tại Jakarta EE) dành cho quản lý sự tồn tại và truy vấn dữ liệu trong các ứng dụng Java. JPA cung cấp một cách thức chuẩn để ánh xạ các đối tượng Java với các bảng trong cơ sở dữ liệu quan hệ và quản lý giao dịch dữ liệu.

### **Các Thành Phần Chính của JPA**

1. **EntityManager:**
    - **EntityManager** là đối tượng chính trong JPA để quản lý các thực thể (entities). Nó cung cấp các phương thức để thực hiện các thao tác CRUD (Create, Read, Update, Delete) và quản lý các giao dịch.
    - Phương thức chính của `EntityManager` bao gồm:
        - `persist()`: Lưu một entity vào cơ sở dữ liệu.
        - `merge()`: Cập nhật một entity đã tồn tại.
        - `remove()`: Xóa một entity khỏi cơ sở dữ liệu.
        - `find()`: Tìm một entity bằng khóa chính.
        - `createQuery()`: Tạo và thực hiện các truy vấn JPQL (Java Persistence Query Language).

2. **EntityTransaction:**
    - **EntityTransaction** được sử dụng để quản lý các giao dịch trong JPA. Bạn có thể bắt đầu, commit và rollback giao dịch bằng đối tượng này.

3. **Persistence Context:**
    - **Persistence Context** là một không gian quản lý các entity, đảm bảo rằng các entity được quản lý trong một phiên giao dịch duy nhất là nhất quán.

4. **Entity:**
    - Một lớp Java được ánh xạ với một bảng trong cơ sở dữ liệu. Các thuộc tính của lớp ánh xạ với các cột trong bảng.

5. **EntityManagerFactory:**
    - **EntityManagerFactory** tạo và quản lý các EntityManager. Nó được cấu hình từ tập tin cấu hình JPA (persistence.xml) hoặc qua cấu hình Java.

6. **Query API:**
    - JPA cung cấp các API để thực hiện các truy vấn đối tượng. Bạn có thể sử dụng JPQL hoặc Criteria API để viết và thực hiện các truy vấn.

### **Mô Hình Class và Annotation trong JPA**

1. **Entity Class:**
   ```java
   @Entity
   @Table(name = "employee")
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @Column(name = "first_name")
       private String firstName;

       @Column(name = "last_name")
       private String lastName;

       // Constructors, Getters, and Setters
   }
   ```

    - **@Entity:** Đánh dấu lớp là một thực thể JPA.
    - **@Table:** Chỉ định tên bảng trong cơ sở dữ liệu mà lớp ánh xạ đến.
    - **@Id:** Chỉ định thuộc tính là khóa chính của bảng.
    - **@GeneratedValue:** Chỉ định cách sinh giá trị cho khóa chính (AUTO, SEQUENCE, IDENTITY).
    - **@Column:** Chỉ định tên cột trong cơ sở dữ liệu ánh xạ với thuộc tính.

2. **One-to-One Relationship:**
   ```java
   @Entity
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToOne(mappedBy = "employee")
       private Address address;

       // Constructors, Getters, and Setters
   }

   @Entity
   public class Address {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToOne
       @JoinColumn(name = "employee_id")
       private Employee employee;

       // Constructors, Getters, and Setters
   }
   ```

    - **@OneToOne:** Đánh dấu quan hệ một-một giữa hai thực thể.
    - **@JoinColumn:** Chỉ định cột ngoại khóa trong bảng.

3. **One-to-Many Relationship:**
   ```java
   @Entity
   public class Department {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @OneToMany(mappedBy = "department")
       private List<Employee> employees;

       // Constructors, Getters, and Setters
   }

   @Entity
   public class Employee {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToOne
       @JoinColumn(name = "department_id")
       private Department department;

       // Constructors, Getters, and Setters
   }
   ```

    - **@OneToMany:** Đánh dấu quan hệ một-nhiều giữa hai thực thể.
    - **@ManyToOne:** Đánh dấu quan hệ nhiều-một giữa hai thực thể.

4. **Many-to-Many Relationship:**
   ```java
   @Entity
   public class Student {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToMany
       @JoinTable(
           name = "student_course",
           joinColumns = @JoinColumn(name = "student_id"),
           inverseJoinColumns = @JoinColumn(name = "course_id")
       )
       private Set<Course> courses;

       // Constructors, Getters, and Setters
   }

   @Entity
   public class Course {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

       @ManyToMany(mappedBy = "courses")
       private Set<Student> students;

       // Constructors, Getters, and Setters
   }
   ```

    - **@ManyToMany:** Đánh dấu quan hệ nhiều-nhiều giữa hai thực thể.
    - **@JoinTable:** Chỉ định bảng liên kết trong cơ sở dữ liệu để lưu trữ quan hệ nhiều-nhiều.

5. **JPQL (Java Persistence Query Language):**
   ```java
   String jpql = "SELECT e FROM Employee e WHERE e.lastName = :lastName";
   TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
   query.setParameter("lastName", "Doe");
   List<Employee> employees = query.getResultList();
   ```

    - JPQL là ngôn ngữ truy vấn tương tự SQL nhưng làm việc với các đối tượng JPA.

### **Cấu Hình JPA**

- **persistence.xml:** Tập tin cấu hình chính cho JPA, định nghĩa `persistence-unit` và các thuộc tính kết nối cơ sở dữ liệu.
  ```xml
  <persistence xmlns="http://xmlns.jcp.org/xml/ns/persistence"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence
                                   http://xmlns.jcp.org/xml/ns/persistence/persistence_2_1.xsd"
               version="2.1">
      <persistence-unit name="myPersistenceUnit">
          <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
          <class>com.example.Employee</class>
          <properties>
              <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/mydb"/>
              <property name="javax.persistence.jdbc.user" value="root"/>
              <property name="javax.persistence.jdbc.password" value="password"/>
              <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
              <property name="hibernate.hbm2ddl.auto" value="update"/>
          </properties>
      </persistence-unit>
  </persistence>
  ```

- **Java Configuration:** Bạn có thể cấu hình JPA bằng cách sử dụng Java Config thay cho `persistence.xml`.

Hy vọng rằng giải thích chi tiết và mô hình lớp này giúp bạn hiểu rõ hơn về JPA và cách sử dụng nó trong các ứng dụng Java! Nếu bạn cần thêm thông tin hoặc có câu hỏi cụ thể, đừng ngần ngại hỏi nhé.