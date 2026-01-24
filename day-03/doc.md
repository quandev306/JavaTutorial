# Ngày 3: StringBuilder vs StringBuffer

> [!TIP]
> **Mental Model:**
>
> - **PHP:** `"Hello" . " " . "World"` là bình thường, engine tự tối ưu.
> - **Java:** `s1 + s2 + s3` trong vòng lặp là **THẢM HỌA HIỆU NĂNG**. Mỗi dấu `+` có thể tạo ra một Object rác.

---

## 1. Tại sao cần StringBuilder?

Vì `String` là bất biến (Immutable).

```java
String s = "";
for (int i = 0; i < 1000; i++) {
    s += "a";
    // MỖI LẦN LẶP:
    // 1. Tạo StringBuilder ảo
    // 2. Append
    // 3. toString() -> Tạo String mới
    // => Tạo 1000 object rác trong Heap!
}
```

**Giải pháp:** Dùng `StringBuilder` (Mutable - Thay đổi được).

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append("a"); // Chỉ sửa nội dung bên trong 1 object duy nhất.
}
// => Chỉ 1 object, cực nhanh.
```

---

## 2. So sánh bộ ba

| Class             | Mutable? | Thread-Safe?       | Tốc độ               | Khi nào dùng?                                         |
| :---------------- | :------- | :----------------- | :------------------- | :---------------------------------------------------- |
| **String**        | ❌ No    | ✅ Yes (Immutable) | Chậm (nếu nối nhiều) | Lưu dữ liệu cố định, tên, email...                    |
| **StringBuilder** | ✅ Yes   | ❌ No              | **Nhanh nhất**       | Nối chuỗi, thao tác chuỗi trong hàm. (99% trường hợp) |
| **StringBuffer**  | ✅ Yes   | ✅ Yes             | Chậm hơn chút        | Chỉ dùng khi chia sẻ biến giữa nhiều Thread (Legacy). |

---

## 3. Tổng kết

1. **Nối chuỗi đơn giản:** Dùng `+` (Code dễ đọc, Compiler tự tối ưu).

   ```java
   String s = "Hello " + name; // OK
   ```

2. **Nối chuỗi trong vòng lặp (Loop):** **BẮT BUỘC** dùng `StringBuilder`.

   ```java
   // Building SQL, JSON, CSV...
   StringBuilder sql = new StringBuilder("SELECT * FROM table WHERE ");
   ```

3. **Đa luồng (Multi-thread):** Mới cân nhắc `StringBuffer` (nhưng giờ thường dùng giải pháp lock khác).
