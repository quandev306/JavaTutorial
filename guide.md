# 🚀 Lộ Trình Học Java 100 Ngày (Dành Cho Developer PHP/JS)

_Mục tiêu:_ Chuyển đổi từ Senior PHP/Frontend sang Java Backend Developer.
_Thời gian:_ 100 Ngày.
_Phương pháp:_ Xây dựng mental model như senior Java từ đầu, **KHÔNG map 1-1 từ PHP sang Java**.

---

## 🧠 Mental Model - Tư Duy Cốt Lõi (ĐỌC TRƯỚC KHI HỌC)

> [!CAUTION] > **Sai lầm lớn nhất:** Viết Java bằng tư duy PHP. Không mapping 1-1!

### 1️⃣ Runtime & Memory (PHP vs Java)

| PHP                                        | Java                                            |
| ------------------------------------------ | ----------------------------------------------- |
| Request-based: Mỗi request = khởi động lại | JVM sống lâu: Ứng dụng chạy liên tục hàng tháng |
| Memory tự giải phóng sau request           | Phải hiểu Heap/Stack, GC, memory leak           |
| Không quan tâm thread                      | Thread pool, concurrency là lõi                 |

**→ Tư duy:** Ứng dụng Java = hệ thống dài hạn. Mỗi dòng code ảnh hưởng performance trong months, không phải seconds.

### 2️⃣ OOP Kỷ Luật

| PHP OOP                       | Java OOP                                      |
| ----------------------------- | --------------------------------------------- |
| Class = nơi gom code tiện tay | Class = đại diện concept ổn định trong domain |
| Interface ít dùng             | Interface = contract bắt buộc, dùng khắp nơi  |
| Mutable mọi thứ               | Ưu tiên Immutability (final, không setter)    |

**→ Tư duy:** Thiết kế theo domain, không phải theo tính năng.

### 3️⃣ Concurrency & Async

| PHP                           | Java                                                  |
| ----------------------------- | ----------------------------------------------------- |
| Đơn luồng, dùng queue nếu cần | Thread, Executor, Future, CompletableFuture, Reactive |
| Race condition? Hiếm gặp      | Race condition = bug phổ biến, khó debug              |

**→ Tư duy:** Code concurrent-safe từ đầu. Hiểu synchronized, volatile, Atomic.

### 4️⃣ Kiến Trúc

| PHP/Laravel                            | Java/Spring                                         |
| -------------------------------------- | --------------------------------------------------- |
| Fat Controller, logic trong Controller | Controller chỉ nhận request, delegate xuống Service |
| Business trong Model (Eloquent)        | Business logic độc lập framework (Domain layer)     |

**→ Tư duy:** Clean Architecture / Hexagonal. Business logic KHÔNG phụ thuộc Spring.

### 5️⃣ Tư Duy Hệ Sinh Thái

| PHP Mindset            | Java Mindset                         |
| ---------------------- | ------------------------------------ |
| Viết nhanh, ship nhanh | Code rõ ràng, bảo trì 5-10 năm       |
| Một dev làm tất        | Team lớn, convention quan trọng      |
| Test? Tùy tâm          | Test là bắt buộc (Unit, Integration) |

---

## 📅 Giai đoạn 1: Java Core & Tư duy JVM (Ngày 1 - 25)

_Mục tiêu:_ Hiểu JVM runtime, memory model, và OOP kỷ luật. **Không còn tư duy request-based như PHP.**

### 📚 Lý thuyết trọng tâm

#### 📁 Ngày 1: Setup & Primitive vs Wrapper → `day-01/`

- Cài đặt JDK (Java Development Kit) và IntelliJ IDEA.
- _Quan trọng:_ Phân biệt Primitive types (int, boolean) vs Wrapper Classes (Integer, Boolean).
- Autoboxing/Unboxing: Java tự động chuyển đổi giữa primitive và wrapper.

#### 📁 Ngày 2: String Pool → `day-02/`

- Hiểu về String Pool (Tại sao so sánh chuỗi dùng .equals() chứ không dùng ==).
- String Immutability: Tại sao String bất biến là điều tốt cho memory và thread-safety.

#### 📁 Ngày 3: StringBuilder vs StringBuffer → `day-03/`

- **String:** Immutable (Bất biến) - Nối chuỗi tạo object mới -> chậm.
- **StringBuilder:** Mutable (Thay đổi được) - Nhanh, dùng khi nối chuỗi nhiều.
- **StringBuffer:** Giống StringBuilder nhưng Thread-safe (cũ, ít dùng hơn ngoại trừ môi trường đa luồng).

#### 📁 Ngày 4: Stack vs Heap Memory → `day-04/`

- **Stack:** Lưu biến cục bộ (local variables), reference variable, method call. Tự động giải phóng khi hàm chạy xong.
- **Heap:** Lưu Object (`new ...`). Sống cho đến khi Garbage Collector (GC) dọn dẹp.
- **JVM là gì?** Tại sao ứng dụng Java sống hàng tháng, không phải hàng giây như PHP.

> [!TIP] > **Mental model:** Mỗi object tồn tại trong Heap cho đến khi GC dọn. Memory leak là vấn đề thực sự trong Java.

