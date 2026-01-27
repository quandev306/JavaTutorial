/**
 * Ngày 7: Encapsulation (Tính đóng gói)
 *
 * Mục tiêu:
 * - Hiểu tại sao KHÔNG NÊN dùng public fields.
 * - Cách dùng Getter/Setter để bảo vệ dữ liệu.
 * - Các mức truy cập: private, default, protected, public.
 */
public class EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. BAD EXAMPLE (Public Fields) ===");
        BadBankAccount badAcc = new BadBankAccount();
        badAcc.balance = -1000000; // ❌ NGUY HIỂM! Ai cũng có thể chỉnh sửa tùy ý
        System.out.println("Bad Account Balance: " + badAcc.balance);

        System.out.println("\n=== 2. GOOD EXAMPLE (Encapsulation) ===");
        BankAccount goodAcc = new BankAccount("Nguyen Van A");

        // goodAcc.balance = 5000; // ❌ Lỗi Compile! Không thể truy cập private field

        goodAcc.deposit(1000); // ✅ Gửi tiền qua method (có kiểm tra)
        goodAcc.withdraw(500); // ✅ Rút tiền qua method (có kiểm tra)

        System.out.println("Current Balance: " + goodAcc.getBalance());

        // Thử phá hoại
        System.out.println("\n--- Thử gửi tiền âm ---");
        goodAcc.deposit(-200); // ❌ Bị chặn bởi logic trong method

        System.out.println("\n--- Thử rút quá số dư ---");
        goodAcc.withdraw(50000); // ❌ Bị chặn

        // In thông tin
        System.out.println("\n" + goodAcc.toString());
    }
}

// -----------------------------------------------------------
// ❌ BAD: Dữ liệu bị phơi bày ra ngoài
// -----------------------------------------------------------
class BadBankAccount {
    public double balance; // Ai cũng sửa được
}

// -----------------------------------------------------------
// ✅ GOOD: Giấu dữ liệu, chỉ chìa ra hành động
// -----------------------------------------------------------
class BankAccount {
    // 1. PRIVATE FIELDS: Chỉ class này mới được đụng vào
    private String owner;
    private double balance;

    public BankAccount(String owner) {
        this.owner = owner;
        this.balance = 0.0;
    }

    // 2. PUBLIC METHODS (Getter/Setter/Logic): Cửa ngõ giao tiếp
    public double getBalance() {
        return this.balance;
    }

    // Không có setBalance() -> Read-only từ bên ngoài
    // Muốn đổi balance phải qua deposit/withdraw

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Lỗi: Số tiền gửi phải > 0");
            return;
        }
        this.balance += amount;
        System.out.println("✅ Đã gửi: " + amount + ". Số dư mới: " + this.balance);
    }

    public void withdraw(double amount) {
        if (amount > this.balance) {
            System.out.println("❌ Lỗi: Không đủ tiền để rút!");
            return;
        }
        this.balance -= amount;
        System.out.println("✅ Đã rút: " + amount + ". Số dư mới: " + this.balance);
    }

    @Override
    public String toString() {
        return "BankAccount { owner='" + owner + "', balance=" + balance + " }";
    }
}
