# 🚀 Lộ Trình Học Java 100 Ngày (Dành Cho Developer PHP/JS)

_Mục tiêu:_ Chuyển đổi từ Senior PHP/Frontend sang Java Backend Developer.
_Thời gian:_ 100 Ngày.
_Phương pháp:_ Fast-track, tập trung vào sự khác biệt và mapping kiến thức từ Laravel/React sang hệ sinh thái Java/Spring.

---

## 📅 Giai đoạn 1: Java Core & Tư duy Static Typing (Ngày 1 - 20)

_Mục tiêu:_ Quên đi sự linh hoạt của mảng trong PHP, làm quen với sự chặt chẽ của Java OOP.

### 📚 Lý thuyết trọng tâm

#### 📁 Ngày 1: Setup & Primitive vs Wrapper → `day-01/`

- Cài đặt JDK (Java Development Kit) và IntelliJ IDEA.
- _Quan trọng:_ Phân biệt Primitive types (int, boolean) vs Wrapper Classes (Integer, Boolean).

#### 📁 Ngày 2: String Pool → `day-02/`

- Hiểu về String Pool (Tại sao so sánh chuỗi dùng .equals() chứ không dùng ==).

#### 📁 Ngày 3: StringBuilder vs StringBuffer → `day-03/`

- **String:** Immutable (Bất biến) - Nối chuỗi tạo object mới -> chậm.
- **StringBuilder:** Mutable (Thay đổi được) - Nhanh, dùng khi nối chuỗi nhiều.
- **StringBuffer:** Giống StringBuilder nhưng Thread-safe (cũ, ít dùng hơn ngoại trừ môi trường đa luồng).

#### 📁 Ngày 4: Stack vs Heap Memory → `day-04/`

- **Stack:** Lưu biến cục bộ (local variables), reference variable, method call. Tự động giải phóng khi hàm chạy xong.
- **Heap:** Lưu Object (`new ...`). Sống cho đến khi Garbage Collector (GC) dọn dẹp.

#### 📁 Ngày 5: Java Generics (Tham số hóa kiểu) → `day-05/`

- **Tại sao cần?** Tránh lỗi runtime khi làm việc với Collections (ArrayList, HashMap).
- **Generic Classes:** Tạo class có thể làm việc với bất kỳ kiểu dữ liệu nào (ví dụ: `Box<T>`).
- **Generic Methods:** Viết hàm có thể xử lý nhiều kiểu dữ liệu khác nhau.
- **Bounded Types:** Giới hạn kiểu dữ liệu (ví dụ: `<T extends Number>` chỉ chấp nhận số).
- **Wildcards:** `<?>`, `<? extends T>`, `<? super T>` (dùng khi đọc/ghi Collections).
- **Type Erasure:** Hiểu cơ chế Java xóa thông tin Generic sau compile (để tương thích ngược).

#### Ngày 6-12: OOP Hardcore

- Interface vs Abstract Class: Khi nào dùng cái nào? (Java dùng Interface nhiều hơn PHP).
- Polymorphism (Đa hình) và tính đóng gói (private, protected, public, default).
- Từ khóa static và final.

#### Ngày 13-20: Java Collections Framework

- Thay đổi tư duy: Không dùng mảng cho mọi thứ như PHP.
- List (ArrayList, LinkedList): Danh sách có thứ tự.
- Set (HashSet): Danh sách không trùng lặp.
- Map (HashMap, TreeMap): Lưu Key-Value.

### 🛠 Dự án thực hành: Console HR Management

_Yêu cầu:_ Viết ứng dụng chạy trên màn hình đen (Console).

1.  Tạo Class cha Employee, các class con Developer, Manager.
2.  Tạo Interface Workable (method work()), Manageable.
3.  Lưu danh sách nhân viên vào ArrayList.
4.  Viết hàm tìm kiếm nhân viên theo ID dùng HashMap để tối ưu tốc độ.
5.  Xử lý try-catch khi người dùng nhập tuổi là chữ cái.

---

## 📅 Giai đoạn 2: Advanced Java & Build Tools (Ngày 21 - 40)

_Mục tiêu:_ Viết code hiện đại (Java 8+) và quản lý thư viện chuyên nghiệp.

### 📚 Lý thuyết trọng tâm

- _Ngày 21-25: Build Tools (Maven/Gradle)_
  - Hiểu cấu trúc file pom.xml (Tương tự composer.json hay package.json).
  - Cách quản lý dependencies và plugins.
