### Tại sao lại sinh ra AOP và tác dụng của nó trong framework

#### Nguyên nhân sinh ra AOP:

1. **Separation of Concerns (Tách biệt các mối quan tâm)**:
    - Trong quá trình phát triển phần mềm, có những mối quan tâm xuyên suốt nhiều phần của ứng dụng như logging (ghi log), transaction management (quản lý giao dịch), security (bảo mật). Những mối quan tâm này thường không liên quan trực tiếp đến logic kinh doanh nhưng cần thiết để ứng dụng hoạt động đúng đắn. Việc nhúng các mối quan tâm này vào code logic kinh doanh sẽ làm cho mã trở nên phức tạp và khó bảo trì.

2. **Code Reusability (Tái sử dụng mã)**:
    - AOP cho phép bạn viết mã liên quan đến một mối quan tâm cụ thể một lần và áp dụng nó cho nhiều nơi trong ứng dụng. Điều này giúp tăng tính tái sử dụng của mã.

3. **Maintainability (Khả năng bảo trì)**:
    - Khi các mối quan tâm được tách biệt và quản lý một cách độc lập, việc thay đổi hay bảo trì chúng sẽ trở nên dễ dàng hơn. Bạn không cần phải tìm và sửa đổi mã liên quan đến một mối quan tâm cụ thể trong toàn bộ ứng dụng.

4. **Modularity (Tính module hóa)**:
    - AOP giúp tạo ra các module mã độc lập cho các mối quan tâm cụ thể, làm cho ứng dụng trở nên dễ hiểu và dễ quản lý hơn.

#### Tác dụng của AOP trong framework:

1. **Logging (Ghi log)**:
    - Ghi lại các thông tin quan trọng về trạng thái của ứng dụng, các lỗi xảy ra hoặc các hoạt động người dùng mà không cần nhúng mã ghi log vào logic kinh doanh chính.

   ```java
   @Aspect
   @Component
   public class LoggingAspect {

       @Before("execution(* com.example.service.*.*(..))")
       public void logBefore(JoinPoint joinPoint) {
           System.out.println("Start method: " + joinPoint.getSignature().getName());
       }

       @After("execution(* com.example.service.*.*(..))")
       public void logAfter(JoinPoint joinPoint) {
           System.out.println("End method: " + joinPoint.getSignature().getName());
       }
   }
   ```

2. **Transaction Management (Quản lý giao dịch)**:
    - Quản lý giao dịch một cách tự động và nhất quán mà không cần phải viết mã quản lý giao dịch trong từng phương thức.

   ```java
   @Aspect
   @Component
   public class TransactionAspect {

       @Around("execution(* com.example.service.*.*(..))")
       public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
           TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
           Object result;
           try {
               result = joinPoint.proceed();
               transactionManager.commit(status);
           } catch (Exception e) {
               transactionManager.rollback(status);
               throw e;
           }
           return result;
       }
   }
   ```

3. **Security (Bảo mật)**:
    - Kiểm tra quyền truy cập và xác thực người dùng trước khi cho phép thực thi các phương thức nhạy cảm.

   ```java
   @Aspect
   @Component
   public class SecurityAspect {

       @Before("execution(* com.example.service.*.*(..))")
       public void checkAccess(JoinPoint joinPoint) {
           // Kiểm tra quyền truy cập của người dùng
           if (!hasAccess()) {
               throw new SecurityException("Access denied");
           }
       }

       private boolean hasAccess() {
           // Kiểm tra quyền truy cập (ví dụ: kiểm tra token, quyền user)
           return true;
       }
   }
   ```

4. **Performance Monitoring (Giám sát hiệu năng)**:
    - Theo dõi thời gian thực thi của các phương thức để tối ưu hóa hiệu năng.

   ```java
   @Aspect
   @Component
   public class PerformanceAspect {

       @Around("execution(* com.example.service.*.*(..))")
       public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
           long start = System.currentTimeMillis();
           Object result = joinPoint.proceed();
           long elapsedTime = System.currentTimeMillis() - start;
           System.out.println("Method " + joinPoint.getSignature().getName() + " executed in " + elapsedTime + "ms");
           return result;
       }
   }
   ```

Bằng cách sử dụng AOP, các mối quan tâm này được quản lý một cách hiệu quả và tách biệt khỏi logic kinh doanh chính, giúp ứng dụng trở nên sạch sẽ, dễ bảo trì và mở rộng hơn.