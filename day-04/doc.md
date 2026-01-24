# Ngày 4: Stack vs Heap & Bộ Nhớ Java

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Ứng dụng "chết" sau mỗi request → Ít quan tâm memory leak.
> - **Java:** Ứng dụng "sống" hàng tháng trời trong JVM → Heap Memory quản lý không khéo sẽ sập app (Out Of Memory).

---

## 1. Tổng quan: Stack vs Heap

Java chia bộ nhớ thành 2 vùng chính:

| Đặc điểm        | STACK (Ngăn xếp)                                                                                           | HEAP (Đống)                                                   |
| :-------------- | :--------------------------------------------------------------------------------------------------------- | :------------------------------------------------------------ |
| **Lưu cái gì?** | Biến cục bộ primitive (`int`, `boolean`...), tham chiếu (`reference Object`), lời gọi hàm (method frames). | **OBJECT** thật sự (`new Student()`, `new String()`).         |
| **Vòng đời**    | **Tạm thời**: Sinh ra khi hàm chạy, mất đi khi hàm kết thúc.                                               | **Lâu dài**: Sống cho đến khi Garbage Collector (GC) dọn dẹp. |
| **Tốc độ**      | Rất nhanh (LIFO - Last In First Out).                                                                      | Chậm hơn, phức tạp hơn.                                       |
| **Kích thước**  | Nhỏ, cố định (chết chóc nếu đệ quy vô hạn → `StackOverflowError`).                                         | Lớn, động (chết chóc nếu Memory Leak → `OutOfMemoryError`).   |

### 🧠 Visual Mental Model

```text
CODE:
void method() {
    int a = 10;                // a nằm trong Stack
    Student s = new Student(); // s (biến) nằm trong Stack, Student() (Object) nằm trong Heap
}

MEMORY:
+-------------------+             +-----------------------+
|  STACK MEMORY     |             |     HEAP MEMORY       |
+-------------------+             +-----------------------+
| [ method frame ]  |             |                       |
|   a = 10          |             |   Object: Student     |
|   s (reference) --|------------>|   { name: "..." }     |
|                   |             |                       |
+-------------------+             +-----------------------+
```

---

## 2. Pass by Value (Truyền Tham Trị)

Đây là khái niệm cực kỳ quan trọng và hay gây nhầm lẫn cho người mới.

> [!IMPORTANT]
> **Java LUÔN LUÔN là Pass by Value (Truyền giá trị copy).**
> Không có Pass by Reference!

### Tại sao thay đổi biến trong hàm không ảnh hưởng bên ngoài?

(Với Primitive types: `int`, `long`, `boolean`...)

```java
void changePrimitive(int num) {
    num = 99; // Chỉ đổi bản COPY trong Stack frame của hàm này
}
// Kết quả: Biến gốc bên ngoài KHÔNG đổi.
```

### Tại sao thay đổi Object trong hàm LẠI ảnh hưởng bên ngoài?

(Với Reference types: `Object`, `Array`...)

```java
void changeObject(Student ref) {
    ref.name = "New Name";
}
// Kết quả: Object gốc bị đổi TÊN.
```

**Tại sao?**

- Java **COPY giá trị của tham chiếu** (địa chỉ bộ nhớ) vào biến `ref`.
- Biến `ref` (trong hàm) và biến gốc (ngoài hàm) là 2 biến khác nhau...
- ... NHƯNG cả 2 cùng chứa **1 địa chỉ**, cùng trỏ về **1 Object trong Heap**.
- Dùng `ref` để sửa Object → Object trong Heap bị sửa → Bên ngoài thấy thay đổi.

> [!WARNING]
> Nếu bạn gán `ref = new Student()` trong hàm, biến gốc bên ngoài **KHÔNG** bị ảnh hưởng (vì bạn chỉ trỏ biến `ref` cục bộ sang chỗ mới).

---

## 3. String & Wrapper Class (Trường hợp đặc biệt)

```java
void changeInteger(Integer num) {
    num = 88;
}
// Kết quả: Biến gốc KHÔNG đổi.
```

**Tại sao?**

1. `Integer`, `String` là **Immutable** (Bất biến).
2. Khi gán `num = 88`, Java **tạo ra Object Integer(88) mới** trong Heap.
3. Biến `num` cục bộ trỏ sang Object mới này.
4. Biến gốc vẫn trỏ về Object cũ.

---

## 4. Garbage Collector (GC) - Người dọn rác

Trong PHP, memory giải phóng sau mỗi request. Trong Java, **GC** chạy ngầm để dọn Heap.

1. **Khi nào dọn?** Khi Object không còn ai tham chiếu tới (Unreachable).
2. **Cơ chế:** Mark and Sweep (Đánh dấu object sống, quét object chết).
3. **Memory Leak:** Vẫn còn tham chiếu (quên remove khỏi List tĩnh, Listener...) nhưng không dùng tới → GC không dám dọn → Đầy Ram → Sập.

---

## 5. Tổng kết Rules

1. **Biến Primitive** (`int`, `boolean`...) sống ở **Stack**.
2. **Biến Reference** (`s`, `p`...) sống ở **Stack**, trỏ tới **Heap**.
3. **Object thực sự** (`new ...`) sống ở **Heap**.
4. Truyền tham số: Luôn là **COPY** giá trị (với Object là copy địa chỉ).