#### 📁 Ngày 5: Java Generics → `day-05/`

- **Tại sao cần?** Tránh lỗi runtime khi làm việc với Collections (ArrayList, HashMap).
- **Generic Classes:** Tạo class có thể làm việc với bất kỳ kiểu dữ liệu nào (ví dụ: `Box<T>`).
- **Generic Methods:** Viết hàm có thể xử lý nhiều kiểu dữ liệu khác nhau.
- **Bounded Types:** Giới hạn kiểu dữ liệu (ví dụ: `<T extends Number>` chỉ chấp nhận số).
- **Wildcards:** `<?>`, `<? extends T>`, `<? super T>` (dùng khi đọc/ghi Collections).
- **Type Erasure:** Hiểu cơ chế Java xóa thông tin Generic sau compile (để tương thích ngược).

#### Ngày 6-12: OOP Kỷ Luật (Không phải OOP PHP)

- Interface vs Abstract Class: Khi nào dùng cái nào? (Java dùng Interface nhiều hơn PHP).
- **Interface = Contract:** Mô tả hành vi, không phải nơi gom code.
- **Class = Domain Concept:** Đại diện khái niệm ổn định, không phải nơi viết logic tiện tay.
- Polymorphism (Đa hình) và tính đóng gói (private, protected, public, default).
- Từ khóa static và final.
- **Immutability:** Ưu tiên `final`, hạn chế setter.
- **Composition over Inheritance:** Tại sao Java dev ít dùng extends.

> [!IMPORTANT] > **Khác PHP:** Không viết Fat Model. Business logic tách khỏi Entity.

#### Ngày 13-18: Generics & Collections

- **Collections Framework:** List, Set, Map - chọn đúng cấu trúc dữ liệu.
- List (ArrayList, LinkedList): Danh sách có thứ tự.
- Set (HashSet): Danh sách không trùng lặp.
- Map (HashMap, TreeMap): Lưu Key-Value.
- **Immutable Collections:** `List.of()`, `Map.of()` (Java 9+).

#### Ngày 19-25: Testing Culture (Đặt nền tảng sớm!)

- **JUnit 5:** Viết unit test cho mọi class.
- **Test-first mindset:** Viết test trước khi viết code.
- **Assertions:** assertEquals, assertTrue, assertThrows.
- **Tại sao quan trọng?** Java code chạy dài hạn, refactor liên tục, test là lưới an toàn.

### 🛠 Dự án thực hành: Console HR Management + Unit Tests

_Yêu cầu:_ Viết ứng dụng chạy trên Console với Unit Tests.

1. Tạo Class cha Employee, các class con Developer, Manager.
2. Tạo Interface Workable (method work()), Manageable.
3. Lưu danh sách nhân viên vào ArrayList.
4. Viết hàm tìm kiếm nhân viên theo ID dùng HashMap để tối ưu tốc độ.
5. Xử lý try-catch khi người dùng nhập tuổi là chữ cái.
6. **Viết Unit Test** cho các method quan trọng.

---

## 📅 Giai đoạn 2: Clean Architecture & Modern Java (Ngày 26 - 50)

_Mục tiêu:_ Tư duy kiến trúc **TRƯỚC KHI học framework**. Business logic độc lập.

### 📚 Lý thuyết trọng tâm

#### Ngày 26-32: Clean Architecture / Hexagonal

> [!CAUTION] > **Không học Spring trước kiến trúc!** Sẽ viết code phụ thuộc framework.

- **Layered Architecture:** Controller → Service → Repository
- **Dependency Rule:** Lớp trong không biết lớp ngoài
- **Domain Layer:** Business logic thuần Java, không import Spring/JPA
- **Ports & Adapters:** Interface ở domain, implementation ở infrastructure

#### Ngày 33-38: Java 8+ Features

- **Lambda & Functional Interface:** Code ngắn gọn, declarative
- **Stream API:** filter, map, reduce - không cần for loop
- **Optional:** Không còn null check khắp nơi
- **Records (Java 14+):** Immutable data class dễ dàng

#### Ngày 39-45: Concurrency Cơ Bản

> [!WARNING] > **Đây là phần PHP dev HAY Bỏ QUA!** Java = đa luồng, phải hiểu.

- **Thread & Runnable:** Tạo và quản lý thread
- **Race Condition:** Tại sao cùng 1 biến, 2 thread sửa = bug
- **synchronized & volatile:** Đồng bộ hóa truy cập
- **ExecutorService:** Thread pool - không tạo thread thủ công
- **CompletableFuture:** Async/await kiểu Java

#### Ngày 46-50: Build Tools & Dependency Management

- **Maven/Gradle:** Quản lý dependencies, build lifecycle
- **pom.xml / build.gradle:** Cấu trúc project chuẩn

### 🛠 Dự án thực hành: Library System (Pure Java, No Framework)

