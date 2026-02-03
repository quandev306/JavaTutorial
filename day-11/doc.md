# Ngày 11: Composition over Inheritance

> [!TIP]
> **Mental Model:**
>
> - **Inheritance (Kế thừa):** "Is-A" - Con LÀ cha. Chặt chẽ, khó thay đổi.
> - **Composition (Tổ hợp):** "Has-A" - Object CHỨA object khác. Linh hoạt, dễ thay đổi.
>
> **Java Senior:** Ưu tiên Composition. Chỉ dùng Inheritance khi thực sự cần quan hệ "Is-A".

---

## 1. Vấn đề với Inheritance

### 1.1. Tight Coupling (Liên kết chặt)

```java
class Bird {
    void fly() { System.out.println("Flying..."); }
}

class Penguin extends Bird {
    // ❌ Penguin không bay được, nhưng kế thừa fly()!
}
```

**Vấn đề:** Penguin "là" Bird, nhưng không phải mọi Bird đều bay.

### 1.2. Fragile Base Class

Thay đổi class cha → Ảnh hưởng tất cả class con.

```java
class Parent {
    void doSomething() { /* logic A */ }
}

class Child extends Parent {
    @Override
    void doSomething() {
        super.doSomething();  // Phụ thuộc logic cha
        // logic B
    }
}
// Nếu Parent thay đổi logic A → Child có thể bị bug!
```

### 1.3. Single Inheritance

Java chỉ cho **1 class cha**. Không thể kế thừa nhiều behavior.

```java
class Robot extends Machine { }
// Robot cũng cần behavior của Worker? Không được extends 2 class!
```

---

## 2. Composition giải quyết vấn đề

### Ý tưởng: Thay vì "là", hãy "có"

```java
// Thay vì: Penguin IS-A Bird (kế thừa fly())
// Dùng: Penguin HAS-A SwimmingAbility (composition)

class SwimmingAbility {
    void swim() { System.out.println("Swimming..."); }
}

class FlyingAbility {
    void fly() { System.out.println("Flying..."); }
}

class Penguin {
    private SwimmingAbility swimming = new SwimmingAbility();

    void swim() { swimming.swim(); }
    // Không có fly() vì Penguin không bay!
}

class Eagle {
    private FlyingAbility flying = new FlyingAbility();

    void fly() { flying.fly(); }
}
```

---

## 3. So sánh Inheritance vs Composition

| Tiêu chí           | Inheritance              | Composition                  |
| :----------------- | :----------------------- | :--------------------------- |
| **Quan hệ**        | Is-A (Là)                | Has-A (Có)                   |
| **Coupling**       | Chặt (Tight)             | Lỏng (Loose)                 |
| **Linh hoạt**      | Khó thay đổi             | Dễ thay đổi runtime          |
| **Số lượng**       | 1 class cha              | Nhiều components             |
| **Reuse code**     | Qua extends              | Qua delegation               |
| **Testing**        | Khó mock class cha       | Dễ mock components           |

---

## 4. Khi nào dùng cái nào?

### Dùng Inheritance khi:

- Quan hệ **thực sự** là "Is-A" (Employee → FullTimeEmployee)
- Cần **override** behavior của cha
- Chia sẻ **state và behavior** chung

### Dùng Composition khi:

- Quan hệ là "Has-A" (Car has Engine)
- Cần **tổ hợp nhiều behavior**
- Behavior có thể **thay đổi runtime**
- Muốn code **dễ test, dễ maintain**

---

## 5. Strategy Pattern (Ứng dụng Composition)

Thay đổi behavior **tại runtime** bằng cách swap component.

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " via PayPal");
    }
}

class Order {
    private PaymentStrategy paymentStrategy;  // Composition

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;  // Thay đổi runtime
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

**Sử dụng:**

```java
Order order = new Order();
order.setPaymentStrategy(new CreditCardPayment());
order.checkout(100);  // Credit Card

order.setPaymentStrategy(new PayPalPayment());
order.checkout(200);  // PayPal
```

---

## 6. Dependency Injection (Spring Boot preview)

Composition là nền tảng của **Dependency Injection** trong Spring.

```java
// Không dùng DI: Tight coupling
class OrderService {
    private EmailService emailService = new EmailService();  // ❌ Hard-coded
}

// Dùng DI: Loose coupling (Composition)
class OrderService {
    private final NotificationService notificationService;

    public OrderService(NotificationService service) {  // ✅ Inject từ ngoài
        this.notificationService = service;
    }
}
```

---

## 7. Tổng kết

| Nguyên tắc                         | Giải thích                              |
| :--------------------------------- | :-------------------------------------- |
| **Favor Composition**              | Ưu tiên "có" hơn "là"                   |
| **Program to Interface**           | Depend vào abstraction, không concrete  |
| **Single Responsibility**          | Mỗi component làm 1 việc                |
| **Open/Closed**                    | Mở để extend, đóng để modify            |

> [!IMPORTANT]
> **Quy tắc của Senior Java:**
> - Hỏi "Có thực sự là quan hệ Is-A không?" trước khi dùng `extends`.
> - Nếu chỉ muốn reuse code → Dùng Composition.
> - Nếu behavior thay đổi → Dùng Composition + Interface.
