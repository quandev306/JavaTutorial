

/**
 * 4 MỨC ĐỘ TRUY CẬP (ACCESS MODIFIERS)
 *
 * 1. private:   Chỉ trong class này. (Riêng tư nhất)
 * 2. default:   Chỉ trong package này. (Mặc định nếu không viết gì)
 * 3. protected: Trong package này + Class con kế thừa ở package khác.
 * 4. public:    Tất cả mọi nơi. (Công khai)
 */
public class AccessModifierDemo {
    public static void main(String[] args) {
        DatabaseConfig db = new DatabaseConfig();

        // 1. PUBLIC: Truy cập thoải mái
        System.out.println("Public: " + db.connectionUrl);

        // 2. DEFAULT: Truy cập được (vì AccessModifierDemo cùng package với DatabaseConfig)
        System.out.println("Default: " + db.databaseName);

        // 3. PROTECTED: Truy cập được (vì cùng package)
        System.out.println("Protected: " + db.maxConnections);

        // 4. PRIVATE: ❌ Lỗi ngay nếu bỏ comment
        // System.out.println(db.password);

        System.out.println("Private: Không thể truy cập trực tiếp db.password");
        db.showPassword(); // Phải dùng phương thức public để xem
    }
}

class DatabaseConfig {
    // 1. PUBLIC: Ai cũng thấy
    public String connectionUrl = "jdbc:mysql://localhost:3306";

    // 2. DEFAULT (Không viết gì): Chỉ class cùng package thấy
    String databaseName = "my_app_db";

    // 3. PROTECTED: Cùng package + Con cái thấy
    protected int maxConnections = 100;

    // 4. PRIVATE: "Sống để bụng, chết mang theo" - Chỉ class này thấy
    private String password = "secret_password";

    // Method public để "khoe" biến private (Controlled Access)
    public void showPassword() {
        System.out.println("Password (Internal): " + this.password);
    }
}
