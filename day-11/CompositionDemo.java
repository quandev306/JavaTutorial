/**
 * COMPOSITION OVER INHERITANCE
 *
 * Ưu tiên "Has-A" (có) hơn "Is-A" (là).
 * Linh hoạt, dễ test, dễ maintain.
 */
public class CompositionDemo {
    public static void main(String[] args) {
        System.out.println("=== VẤN ĐỀ VỚI INHERITANCE ===\n");

        // Penguin kế thừa Bird, nhưng không bay được!
        // Đây là thiết kế sai
        BadPenguin badPenguin = new BadPenguin();
        badPenguin.fly();  // ❌ Penguin không bay được!

        System.out.println("\n=== GIẢI QUYẾT BẰNG COMPOSITION ===\n");

        // Penguin chỉ có khả năng bơi, không có khả năng bay
        Penguin penguin = new Penguin("Pingu");
        penguin.swim();

        // Eagle có khả năng bay
        Eagle eagle = new Eagle("Eddie");
        eagle.fly();

        // Duck có cả hai khả năng
        Duck duck = new Duck("Donald");
        duck.fly();
        duck.swim();

        System.out.println("\n=== STRATEGY PATTERN ===\n");

        Order order = new Order("ORD-001", 150.0);

        // Thanh toán bằng Credit Card
        order.setPaymentStrategy(new CreditCardPayment());
        order.checkout();

        // Đổi sang PayPal (thay đổi runtime!)
        order.setPaymentStrategy(new PayPalPayment());
        order.checkout();

        // Đổi sang Crypto
        order.setPaymentStrategy(new CryptoPayment());
        order.checkout();

        System.out.println("\n=== DEPENDENCY INJECTION PREVIEW ===\n");

        // Inject EmailNotification
        NotificationService emailService = new EmailNotification();
        OrderService orderService1 = new OrderService(emailService);
        orderService1.placeOrder("ORD-002");

        // Inject SMSNotification (dễ dàng swap!)
        NotificationService smsService = new SMSNotification();
        OrderService orderService2 = new OrderService(smsService);
        orderService2.placeOrder("ORD-003");
    }
}

// ==================== VẤN ĐỀ: INHERITANCE SAI =====================

class BadBird {
    void fly() {
        System.out.println("Flying high! 🦅");
    }
}

class BadPenguin extends BadBird {
    // ❌ Kế thừa fly() nhưng Penguin không bay được!
    // Phải override để ném exception hoặc không làm gì
    @Override
    void fly() {
        System.out.println("❌ Penguin can't fly! (Bad design)");
    }
}

// ==================== GIẢI PHÁP: COMPOSITION ====================

// Abilities (Components)
class FlyingAbility {
    void fly() {
        System.out.println("Flying gracefully! 🦅");
    }
}

class SwimmingAbility {
    void swim() {
        System.out.println("Swimming smoothly! 🏊");
    }
}

// Animals sử dụng Composition
class Penguin {
    private final String name;
    private final SwimmingAbility swimmingAbility = new SwimmingAbility();

    public Penguin(String name) {
        this.name = name;
    }

    void swim() {
        System.out.print(name + ": ");
        swimmingAbility.swim();
    }
    // Không có fly() vì Penguin không bay!
}

class Eagle {
    private final String name;
    private final FlyingAbility flyingAbility = new FlyingAbility();

    public Eagle(String name) {
        this.name = name;
    }

    void fly() {
        System.out.print(name + ": ");
        flyingAbility.fly();
    }
}

class Duck {
    private final String name;
    private final FlyingAbility flyingAbility = new FlyingAbility();
    private final SwimmingAbility swimmingAbility = new SwimmingAbility();

    public Duck(String name) {
        this.name = name;
    }

    void fly() {
        System.out.print(name + ": ");
        flyingAbility.fly();
    }

    void swim() {
        System.out.print(name + ": ");
        swimmingAbility.swim();
    }
}

// ==================== STRATEGY PATTERN ====================

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("💳 Paid $" + amount + " via Credit Card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("🅿️ Paid $" + amount + " via PayPal");
    }
}

class CryptoPayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("₿ Paid $" + amount + " via Crypto");
    }
}

class Order {
    private final String orderId;
    private final double amount;
    private PaymentStrategy paymentStrategy;  // Composition!

    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;  // Thay đổi runtime
    }

    public void checkout() {
        System.out.print("Order " + orderId + ": ");
        paymentStrategy.pay(amount);
    }
}

// ==================== DEPENDENCY INJECTION ====================

interface NotificationService {
    void send(String message);
}

class EmailNotification implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("📧 Email: " + message);
    }
}

class SMSNotification implements NotificationService {
    @Override
    public void send(String message) {
        System.out.println("📱 SMS: " + message);
    }
}

class OrderService {
    private final NotificationService notificationService;  // Composition + DI

    // Constructor Injection
    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder(String orderId) {
        // Business logic...
        notificationService.send("Order " + orderId + " placed successfully!");
    }
}
