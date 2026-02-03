/**
 * STATIC & FINAL
 *
 * static: Thuộc về Class, không thuộc về object
 * final: Không thể thay đổi sau khi gán
 */
public class StaticFinalDemo {
    public static void main(String[] args) {
        System.out.println("=== STATIC VARIABLE ===\n");

        // Trước khi tạo bất kỳ Employee nào
        System.out.println("Total employees: " + Employee.totalEmployees);

        // Tạo các Employee
        Employee e1 = new Employee("Alice");
        Employee e2 = new Employee("Bob");
        Employee e3 = new Employee("Charlie");

        // Biến static được chia sẻ
        System.out.println("Total employees: " + Employee.totalEmployees);
        System.out.println("e1 sees: " + e1.totalEmployees);  // Cùng giá trị
        System.out.println("e2 sees: " + e2.totalEmployees);  // Cùng giá trị

        System.out.println("\n=== STATIC METHOD ===\n");

        // Gọi trực tiếp qua Class, không cần object
        int sum = MathUtils.add(10, 20);
        int max = MathUtils.max(10, 20);
        System.out.println("MathUtils.add(10, 20) = " + sum);
        System.out.println("MathUtils.max(10, 20) = " + max);

        System.out.println("\n=== STATIC FINAL (CONSTANT) ===\n");

        System.out.println("App Name: " + AppConfig.APP_NAME);
        System.out.println("Max Users: " + AppConfig.MAX_USERS);
        System.out.println("Tax Rate: " + AppConfig.TAX_RATE);

        // ❌ Không thể gán lại hằng số
        // AppConfig.APP_NAME = "New Name";  // Compile error!

        System.out.println("\n=== FINAL VARIABLE ===\n");

        final int maxAge = 100;
        // maxAge = 150;  // ❌ Compile error!
        System.out.println("Max age (final): " + maxAge);

        // Final với object: Reference không đổi, nội dung có thể đổi
        final java.util.List<String> names = new java.util.ArrayList<>();
        names.add("Alice");   // ✅ OK
        names.add("Bob");     // ✅ OK
        // names = new java.util.ArrayList<>();  // ❌ Compile error!
        System.out.println("Names: " + names);

        System.out.println("\n=== STATIC BLOCK ===\n");

        // Static block chạy khi Class được load
        System.out.println("Database URL: " + DatabaseConfig.connectionUrl);
    }
}

// ==================== STATIC VARIABLE ====================

class Employee {
    // Static: Biến chung của Class, chia sẻ giữa tất cả objects
    static int totalEmployees = 0;

    // Instance: Biến riêng mỗi object
    private String name;

    public Employee(String name) {
        this.name = name;
        totalEmployees++;  // Tăng biến chung
        System.out.println("Created: " + name + " (Total: " + totalEmployees + ")");
    }

    public String getName() {
        return name;
    }
}

// ==================== STATIC METHOD ====================

class MathUtils {
    // Private constructor: Không cho tạo object
    private MathUtils() {}

    public static int add(int a, int b) {
        return a + b;
    }

    public static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    public static int min(int a, int b) {
        return (a < b) ? a : b;
    }
}

// ==================== STATIC FINAL (CONSTANTS) ====================

class AppConfig {
    // Hằng số: static final + UPPER_SNAKE_CASE
    public static final String APP_NAME = "JavaTutorial";
    public static final int MAX_USERS = 1000;
    public static final double TAX_RATE = 0.1;

    // Private constructor: Không cho tạo object
    private AppConfig() {}
}

// ==================== STATIC BLOCK ====================

class DatabaseConfig {
    static String connectionUrl;
    static int maxConnections;

    // Static block: Chạy 1 lần khi Class được load
    static {
        System.out.println(">>> Static block executing...");
        connectionUrl = "jdbc:mysql://localhost:3306/mydb";
        maxConnections = 100;
        System.out.println(">>> Database configured!");
    }
}

// ==================== FINAL CLASS ====================

final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

// ❌ Không thể extend final class
// class MyPoint extends ImmutablePoint {}  // Compile error!
