## 📦 Câu hỏi phỏng vấn về Microservices kèm đáp án mở rộng (Tiếng Việt)

---

### 🌱 **Cơ bản**

**1. Microservice là gì? So sánh với kiến trúc Monolithic.**
- **Đáp án:**
  Microservices là kiểu kiến trúc phần mềm mà trong đó hệ thống được chia thành nhiều dịch vụ nhỏ, độc lập, giao tiếp với nhau qua giao thức nhẹ như HTTP/REST. Kiến trúc Monolithic là kiểu xây dựng toàn bộ ứng dụng trong một khối duy nhất.
    - **Khác biệt chính:**
        - Triển khai: Microservices triển khai độc lập, Monolithic thì không.
        - Quản lý: Microservices dễ mở rộng, khó debug hơn Monolithic.
    - **Thư viện mở rộng:** Spring Boot (Spring Cloud), Micronaut, Quarkus – giúp dễ dàng triển khai service độc lập và có sẵn nhiều cấu hình phù hợp với mô hình phân tán.

**2. Lợi ích chính của Microservices?**
- **Đáp án:**
    - Triển khai và phát triển độc lập.
    - Dễ scale theo từng thành phần.
    - Dễ thay thế công nghệ theo từng module.
    - Tăng khả năng chịu lỗi.
    - **Thư viện hỗ trợ:** Kubernetes (triển khai & scale), Docker (đóng gói service), Spring Cloud (cấu hình, discovery, gateway).

**3. Các thành phần phổ biến trong Microservices?**
- **Đáp án:**
    - Service Registry (Eureka, Consul)
    - API Gateway (Zuul, Spring Cloud Gateway, Kong)
    - Config Server (Spring Cloud Config)
    - Load Balancer (Ribbon, Envoy, NGINX)
    - Message Queue (Kafka, RabbitMQ)
    - **Tại sao nên dùng:** Các thư viện này giúp đảm bảo khả năng mở rộng, khả năng tự phát hiện (service discovery), tách cấu hình và hỗ trợ giao tiếp bất đồng bộ.

**4. Service Discovery là gì?**
- **Đáp án:**
  Cơ chế giúp các service tự động tìm thấy nhau trong hệ thống phân tán. VD: Eureka, Consul.
    - **Tại sao nên dùng:** Tránh hard-code endpoint, dễ scale.

**5. API Gateway là gì?**
- **Đáp án:**
  Là điểm đầu vào duy nhất cho client, chuyển tiếp request đến các service tương ứng. Có thể xử lý xác thực, routing, throttling, logging.
    - **Thư viện:** Spring Cloud Gateway, Zuul, Kong, Ambassador.

**6. Stateless vs Stateful?**
- **Đáp án:**
    - Stateless không lưu trạng thái giữa các request → dễ scale.
    - Stateful lưu trạng thái → khó mở rộng, cần sticky session.
    - **Thư viện hỗ trợ cache và lưu trạng thái tạm thời:** Redis (lưu session tạm), Hazelcast.

**7. Microservices giao tiếp nhau như thế nào?**
- **Đáp án:**
    - Đồng bộ: REST, gRPC (hiệu quả hơn, hỗ trợ schema contract).
    - Bất đồng bộ: Kafka, RabbitMQ (giảm coupling, tăng resilience).
    - **Tại sao nên dùng:** gRPC giúp giảm độ trễ và định nghĩa rõ schema; Kafka tăng throughput và dễ dàng mở rộng.

---

### ⚙️ **Trung bình**

**1. Quản lý database trong Microservices?**
- **Đáp án:**
  Mỗi service nên có DB riêng (Database per service) để tránh phụ thuộc. Dùng event để đồng bộ dữ liệu nếu cần.
    - **Thư viện/Công cụ hỗ trợ:** Debezium (CDC - change data capture), Kafka (event sourcing).

