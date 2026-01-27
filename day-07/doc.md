# Ngày 7: Encapsulation (Tính đóng gói) & Access Modifiers

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Thường dùng `public` cho tiện (nhanh -> ẩu).
> - **Java:** **"Đừng tin ai cả"**. Nguyên tắc **Zero Trust**:
>   - Giấu hết dữ liệu (`private`).
>   - Chỉ cho xem/sửa qua các "cổng kiểm soát" (`method`) có logic bảo vệ.
>   - _Ví dụ:_ Không cho sửa trực tiếp số dư tài khoản, phải qua hàm `deposit()` để check tiền gửi > 0.

---

## 1. Tại sao phải Đóng Gói (Encapsulation)?

Mục tiêu: **Bảo vệ toàn vẹn dữ liệu (Data Integrity).**

### Ví dụ thực tế:

- **BAD (`public`):** Hacker gán `account.balance = -9999999;` -> Chấp nhận luôn -> Ngân hàng sập.
- **GOOD (`private` + method):** Hacker gọi `account.withdraw(9999999);` -> Hàm check `if (amount > balance) return;` -> Bị chặn -> An toàn.

> **Quy tắc vàng:**
>
> 1. Field (biến) luôn luôn là **PRIVATE**.
> 2. Method (hàm) cho phép bên ngoài dùng thì **PUBLIC**.

---

## 2. 4 Mức độ truy cập (Access Modifiers)

Từ khó tính nhất (🔒) đến dễ dãi nhất (🌍).

| Level            | Keyword           | Phạm vi truy cập (Ai nhìn thấy?)   | Mental Model (Ví dụ đời sống)                                                                           |
| :--------------- | :---------------- | :--------------------------------- | :------------------------------------------------------------------------------------------------------ |
| **1. Private**   | `private`         | **Chỉ nội bộ Class**.              | **"Nhật ký bí mật"**. Chỉ mình mình đọc được. Vợ con cũng không cho xem.                                |
| **2. Default**   | _(không viết gì)_ | **Nội bộ Package** (Thư mục).      | **"Đồ dùng trong nhà"**. Người trong nhà (package) dùng chung. Hàng xóm (package khác) không được dùng. |
| **3. Protected** | `protected`       | Package + **Class Con** (kế thừa). | **"Gia bảo"**. Người nhà dùng + Con cái ở xa cũng được thừa kế.                                         |
| **4. Public**    | `public`          | **Tất cả mọi nơi**.                | **"Wifi quán cafe"**. Ai cũng vào được.                                                                 |

### Khi nào dùng cái nào?

- **Private:** Dùng cho **99% các thuộc tính (fields)**. (`password`, `balance`, `id`...).
- **Public:** Dùng cho các hàm **API, Service** để bên ngoài gọi (`login()`, `processPayment()`).
- **Default:** Dùng cho các class/hàm tiện ích chỉ chạy ngầm trong module, không muốn lộ ra ngoài module khác.
- **Protected:** Chỉ dùng khi viết **Library/Framework** cho người khác kế thừa.

---

## 3. Getter & Setter: Đừng dùng vô tội vạ!

Lỗi sơ đẳng của người mới: Tạo class xong Generate Getter/Setter cho **TẤT CẢ** các biến.
-> Như vậy thì `private` biến thành `public` gián tiếp rồi!

**Tư duy đúng:**

1.  **Read-only:** Chỉ tạo `Getter`. (Ví dụ: Số CMND, Ngày sinh - không được sửa).
2.  **Write-only:** Chỉ tạo `Setter` (Hiếm gặp).
3.  **Full access:** Tạo cả hai (Ví dụ: Địa chỉ nhà - được xem và sửa).
4.  **No access:** Không tạo gì cả (Ví dụ: `password` - chỉ dùng nội bộ để check login).

---

## 4. Tổng kết

1.  **Encapsulation** không phải để "giấu nghề", mà để **giữ cho dữ liệu luôn đúng**.
2.  Mặc định cứ phang **Private** cho biến. Cần thiết lắm mới mở dần ra.
3.  Kiểm soát dữ liệu đầu vào chặt chẽ ngay tại hàm `Setter` hoặc method nghiệp vụ (`deposit`, `withdraw`).
