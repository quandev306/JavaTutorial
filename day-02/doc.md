# Ngày 2: String Pool & So sánh chuỗi

> [!TIP]
> **Mental Model:**
>
> - **PHP:** `==` so sánh giá trị (kèm ép kiểu), `===` so sánh giá trị và kiểu. Chuỗi là value type.
> - **Java:** `String` là **OBJECT**.
>   - `==` so sánh **ĐỊA CHỈ BỘ NHỚ** (Reference).
>   - `.equals()` so sánh **NỘI DUNG**.

---

## 1. String Pool là gì?

String Pool (Bể chứa chuỗi) là vùng nhớ đặc biệt trong Heap để tiết kiệm RAM.

```java
String s1 = "Hello"; // Tạo "Hello" trong Pool
String s2 = "Hello"; // Tái sử dụng "Hello" cũ -> Cùng địa chỉ
```

Nếu dùng `new`:

```java
String s3 = new String("Hello"); // Tạo Object MỚI trong Heap (ngoài Pool) -> Khác địa chỉ
```

---

## 2. String Immutability (Bất biến)

Trong Java, **không thể thay đổi nội dung String** sau khi tạo.

```java
String s = "Java";
s = s.toUpperCase(); // Tạo ra chuỗi "JAVA" MỚI, s trỏ sang cái mới.
// Chuỗi cũ "Java" vẫn nằm đó (hoặc bị GC dọn).
```

**Tại sao?**

- **Security:** Class loader, Database connection string... không bị thay đổi lén lút.
- **Thread-safe:** Nhiều luồng đọc cùng lúc không sao.
- **Performance:** Hashcode được cache.

---

## 3. Quy tắc vàng khi so sánh

| Mục đích         | Code            | Lời khuyên                       |
| :--------------- | :-------------- | :------------------------------- |
| So sánh nội dung | `s1.equals(s2)` | ✅ **LUÔN DÙNG**                 |
| So sánh địa chỉ  | `s1 == s2`      | ❌ Hầu như không dùng cho String |

**Best Practice tránh Null:**

```java
String input = null;
// ❌ Dễ dính NPE
if (input.equals("admin")) { ... }

// ✅ Yoda Condition (An toàn tuyệt đối)
if ("admin".equals(input)) { ... }
```

---

## 4. Tổng kết

1. **Quên `==` đi** khi làm việc với String (trừ khi check null `s == null`).
2. **Luôn dùng `.equals()`**.
3. **String là bất biến**, muốn sửa đổi nhiều (nối chuỗi trong loop) thì phải dùng `StringBuilder`.