**2. Authentication/Authorization?**
- **Đáp án:**
    - Dùng JWT + OAuth2.
    - API Gateway xử lý xác thực, forward token đến service.
    - **Thư viện:** Spring Security + Spring Cloud Gateway, Keycloak, Auth0.

**3. Circuit Breaker, Retry, Fallback?**
- **Đáp án:**
    - Circuit Breaker: tránh gọi service bị lỗi liên tục (Netflix Hystrix, Resilience4j).
    - Retry: thử lại sau khi lỗi.
    - Fallback: xử lý thay thế khi lỗi.
    - **Tại sao nên dùng:** Tăng khả năng chịu lỗi cho hệ thống, ngăn tình trạng cascading failure.

**4. Distributed Transaction?**
- **Đáp án:**
    - Không dùng transaction truyền thống.
    - Dùng Saga hoặc event-based transaction để đảm bảo nhất quán.
    - **Thư viện:** Axon Framework, Eventuate Tram Saga.

**5. Đồng bộ dữ liệu?**
- **Đáp án:**
    - Event-driven architecture, Kafka, CDC (Change Data Capture).
    - **Tại sao nên dùng:** Hạn chế coupling và tăng khả năng mở rộng.

**6. API versioning?**
- **Đáp án:**
    - Sử dụng version trong URL (/v1/product).
    - Hoặc trong header (Accept: application/vnd.api.v1+json).
    - **Thư viện hỗ trợ:** Swagger/OpenAPI – quản lý document theo version.

**7. Logging/Tracing/Monitoring?**
- **Đáp án:**
    - Logging: ELK stack (Elasticsearch, Logstash, Kibana).
    - Tracing: Zipkin, Jaeger.
    - Monitoring: Prometheus, Grafana.
    - **Tại sao nên dùng:** Theo dõi toàn hệ thống, debug và tối ưu dễ dàng hơn.

---

### 🚀 **Nâng cao**

**1. Saga pattern là gì?**
- **Đáp án:**
  Là chuỗi các transaction nhỏ, nếu lỗi thì có bước rollback tương ứng. Có 2 dạng: Choreography và Orchestration.
    - **Thư viện:** Axon, Camunda, Eventuate Tram.
    - **Tại sao nên dùng:** Đảm bảo eventual consistency trong hệ phân tán.

**2. Message broker nào nên dùng?**
- **Đáp án:**
    - Kafka: throughput cao, dùng cho event streaming.
    - RabbitMQ: dễ dùng, tốt cho hệ thống nhỏ hoặc cần message order.
    - **Tại sao nên dùng:** Kafka có khả năng lưu trữ lâu, giúp xử lý lại message, RabbitMQ phù hợp cho low-latency.

**3. Scale Microservices?**
- **Đáp án:**
    - Horizontal scaling.
    - Dùng container (Docker) + orchestration (Kubernetes).
    - **Tại sao nên dùng:** Kubernetes tự động cân bằng tải, restart container khi lỗi.

**4. Failover/Lỗi mạng?**
- **Đáp án:**
    - Retry logic, timeout, Circuit Breaker.
    - Load Balancer chuyển hướng request.
    - **Thư viện:** Resilience4j, Ribbon, Istio.

**5. Eventual consistency?**
- **Đáp án:**
    - Chấp nhận dữ liệu không đồng bộ ngay.
    - Dùng event sourcing, message queue.
    - **Tại sao nên dùng:** Phù hợp hệ thống lớn, tránh deadlock.

**6. Zero-downtime deployment?**
- **Đáp án:**
    - Blue/Green deployment.
    - Canary release.
    - **Công cụ hỗ trợ:** Spinnaker, ArgoCD.

**7. Quản lý cấu hình tập trung?**
- **Đáp án:**
    - Spring Cloud Config, Consul, ZooKeeper.
    - **Tại sao nên dùng:** Giúp quản lý cấu hình nhiều service từ một nơi duy nhất, dễ dàng thay đổi khi triển khai.

