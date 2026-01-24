# Ngày 1: Primitive Types vs Wrapper Classes

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Không phân biệt `int` hay `Integer`, tất cả là số.
> - **Java:** Phân biệt rõ ràng giữa **Giá trị thô** (Primitive) và **Đối tượng** (Wrapper).
>   - _Primitive:_ Nhanh, nhẹ, sống ở Stack.
>   - _Wrapper:_ Nặng hơn, sống ở Heap, dùng cho OOP.

---

## 1. Phân loại kiểu dữ liệu

| Đặc điểm         | Primitive (`int`, `double`, `boolean`...)  | Wrapper (`Integer`, `Double`, `Boolean`...)            |
| :--------------- | :----------------------------------------- | :----------------------------------------------------- |
| **Bản chất**     | Giá trị đơn thuần.                         | Là một Object (Đối tượng).                             |
| **Giá trị Null** | ❌ KHÔNG thể null (`int a = null` -> Lỗi). | ✅ Có thể null (`Integer a = null`).                   |
| **Lưu trữ**      | **STACK** (Hiệu năng cao).                 | **HEAP** (Tốn bộ nhớ hơn).                             |
| **Sử dụng**      | Tính toán, biến cục bộ, loop.              | Dùng trong Collections (`List<Integer>`), Entity, DTO. |

---

## 2. Autoboxing & Unboxing

Java tự động chuyển đổi qua lại, nhưng cần cẩn trọng.

- **Autoboxing:** Primitive → Wrapper

  ```java
  Integer num = 10; // Tự động: Integer.valueOf(10)
  ```

- **Unboxing:** Wrapper → Primitive
  ```java
  int n = num; // Tự động: num.intValue()
  ```

---

## 3. Cạm bẫy: NullPointerException (NPE)

Đây là lỗi phổ biến nhất khi chuyển từ PHP sang Java.

```java
Integer soLuong = null; // Giả sử lấy từ DB là null
int tong = soLuong + 10; // ❌ BÙM! NullPointerException
```

**Tại sao?**
Vì Java cố gắng "Unbox" (lấy giá trị ra từ) `null` để cộng với 10.
-> **Luôn kiểm tra null** trước khi unbox hoặc dùng method.

---

## 4. Tổng kết

1. **Ưu tiên Primitive (`int`, `long`)** cho tính toán, loop, biến cục bộ để nhanh và nhẹ.
2. **Dùng Wrapper (`Integer`, `Long`)** khi làm việc với Database (có thể null), JSON, hoặc Collections (`ArrayList`).
3. **Cẩn thận với NPE** khi unbox.
