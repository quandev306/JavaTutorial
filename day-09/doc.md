# Ngày 9: Static & Final

> [!TIP]
> **Mental Model:**
>
> - **`static`:** Thuộc về **Class**, không thuộc về object. Như **"tài sản chung"** của gia đình.
> - **`final`:** **"Đóng băng"**. Không thể thay đổi sau khi gán giá trị.

---

## 1. Từ khóa `static`

### 1.1. Static Variable (Biến tĩnh)

Biến thuộc về **Class**, chia sẻ giữa tất cả các object.

```java
class Employee {
    static int totalEmployees = 0;  // Tài sản chung
    String name;                     // Tài sản riêng mỗi object

    public Employee(String name) {
        this.name = name;
        totalEmployees++;  // Mỗi nhân viên mới, tăng biến chung
    }
}
```

**Ví dụ:** Đếm số lượng nhân viên. Dù tạo 100 object, chỉ có 1 biến `totalEmployees`.

### 1.2. Static Method (Phương thức tĩnh)

- **Không cần tạo object** để gọi.
- **Không thể truy cập** biến/method không static (vì chưa có object).

```java
class MathUtils {
    public static int add(int a, int b) {
        return a + b;
    }
}

// Gọi trực tiếp qua tên Class
int result = MathUtils.add(5, 3);
```

### 1.3. Static Block

Chạy **1 lần duy nhất** khi Class được load vào memory.

```java
class DatabaseConfig {
    static String connectionUrl;

    static {
        // Chạy khi Class được load
        connectionUrl = loadFromEnvironment();
        System.out.println("Database configured!");
    }
}
```

### 1.4. Khi nào dùng Static?

| Dùng `static`                         | Không dùng `static`                |
| :------------------------------------ | :--------------------------------- |
| Utility methods (`Math.max()`)        | Business logic phụ thuộc state     |
| Constants (`AppConfig.MAX_USERS`)     | Data riêng mỗi object              |
| Factory methods (`LocalDate.now()`)   | Method cần truy cập instance field |
| Counter/Shared state giữa các objects |                                    |

---

## 2. Từ khóa `final`

### 2.1. Final Variable

**Không thể gán lại** sau khi khởi tạo.

```java
final int MAX_USERS = 100;
MAX_USERS = 200;  // ❌ Lỗi compile!
```

**Với object:** Reference không đổi, nhưng **nội dung object có thể đổi**.

```java
final List<String> names = new ArrayList<>();
names.add("Alice");    // ✅ OK: Thay đổi nội dung
names = new ArrayList<>();  // ❌ Lỗi: Không thể gán lại reference
```

### 2.2. Final Method

**Không thể override** ở class con.

```java
class Parent {
    public final void criticalMethod() {
        // Logic quan trọng, không cho con ghi đè
    }
}

class Child extends Parent {
    @Override
    public void criticalMethod() {  // ❌ Lỗi compile!
    }
}
```

### 2.3. Final Class

**Không thể extend** (kế thừa).

```java
final class String {
    // Không ai có thể extends String
}

class MyString extends String {  // ❌ Lỗi compile!
}
```

**Ví dụ thực tế:** `String`, `Integer`, `Math` đều là `final class`.

---

## 3. Kết hợp `static final` = Hằng số

Đây là cách **chuẩn** để khai báo hằng số trong Java.

```java
public class AppConfig {
    public static final String APP_NAME = "JavaTutorial";
    public static final int MAX_CONNECTIONS = 100;
    public static final double TAX_RATE = 0.1;
}

// Sử dụng
System.out.println(AppConfig.APP_NAME);
```

**Convention:** Tên hằng số viết **UPPER_SNAKE_CASE**.

---

## 4. Static vs Non-Static

| Đặc điểm             | Static                   | Non-Static (Instance)       |
| :------------------- | :----------------------- | :-------------------------- |
| Thuộc về             | Class                    | Object                      |
| Memory               | 1 bản trong Class memory | Mỗi object 1 bản            |
| Truy cập             | `ClassName.member`       | `object.member`             |
| Truy cập instance?   | ❌ Không thể             | ✅ Có thể                   |
| Ví dụ                | `Math.PI`, `Math.max()`  | `employee.getName()`        |

---

## 5. Tổng kết

| Keyword        | Áp dụng cho     | Ý nghĩa                              |
| :------------- | :-------------- | :----------------------------------- |
| `static`       | Variable/Method | Thuộc về Class, không cần object     |
| `final`        | Variable        | Không thể gán lại                    |
| `final`        | Method          | Không thể override                   |
| `final`        | Class           | Không thể extend                     |
| `static final` | Variable        | Hằng số (Constant)                   |

> [!IMPORTANT]
> **Best Practice:**
> - Dùng `final` mọi nơi có thể để tránh bug do reassign.
> - Dùng `static` cho utilities, constants, factory methods.
> - **Không dùng** static cho business state (dễ gây bug trong multi-thread).
