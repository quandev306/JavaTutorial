# Ngày 6: Interface vs Abstract Class & Loose Coupling

> [!TIP]
> **Mental Model:**
>
> - **Abstract Class:** Hỏi **"Nó LÀ cái gì?"** (Identity). Dùng cho quan hệ cha-con ruột thịt (Employee -> FullTimeEmployee), chia sẻ code chung.
> - **Interface:** Hỏi **"Nó LÀM ĐƯỢC gì?"** (Capability/Contract). Dùng cho các class không liên quan nhưng có chung hành vi (Employee nộp thuế, Company nộp thuế).

---

## 1. Abstract Class (Identity - Danh tính)

Dùng khi bạn muốn tạo một "khuôn mẫu" cho các class con có quan hệ chặt chẽ.

```java
// Logic chung: Mọi nhân viên đều có tên, lương cơ bản (Identity)
abstract class Employee {
    String name;
    double salary;

    // Con phải tự định nghĩa
    abstract double getMonthlySalary();
}
```

---

## 2. Interface (Capability - Khả năng)

Dùng để định nghĩa "Hợp đồng" (Contract). Giúp kết nối những thứ không liên quan.

```java
// Hợp đồng: "Bất cứ ai implement tôi đều phải biết tính thuế"
interface TaxPayer {
    double calculateTax();
}

// FullTimeEmployee VỪA là Employee (Identity), VỪA là TaxPayer (Capability)
class FullTimeEmployee extends Employee implements TaxPayer { ... }

// Company KHÔNG phải Employee, nhưng cũng là TaxPayer (Nộp thuế)
class Company implements TaxPayer { ... }
```

---

## 3. Loose Coupling (Liên kết lỏng)

Đây là lý do tại sao Interface cực kỳ quan trọng trong Java (và Spring Boot sau này).

### ❌ Code Chặt (Tight Coupling): phụ thuộc vào Class cụ thể

```java
// Service này chỉ biết tính thuế cho FullTimeEmployee
void calculateValidTax(FullTimeEmployee emp) {
    emp.calculateTax();
}
// Vấn đề: Nếu sếp bảo "Tính thuế cho Company nữa", hàm này vô dụng.
```

### ✅ Code Lỏng (Loose Coupling): phụ thuộc vào Interface

```java
// Service này chấp nhận BẤT CỨ AI có khả năng nộp thuế
void calculateValidTax(TaxPayer payer) {
    payer.calculateTax();
}
// Lợi ích: Truyền Employee cũng được, Company cũng được, Robot cũng được.
// -> Không cần sửa code Service khi thêm loại đối tượng mới.
```

---

## 4. Giải đáp thắc mắc thường gặp

### @Override có bắt buộc không?

- **Không bắt buộc** về mặt cú pháp (code vẫn chạy nếu bỏ).
- **Nhưng NÊN DÙNG**.
- **Tại sao?** Nó là tấm khiên bảo vệ. Nếu bạn gõ sai tên hàm cha (`calculatSalary` thiếu `e`), `@Override` sẽ báo lỗi compile ngay lập tức.

### Interface có field (biến) không?

- Không có biến instance (`private int a`).
- Chỉ có hằng số (`public static final`). -> Interface không dùng để lưu trữ trạng thái.

---

## 5. Tổng kết

| Tiêu chí          | Abstract Class                   | Interface                          |
| :---------------- | :------------------------------- | :--------------------------------- |
| **Tư duy**        | "Is-A" (Là cái gì)               | "Can-Do" (Làm được gì)             |
| **Quan hệ**       | Cha-Con chặt chẽ                 | Không cần quan hệ họ hàng          |
| **Đa kế thừa**    | ❌ Không (1 cha)                 | ✅ Có (Nhiều interface)            |
| **Dùng khi nào?** | Chia sẻ code chung (biến, logic) | Định nghĩa hành vi, Loose Coupling |

> [!IMPORTANT]
> Trong thiết kế ngày nay, **ưu tiên dùng Interface** để giao tiếp giữa các module (Service, Controller, Repository) để đạt được Loose Coupling.
