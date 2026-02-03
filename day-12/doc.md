# Ngày 12: OOP Tổng kết & Thực hành

> [!TIP]
> **Mental Model:**
>
> Ngày này tổng hợp lại toàn bộ kiến thức OOP từ Day 6-11.
> Mục tiêu: **Hiểu rõ khi nào dùng cái gì**, không chỉ biết syntax.

---

## 1. Tổng kết OOP trong Java

### 1.1. Bốn trụ cột OOP

| Trụ cột             | Ý nghĩa                              | Keyword/Cách dùng                   |
| :------------------ | :----------------------------------- | :---------------------------------- |
| **Encapsulation**   | Giấu data, kiểm soát truy cập        | `private`, getter/setter có logic   |
| **Inheritance**     | Kế thừa từ class cha                 | `extends`, `super`                  |
| **Polymorphism**    | Nhiều hình thái, cùng interface      | Override, Upcasting                 |
| **Abstraction**     | Ẩn chi tiết, chỉ lộ interface        | `abstract class`, `interface`       |

### 1.2. Các khái niệm đã học

| Ngày   | Chủ đề                      | Key Takeaway                                        |
| :----- | :-------------------------- | :-------------------------------------------------- |
| Day 6  | Interface vs Abstract       | Interface = "Can-Do", Abstract = "Is-A"             |
| Day 7  | Encapsulation               | `private` mọi field, kiểm soát qua method           |
| Day 8  | Polymorphism                | Biến cha chứa object con, JVM gọi đúng method       |
| Day 9  | Static & Final              | `static` = Class-level, `final` = Không đổi         |
| Day 10 | Immutability                | Object không đổi = Thread-safe, ít bug              |
| Day 11 | Composition > Inheritance   | "Has-A" linh hoạt hơn "Is-A"                        |

---

## 2. Khi nào dùng cái gì?

### 2.1. Interface vs Abstract Class

| Câu hỏi                                | Chọn               |
| :------------------------------------- | :----------------- |
| Các class không liên quan, cùng hành vi| **Interface**      |
| Các class cùng họ, chia sẻ code        | **Abstract Class** |
| Cần đa kế thừa behavior                | **Interface**      |
| Cần fields + partial implementation    | **Abstract Class** |

### 2.2. Inheritance vs Composition

| Câu hỏi                                | Chọn               |
| :------------------------------------- | :----------------- |
| Quan hệ thực sự "Is-A"                 | **Inheritance**    |
| Chỉ muốn reuse code                    | **Composition**    |
| Behavior thay đổi runtime              | **Composition**    |
| Cần dễ test, dễ maintain               | **Composition**    |

### 2.3. Mutable vs Immutable

| Câu hỏi                                | Chọn               |
| :------------------------------------- | :----------------- |
| Value Object (Money, Point)            | **Immutable**      |
| Entity Object (User, Order)            | **Mutable**        |
| Shared giữa threads                    | **Immutable**      |
| Map key, Set element                   | **Immutable**      |

---

## 3. SOLID Principles Preview

### S - Single Responsibility

Mỗi class làm **1 việc duy nhất**.

```java
// ❌ Sai: UserService làm quá nhiều
class UserService {
    void createUser() { }
    void sendEmail() { }
    void generateReport() { }
}

// ✅ Đúng: Tách ra
class UserService { void createUser() { } }
class EmailService { void sendEmail() { } }
class ReportService { void generateReport() { } }
```

### O - Open/Closed

Mở để **extend**, đóng để **modify**.

```java
// Dùng interface để extend behavior mà không sửa code cũ
interface Shape { double area(); }
class Circle implements Shape { ... }
class Square implements Shape { ... }
// Thêm Triangle? Implement Shape, không sửa code cũ!
```

### L - Liskov Substitution

Class con **thay thế** được class cha mà không gây lỗi.

```java
// ❌ Sai: Square extends Rectangle nhưng vi phạm behavior
// ✅ Đúng: Tách Square và Rectangle riêng
```

### I - Interface Segregation

**Tách nhỏ** interface, client không nên implement method không dùng.

```java
// ❌ Sai: Interface quá lớn
interface Worker {
    void work();
    void eat();
    void sleep();
}

// ✅ Đúng: Tách ra
interface Workable { void work(); }
interface Eatable { void eat(); }
```

### D - Dependency Inversion

Depend vào **abstraction**, không phải **concrete class**.

```java
// ❌ Sai: Depend vào concrete
class OrderService {
    private MySQLRepository repo = new MySQLRepository();
}

// ✅ Đúng: Depend vào interface
class OrderService {
    private final Repository repo;
    public OrderService(Repository repo) { this.repo = repo; }
}
```

---

## 4. Checklist OOP cho Java Developer

### Khi thiết kế Class:

- [ ] Fields có `private` không?
- [ ] Có cần setter không? (Ưu tiên immutable)
- [ ] Có validate input trong constructor/setter không?
- [ ] Class có làm quá nhiều việc không? (Single Responsibility)

### Khi dùng Inheritance:

- [ ] Có thực sự là quan hệ "Is-A" không?
- [ ] Class con có thay thế được class cha không? (Liskov)
- [ ] Có thể dùng Composition thay thế không?

### Khi dùng Interface:

- [ ] Interface có quá lớn không? (Interface Segregation)
- [ ] Các method có liên quan đến nhau không?
- [ ] Tên interface mô tả **hành vi** chứ không phải **danh từ**?

---

## 5. Bài tập tổng hợp

### Bài 1: Hệ thống quản lý thư viện

Thiết kế các class cho hệ thống quản lý thư viện:

1. `Book` (immutable): id, title, author, isbn
2. `Member`: id, name, borrowedBooks
3. Interface `Borrowable` với method `borrow()`, `returnBook()`
4. `LibraryService` sử dụng Composition

### Bài 2: Hệ thống thanh toán

1. Interface `PaymentProcessor` với method `process(amount)`
2. Implement: `CreditCardProcessor`, `BankTransferProcessor`
3. Class `CheckoutService` nhận `PaymentProcessor` qua constructor (DI)
4. Viết code cho phép đổi payment method runtime

### Bài 3: Refactor code xấu

Refactor đoạn code sau theo nguyên tắc OOP:

```java
// Code xấu - Refactor lại!
class Employee {
    public String name;
    public double salary;
    public String type; // "FULL_TIME" or "PART_TIME"

    public double calculateSalary() {
        if (type.equals("FULL_TIME")) {
            return salary;
        } else if (type.equals("PART_TIME")) {
            return salary * 0.5;
        }
        return 0;
    }
}
```

---

## 6. Tổng kết cuối cùng

> [!IMPORTANT]
> **Tư duy OOP của Senior Java:**
>
> 1. **Encapsulation first:** Giấu hết, mở dần khi cần.
> 2. **Interface-driven:** Design interface trước, implement sau.
> 3. **Composition > Inheritance:** Ưu tiên "có" hơn "là".
> 4. **Immutability by default:** `final` mọi nơi có thể.
> 5. **Single Responsibility:** Mỗi class 1 lý do để thay đổi.

**Bạn đã hoàn thành phần OOP cơ bản!** Tiếp theo là Collections Framework và Testing.
