/**
 * Ngày 6: Interface vs Abstract Class
 *
 * Mục tiêu:
 * - Hiểu Interface (Contract) vs Abstract Class (Base Template)
 * - Demo Polymorphism (Đa hình)
 * - Dependency Injection cơ bản
 */
public class InterfaceVsAbstract {
    public static void main(String[] args) {
        System.out.println("=== 1. ABSTRACT CLASS (Is-A Relationship) ===");

        Animal dog = new Dog(); // Polymorphism: Biến Animal giữ object Dog
        Animal cat = new Cat();

        dog.makeSound(); // Woof woof!
        cat.makeSound(); // Meow meow!
        dog.sleep();     // Common logic từ Abstract Class

        System.out.println("\n=== 2. INTERFACE (Has-A / Behavior) ===");

        // Dependency Injection: Order không quan tâm là thẻ hay tiền mặt
        // Nó chỉ quan tâm là "thứ này có thể trả tiền (PaymentMethod)"

        PaymentMethod card = new CreditCardPayment();
        PaymentMethod cash = new CashPayment();

        Order order1 = new Order(card);
        order1.process(100.0);

        Order order2 = new Order(cash);
        order2.process(50.0);

        System.out.println("\n=== 3. KHI NÀO DÙNG CÁI NÀO? ===");
        System.out.println("ABSTRACT CLASS: Khi các class có quan hệ cha-con chặt chẽ (Dog LÀ Animal).");
        System.out.println("INTERFACE: Khi các class KHÔNG liên quan nhưng có chung hành động (CreditCard trả tiền, Ví Momo trả tiền).");
    }
}

// ======================= ABSTRACT CLASS DEMO =======================

// Abstract Class: Dùng làm "khuôn mẫu" cho các class con
abstract class Animal {
    // 1. Có thể có method abstract (ko có body) bắt buộc con phải implement
    abstract void makeSound();

    // 2. Có thể có method thường (có body) để tái sử dụng logic
    void sleep() {
        System.out.println("Zzz...");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog: Woof woof!");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat: Meow meow!");
    }
}

// ======================= INTERFACE DEMO =======================

// Interface: Bản hợp đồng (Contract) - "Tôi cam kết sẽ làm được việc này"
// Mọi field mặc định là public static final
// Mọi method mặc định là public abstract
interface PaymentMethod {
    void pay(double amount);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment: " + amount + "$ via Visa/MasterCard 💳");
    }
}

class CashPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Payment: " + amount + "$ via Cash 💵");
    }
}

class Order {
    private PaymentMethod paymentMethod;

    // Constructor Injection
    // Order không phụ thuộc vào CreditCardPayment hay CashPayment cụ thể
    // Nó phụ thuộc vào Interface PaymentMethod (Loose Coupling)
    public Order(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void process(double amount) {
        System.out.print("Processing order... ");
        paymentMethod.pay(amount);
    }
}
