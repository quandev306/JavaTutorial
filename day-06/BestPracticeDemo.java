import java.util.ArrayList;
import java.util.List;

/**
 * BEST PRACTICE: KHI NÀO DÙNG CÁI NÀO?
 *
 * 1. ABSTRACT CLASS (Identity - "Là cái gì?"):
 *    - Dùng khi các class có quan hệ cha-con, chia sẻ code chung.
 *    - VD: FullTimeEmployee và PartTimeEmployee đều LÀ Employee.
 *    - Chúng có chung: id, name, method checkIn().
 *
 * 2. INTERFACE (Capability - "Làm được gì?"):
 *    - Dùng cho các class KHÔNG LIÊN QUAN gì nhau nhưng có chung hành vi.
 *    - VD: Employee nộp thuế, Company nộp thuế.
 *    - Employee và Company không liên quan (không extends nhau), nhưng đều là "TaxPayer".
 */
public class BestPracticeDemo {
    public static void main(String[] args) {
        // --- 1. LOOSE COUPLING (Liên kết lỏng) ---
        // TaxService không quan tâm đối tượng là Employee hay Company.
        // Nó chỉ quan tâm đối tượng đó có là TaxPayer không. (Hợp đồng)
        TaxService taxService = new TaxService();

        FullTimeEmployee dev = new FullTimeEmployee("Dev A", 1000);
        Company startup = new Company("Tech Startup", 50000);

        // List chứa Interface, không chứa Class cụ thể
        // Đây là Polymorphism (Đa hình) qua Interface
        List<TaxPayer> taxPayers = new ArrayList<>();
        taxPayers.add(dev);
        taxPayers.add(startup);

        System.out.println("=== REPORT THUẾ ===");
        taxService.calculateTotalTax(taxPayers);
    }
}

// -----------------------------------------------------------
// 1. CAPABILITY (Interface): Khả năng "Nộp thuế"
// Bất cứ ai implement cái này đều phải nộp thuế.
// -----------------------------------------------------------
interface TaxPayer {
    double calculateTax();
}

// -----------------------------------------------------------
// 2. IDENTITY (Abstract Class): "Nhân viên"
// Gom nhóm các logic chung của nhân viên (id, name...).
// Company KHÔNG thể kế thừa Employee (vô lý), nên ta dùng Interface ở trên.
// -----------------------------------------------------------
abstract class Employee implements TaxPayer {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method: Con phải tự định nghĩa cách tính lương
    abstract double getMonthlySalary();
}

// -----------------------------------------------------------
// 3. CONCRETE CLASSES (Lớp cụ thể)
// -----------------------------------------------------------

// Nhân viên Fulltime (Là Employee + Là TaxPayer)
class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override // ✅ Bắt buộc? Không (về syntax). Được khuyên dùng? CÓ (để tránh lỗi typo).
    double getMonthlySalary() {
        return this.salary;
    }

    @Override
    public double calculateTax() {
        return this.salary * 0.1; // Thuế 10%
    }
}

// PartTimeEmployee
class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name, double salary) {
        super(name, salary);
    }

    @Override
    double getMonthlySalary() {
        return this.salary * 0.5;
    }

    @Override
    public double calculateTax() {
        return this.salary * 0.05; // Thuế 5%
    }
}

// Công ty (KHÔNG phải Employee, nhưng cũng là TaxPayer)
class Company implements TaxPayer {
    private String companyName;
    private double revenue;

    public Company(String companyName, double revenue) {
        this.companyName = companyName;
        this.revenue = revenue;
    }

    @Override
    public double calculateTax() {
        return this.revenue * 0.2; // Thuế 20%
    }
}

// -----------------------------------------------------------
// 4. SERVICE (Nơi thể hiện Loose Coupling)
// -----------------------------------------------------------
class TaxService {
    // Hàm này chấp nhận List<TaxPayer>.
    // Nó KHÔNG phụ thuộc vào FullTimeEmployee hay Company.
    // Nếu sau này có thêm "Freelancer" hay "Robot" nộp thuế, hàm này KHÔNG CẦN SỬA.
    // -> ĐÂY LÀ LOOSE COUPLING.
    public void calculateTotalTax(List<TaxPayer> payers) {
        for (TaxPayer p : payers) {
            System.out.println("Thuế: " + p.calculateTax());
        }
    }
}
