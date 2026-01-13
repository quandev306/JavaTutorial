/**
 * Ngày 2: String Pool & So sánh chuỗi
 *
 * Mục tiêu:
 * - Hiểu về String Pool
 * - Tại sao dùng .equals() thay vì ==
 * - String Immutability
 */
public class StringPoolDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. STRING POOL LÀ GÌ? ===\n");

        /*
         * String Pool: Vùng nhớ đặc biệt trong Heap
         * - Java tái sử dụng String literals giống nhau
         * - Tiết kiệm bộ nhớ
         */
        String s1 = "Hello";  // Tạo "Hello" trong Pool
        String s2 = "Hello";  // Tái sử dụng "Hello" từ Pool (cùng địa chỉ)

        System.out.println("s1 = \"Hello\" (literal)");
        System.out.println("s2 = \"Hello\" (literal)");
        System.out.println("s1 == s2 → " + (s1 == s2)); // true (cùng địa chỉ trong Pool)

        System.out.println("\n=== 2. TẠO STRING BẰNG new ===\n");

        /*
         * new String(): Luôn tạo object MỚI trong Heap (ngoài Pool)
         */
        String s3 = new String("Hello"); // Object mới ngoài Pool
        String s4 = new String("Hello"); // Lại thêm object mới nữa

        System.out.println("s3 = new String(\"Hello\")");
        System.out.println("s4 = new String(\"Hello\")");
        System.out.println("s3 == s4 → " + (s3 == s4)); // false (khác địa chỉ)
        System.out.println("s1 == s3 → " + (s1 == s3)); // false (Pool vs Heap)

        System.out.println("\n=== 3. SO SÁNH: == vs .equals() ===\n");

        /*
         * ==        : So sánh ĐỊA CHỈ BỘ NHỚ (reference)
         * .equals() : So sánh NỘI DUNG (value) ✅ LUÔN DÙNG!
         */
        System.out.println("== so sánh địa chỉ (reference)");
        System.out.println(".equals() so sánh nội dung (value)\n");

        System.out.println("s1 == s3 → " + (s1 == s3));           // false
        System.out.println("s1.equals(s3) → " + s1.equals(s3));   // true ✅

        System.out.println("\n=== 4. STRING IMMUTABILITY (Bất biến) ===\n");

        /*
         * String là IMMUTABLE (không thể thay đổi)
         * - Mọi "thay đổi" đều tạo String MỚI
         * - Tốt cho thread-safety và caching
         */
        String original = "Java";
        String modified = original.toUpperCase(); // Tạo String mới "JAVA"

        System.out.println("original = \"" + original + "\" (không đổi!)");
        System.out.println("modified = \"" + modified + "\" (String mới)");
        System.out.println("original == modified → " + (original == modified)); // false

        System.out.println("\n=== 5. BEST PRACTICE: TRÁNH NullPointerException ===\n");

        /*
         * Đặt literal TRƯỚC .equals() để tránh NPE
         */
        String name = null;

        // ❌ SAI: name.equals("admin") → NPE nếu name = null
        // ✅ ĐÚNG: "admin".equals(name) → false, không lỗi

        System.out.println("name = null");
        System.out.println("\"admin\".equals(name) → " + "admin".equals(name)); // false, an toàn

        try {
            boolean result = name.equals("admin"); // ❌ NPE!
        } catch (NullPointerException e) {
            System.out.println("name.equals(\"admin\") → ❌ NullPointerException!");
        }

        System.out.println("\n=== 6. TÓM TẮT ===\n");

        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│  QUY TẮC SO SÁNH STRING:                    │");
        System.out.println("│                                             │");
        System.out.println("│  ❌ ĐỪNG dùng:  s1 == s2                    │");
        System.out.println("│  ✅ HÃY dùng:   s1.equals(s2)               │");
        System.out.println("│  ✅ Tốt hơn:    \"literal\".equals(variable)  │");
        System.out.println("└─────────────────────────────────────────────┘");
    }
}
