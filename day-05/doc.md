# Ngày 5: Java Generics & Tư duy Type-Safe

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Mảng (`array`) là "nồi lẩu thập cẩm", chứa gì cũng được (int, string, object...). Tiện nhưng dễ bug.
> - **Java:** Cần "hộp chuyên dụng" (`Box<Integer>`, `List<String>`). Bỏ sai loại vào là lỗi ngay lúc biên dịch (Compile Error).

---

## 1. Tại sao cần Generics?

Trước Java 5, Collections (như `ArrayList`) chứa `Object` (tương tự `mixed` trong PHP).

```java
// Cách cũ (Nguy hiểm)
List list = new ArrayList();
list.add("Hello");
list.add(123); // Vẫn Ok, nhưng...

// Khi lấy ra dùng:
String s = (String) list.get(1); // 💥 ClassCastException (Runtime Error)
```

**Generics ra đời để chặn lỗi này ngay khi bạn viết code:**

```java
// Cách mới (Type-Safe)
List<String> list = new ArrayList<>();
list.add("Hello");
// list.add(123); // ❌ Lỗi Compile ngay lập tức!
```

---

## 2. Các thành phần Generics cơ bản

### 2.1. Generic Class

Tạo một cái hộp (Box) có thể chứa kiểu `T` bất kỳ.

```java
public class Box<T> {
    private T value;

    public void set(T value) { this.value = value; }
    public T get() { return value; }
}
```

- `Box<Integer>` → Cái hộp chỉ chứa số nguyên.
- `Box<String>` → Cái hộp chỉ chứa chuỗi.

### 2.2. Generic Method

Viết hàm xử lý được nhiều kiểu dữ liệu.

```java
public <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}
```

---

## 3. Wildcards (?) - "Dấu hỏi chấm"

Khi bạn muốn code linh hoạt hơn nhưng vẫn an toàn.

| Ký hiệu              | Ý nghĩa                                                      | Ví dụ                           |
| :------------------- | :----------------------------------------------------------- | :------------------------------ |
| `<?>`                | Chấp nhận **MỌI** kiểu                                       | `List<?>`                       |
| `<? extends Number>` | Chấp nhận `Number` hoặc con cháu nó (`Integer`, `Double`...) | **Read-only** (An toàn để đọc)  |
| `<? super Integer>`  | Chấp nhận `Integer` hoặc cha nó (`Number`, `Object`)         | **Write-only** (An toàn để ghi) |

> [!IMPORTANT]
> **Quy tắc PECS:** Producer Extends, Consumer Super.
>
> - Muốn **ĐỌC** dữ liệu ra: Dùng `extends`.
> - Muốn **GHI** dữ liệu vào: Dùng `super`.

---

## 4. Type Erasure (Xóa kiểu)

Đây là điều thú vị của Java (khác với C#).

Tại **Compile time** (lúc viết code):

- Java kiểm tra chặt chẽ `List<String>`.

Tại **Runtime** (lúc chạy):

- JVM xóa hết thông tin Generic. Nó chỉ biết đó là `List` (raw type).
- Cơ chế này để tương thích ngược với Java đời cũ.

**Hệ quả:**

- ❌ Không thể `new T()` (vì Runtime không biết T là gì).
- ❌ Không thể `instanceof T`.

---

## 5. Tổng kết

1. **Generics = Type Safety:** Biến lỗi Runtime thành lỗi Compile.
2. **Diamond Syntax `<>`:** `new ArrayList<>()` (Java 7+) cho gọn.
3. **Không dùng Raw Type:** Đừng viết `List list`, hãy viết `List<Object>` hoặc `List<?>`.
4. **Mental Model:** Hãy nghĩ về "Cái hộp dán nhãn". Nhãn ghi "Sách" thì không được bỏ "Bút" vào.