- _Ngày 26-35: Java 8 Features_
  - _Lambda Expressions:_ Viết code ngắn gọn (giống Arrow function trong JS).
  - _Stream API:_ Xử lý collection cực mạnh (filter, map, reduce, collect).
  - Optional: Xử lý lỗi NullPointerException (Nỗi ám ảnh của Java dev).
- _Ngày 36-40: Multithreading & IO_
  - Cơ chế đa luồng cơ bản (Thread, Runnable).
  - Đọc/Ghi file với java.nio.

### 🛠 Dự án thực hành: Log Analysis Tool

_Yêu cầu:_ Tool đọc và phân tích file log server.

1.  Đọc file log text lớn (10MB+).
2.  Sử dụng _Stream API_ để lọc ra các dòng ERROR.
3.  Group các lỗi theo ngày tháng.
4.  Ghi báo cáo tóm tắt ra file .txt mới.
5.  Sử dụng Maven để kéo thư viện (ví dụ: Apache Commons IO hoặc Lombok).

---

## 📅 Giai đoạn 3: Spring Boot - Thế giới Enterprise (Ngày 41 - 70)

_Mục tiêu:_ Mapping kiến thức từ Laravel sang Spring Boot.

### 📚 Lý thuyết trọng tâm (Mapping)

- _Ngày 41-50: Spring Core_
  - _Dependency Injection (DI) & IoC Container:_ Hiểu cách Spring quản lý Beans (Tương tự Service Container trong Laravel nhưng tự động hóa cao hơn với Annotation @Autowired, @Component).
- _Ngày 51-60: Spring Data JPA_
  - Hibernate Architecture.
  - Mapping Entity $\leftrightarrow$ Table.
  - Repository Pattern (Tương tự Eloquent Model).
- _Ngày 61-70: REST API & Security_
  - @RestController, @GetMapping, @PostMapping.
  - Spring Security & JWT (Authentication/Authorization).

### 🛠 Dự án thực hành: E-commerce Backend API

_Yêu cầu:_ Viết Backend cho trang bán hàng.

1.  Thiết kế Database: User, Product, Order, OrderDetail.
2.  Cấu hình kết nối MySQL/PostgreSQL trong application.properties.
3.  Viết API CRUD chuẩn RESTful.
4.  Validate dữ liệu đầu vào (ví dụ: email phải đúng định dạng, giá > 0).
5.  Viết Unit Test cho Service layer dùng JUnit và Mockito.

---

## 📅 Giai đoạn 4: Full-stack Integration & Deployment (Ngày 71 - 100)

_Mục tiêu:_ Kết nối với thế mạnh Frontend (Vue/React) và Deploy.

### 📚 Lý thuyết trọng tâm

- _Ngày 71-80: Architecture & Docker_
  - Dockerize ứng dụng Spring Boot (Tạo Dockerfile).
  - Khái niệm cơ bản về Microservices (API Gateway).
- _Ngày 81-90: Integration_
  - Kết nối Frontend (React/Nuxt) với Spring Boot.
  - Xử lý CORS (Cross-Origin Resource Sharing).
  - Xử lý Exception Global (trả về JSON lỗi chuẩn cho Frontend).
- _Ngày 91-100: Capstone & Deploy_
  - CI/CD cơ bản (GitHub Actions).
  - Deploy lên Cloud (AWS Free Tier, Render hoặc Heroku).

### 🛠 Dự án Capstone: Full-stack E-commerce System

_Yêu cầu:_ Sản phẩm hoàn thiện.

1.  Frontend: React/Nuxt hiển thị sản phẩm, giỏ hàng.
2.  Backend: Spring Boot xử lý logic, tính toán đơn hàng.
3.  Database: MySQL chạy trong Docker Container.
4.  Chức năng: Đăng nhập (JWT), Mua hàng, Xem lịch sử đơn hàng.

---

## 💡 Tips dành cho PHP/JS Developer học Java

1.  _Strict Typing:_ Đừng khó chịu khi compiler bắt lỗi sai kiểu dữ liệu. Nó giúp bạn tránh lỗi runtime cực tốt.
2.  _Verbose:_ Java viết dài hơn PHP/JS. Hãy làm quen với việc tạo nhiều class và file. Dùng IDE (IntelliJ) để generate code.
3.  _Compile:_ Code xong phải Compile mới chạy được. Không phải F5 trình duyệt là xong ngay như PHP.
