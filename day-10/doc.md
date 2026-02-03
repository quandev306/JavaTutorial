# Ngày 10: Immutability (Tính bất biến)

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Mutable là mặc định. Object thay đổi thoải mái.
> - **Java:** **Immutable = An toàn**. Object không thể thay đổi sau khi tạo = không sợ bug, không sợ race condition.

---

## 1. Immutability là gì?

**Immutable Object** = Object **không thể thay đổi** sau khi tạo.

### Ví dụ quen thuộc: `String`

```java
String name = "Alice";
name.toUpperCase();        // Không thay đổi name
System.out.println(name);  // Vẫn là "Alice"

name = name.toUpperCase(); // Tạo String MỚI, gán lại
System.out.println(name);  // "ALICE"
```

**`String` không bao giờ thay đổi.** Mọi thao tác đều tạo object mới.

---

## 2. Tại sao Immutability quan trọng?

### 2.1. Thread-Safe mặc định

```java
// Mutable: 2 thread sửa cùng lúc = Bug
class MutableUser {
    private String name;
    public void setName(String n) { this.name = n; }  // Race condition!
}

// Immutable: Không sửa được = Không race condition
class ImmutableUser {
    private final String name;
    public ImmutableUser(String n) { this.name = n; }
    public String getName() { return name; }
}
```

### 2.2. Dễ debug

- Mutable: "Ai sửa biến này? Lúc nào sửa?" -> Debug mệt.
- Immutable: Giá trị không đổi -> Dễ trace.

### 2.3. Hashable (Dùng làm key trong Map)

```java
// Mutable object làm key = BUG
Map<MutableKey, String> map = new HashMap<>();
MutableKey key = new MutableKey("A");
map.put(key, "value");
key.setId("B");  // Thay đổi key!
map.get(key);    // null! Vì hash code đã thay đổi

// Immutable object làm key = AN TOÀN
Map<String, String> safeMap = new HashMap<>();
safeMap.put("key", "value");  // String là immutable
```

---

## 3. Cách tạo Immutable Class

### Quy tắc:

1. **Class là `final`** (không cho extend)
2. **Tất cả fields là `private final`**
3. **Không có setter**
4. **Khởi tạo qua constructor**
5. **Defensive copy** cho mutable objects

### Ví dụ:

```java
public final class Money {
    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() { return amount; }
    public String getCurrency() { return currency; }

    // Thay vì sửa, tạo object MỚI
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        return new Money(this.amount + other.amount, this.currency);
    }
}
```

---

## 4. Defensive Copy

Khi class chứa **mutable object** (như `Date`, `List`), phải copy để bảo vệ.

### ❌ Sai: Không copy

```java
public final class Event {
    private final Date date;

    public Event(Date date) {
        this.date = date;  // ❌ Giữ reference trực tiếp
    }

    public Date getDate() {
        return date;  // ❌ Trả reference trực tiếp
    }
}

// Bug:
Date d = new Date();
Event event = new Event(d);
d.setTime(0);  // Sửa date bên ngoài -> Event bị ảnh hưởng!
```

### ✅ Đúng: Defensive copy

```java
public final class Event {
    private final Date date;

    public Event(Date date) {
        this.date = new Date(date.getTime());  // ✅ Copy khi nhận
    }

    public Date getDate() {
        return new Date(date.getTime());  // ✅ Copy khi trả
    }
}
```

---

## 5. Java Records (Java 14+)

Java Records tự động tạo immutable class.

```java
// Trước Java 14: Viết nhiều code
public final class Point {
    private final int x;
    private final int y;
    public Point(int x, int y) { this.x = x; this.y = y; }
    public int x() { return x; }
    public int y() { return y; }
    // + equals, hashCode, toString...
}

// Java 14+: 1 dòng!
public record Point(int x, int y) {}
```

**Record tự động có:**
- `final` class
- `private final` fields
- Constructor, getters
- `equals()`, `hashCode()`, `toString()`

---

## 6. Khi nào dùng Mutable vs Immutable?

| Dùng Immutable                      | Dùng Mutable                        |
| :---------------------------------- | :---------------------------------- |
| Value objects (Money, Point, Date)  | Entity objects (User, Order)        |
| Shared state giữa threads           | Local variable trong 1 method       |
| Map keys, Set elements              | Builder pattern                     |
| DTO (Data Transfer Object)          | Performance-critical operations     |

---

## 7. Tổng kết

| Khái niệm           | Giải thích                                  |
| :------------------ | :------------------------------------------ |
| **Immutable**       | Object không thay đổi sau khi tạo           |
| **Thread-safe**     | Nhiều thread dùng chung không lo race       |
| **Defensive copy**  | Copy mutable objects khi nhận/trả           |
| **Record**          | Syntax ngắn gọn cho immutable class         |

> [!IMPORTANT]
> **Best Practice Java:**
> - Mặc định dùng `final` cho mọi field.
> - Chỉ thêm setter khi **thực sự cần thiết**.
> - Value Objects luôn immutable.