1. **Domain Layer:** `Book`, `Member`, `Loan` - thuần Java, không annotation
2. **Use Cases:** `BorrowBookUseCase`, `ReturnBookUseCase` - business rules
3. **Repository Interface:** Ở domain, implementation fake (in-memory)
4. **Unit Tests:** 80%+ coverage với JUnit + Mockito
5. **Concurrency:** Nhiều member mượn cùng 1 sách - xử lý race condition

---

## 📅 Giai đoạn 3: Spring Boot - Áp dụng Kiến Trúc (Ngày 51 - 80)

_Mục tiêu:_ Dùng Spring Boot như **tool**, không phải như kiến trúc. Business logic vẫn ở Domain layer.

> [!IMPORTANT] > **Không mapping từ Laravel!** Spring khác hohoàn toàn về tư duy.

### 📚 Lý thuyết trọng tâm

#### Ngày 51-58: Spring Core & DI

- **IoC Container:** Spring quản lý lifecycle của Beans
- **Dependency Injection:** Constructor injection (không dùng @Autowired trên field)
- **Bean Scope:** Singleton vs Prototype - khác PHP mỗi request tạo mới
- **Profiles & Configuration:** Environment-specific settings

#### Ngày 59-68: Spring Data JPA

- **Entity vs Domain:** Entity = persistence, Domain = business logic
- **Repository Pattern:** Interface, Spring tự implement
- **N+1 Problem:** Hiểu và tránh - đây là bug phổ biến
- **Transaction:** @Transactional - khi nào dùng, ở đâu

#### Ngày 69-75: REST API & Validation

- **Controller:** Chỉ nhận request, delegate xuống Use Case
- **DTO vs Entity:** Không expose Entity ra API
- **Bean Validation:** @Valid, custom validators
- **Exception Handling:** @ControllerAdvice - xử lý lỗi tập trung

#### Ngày 76-80: Security & Testing

- **Spring Security:** Authentication, Authorization
- **JWT:** Stateless authentication
- **Integration Tests:** @SpringBootTest, TestContainers
- **MockMvc:** Test API không cần server thật

### 🛠 Dự án thực hành: E-commerce API (Clean Architecture)

1. **Domain Layer:** `Product`, `Order`, `OrderItem` - không có @Entity
2. **Use Cases:** `PlaceOrderUseCase`, `CancelOrderUseCase`
3. **Infrastructure:** JPA Entities, Repositories - implement ports
4. **API Layer:** REST Controllers, DTOs, Mappers
5. **Tests:** Unit (domain) + Integration (API) - **80%+ coverage**

---

## 📅 Giai đoạn 4: Production-Ready & Vận Hành (Ngày 81 - 100)

_Mục tiêu:_ Tư duy vận hành dài hạn. Code cho team 5 người maintain 5 năm.

### 📚 Lý thuyết trọng tâm

#### Ngày 81-86: Observability & Monitoring

- **Logging:** SLF4J + Logback - structured logging
- **Metrics:** Micrometer + Prometheus
- **Health Checks:** Actuator endpoints
- **Distributed Tracing:** Sleuth/Zipkin cơ bản

#### Ngày 87-92: Containerization & CI/CD

- **Docker:** Multi-stage builds, layer caching
- **Docker Compose:** Local development environment
- **GitHub Actions:** Build, test, deploy pipeline
- **Environment đồng bộ:** Dev/Staging/Prod config

#### Ngày 93-97: Advanced Concurrency & Performance

- **Virtual Threads (Java 21):** Scalability mới
- **Connection Pooling:** HikariCP configuration
- **Caching:** Redis/Caffeine strategies
- **Performance Testing:** JMeter/Gatling cơ bản

#### Ngày 98-100: Capstone Review & Refactor

- **Code Review:** Áp dụng mental models vào code cũ
- **Documentation:** README, API docs, ADRs
- **Tech Debt:** Nhận diện và lên kế hoạch xử lý

### 🛠 Dự án Capstone: Production-Ready E-commerce

1. **Frontend:** React/Vue kết nối với API
2. **Backend:** Clean Architecture + Spring Boot
3. **Database:** PostgreSQL + Redis cache
4. **Deployment:** Docker + GitHub Actions + Cloud (AWS/Render)
5. **Monitoring:** Logging + Metrics + Health checks
6. **Documentation:** API docs + Architecture Decision Records

---

## 💡 Nguyên Tắc Vàng Cho PHP Developer Học Java

### ❌ ĐỪNG:

1. **Map 1-1 từ PHP:** Eloquent ≠ JPA, Laravel Service Container ≠ Spring IoC
2. **Viết Fat Controller/Service:** Logic phải ở Domain layer
3. **Bỏ qua Concurrency:** Java = đa luồng, race condition là thật
4. **Copy-paste code chạy được:** Compiler pass ≠ code tốt

### ✅ HÃY:

1. **Học mental model trước syntax:** Hiểu TẠI SAO, không chỉ CÁI GÌ
2. **Embrace Immutability:** `final` mọi nơi có thể, tránh setter
3. **Viết test ngay từ đầu:** JUnit + Mockito = bạn thân
4. **Đọc source code Spring/JDK:** Học cách senior Java viết code
5. **Tư duy vận hành dài hạn:** Code cho team 5 người maintain 5 năm
