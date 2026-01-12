# Bài tập Ngày 6-12: OOP Hardcore

## Mục tiêu
Hiểu sâu về OOP: Interface, Abstract Class, Polymorphism, Static và Final.

## Bài tập 1: Quản lý động vật (Polymorphism & Abstract Class)
1.  Tạo Abstract Class `Animal` với phương thức abstract `makeSound()`.
2.  Tạo các class con `Dog`, `Cat` kế thừa `Animal` và implement `makeSound()` (Dog sủa, Cat kêu meow).
3.  Trong hàm `main`, tạo một mảng (hoặc `ArrayList`) chứa hỗn hợp các đối tượng `Dog` và `Cat`.
4.  Duyệt qua danh sách và gọi `makeSound()` cho từng phần tử.
5.  *Câu hỏi:* Tại sao biến kiểu `Animal` lại có thể giữ instance của `Dog` và `Cat`?

## Bài tập 2: Hệ thống thanh toán (Interface)
1.  Tạo Interface `PaymentMethod` với phương thức `void pay(double amount)`.
2.  Implement class `CreditCardPayment` (in ra "Paying [amount] via Credit Card") va `CashPayment` (in ra "Paying [amount] via Cash").
3.  Viết class `Order` có phương thức `processPayment(PaymentMethod method, double amount)`.
4.  Trong `main`, tạo đơn hàng và thử thanh toán bằng cả hai hình thức mà không cần sửa code của class `Order` (Dependency Injection cơ bản).

## Bài tập 3: Static & Final
1.  Tạo class `AppConfig` với biến `public static final String APP_NAME = "JavaTutorial";`.
2.  Viết hàm `main` in ra `AppConfig.APP_NAME`. Thử gán giá trị mới cho nó và xem lỗi.
3.  Tạo class `Counter` với biến `private static int count = 0`.
    *   Trong constructor của `Counter`, tăng `count` lên 1.
    *   Tạo 3 obj `Counter` khác nhau.
    *   In ra giá trị `count` cuối cùng. Giải thích tại sao nó không phải là 1.
