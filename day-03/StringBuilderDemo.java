/**
 * Ngày 3: StringBuilder vs StringBuffer
 *
 * Mục tiêu:
 * - Hiểu String Immutability (bất biến)
 * - Khi nào dùng StringBuilder vs StringBuffer
 * - So sánh hiệu năng
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. STRING IMMUTABILITY (Bất biến) ===\n");

        /*
         * String là IMMUTABLE (bất biến)
         * - Mỗi lần "thay đổi" = tạo String MỚI
         * - Nối chuỗi nhiều = tốn bộ nhớ và chậm
         */
        String str = "Hello";
        System.out.println("String gốc: " + str);

        str = str + " World";  // Tạo String MỚI, không sửa String cũ
        System.out.println("Sau nối: " + str);
        System.out.println("→ String cũ bị bỏ đi, tạo rác cho GC!\n");

        System.out.println("=== 2. STRINGBUILDER - MUTABLE (Thay đổi được) ===\n");

        /*
         * StringBuilder là MUTABLE (thay đổi được)
         * - Thay đổi TRỰC TIẾP trên cùng 1 object
         * - Không tạo object mới → Nhanh hơn
         * - KHÔNG thread-safe (không an toàn đa luồng)
         */
        StringBuilder sb = new StringBuilder("Hello");
        System.out.println("StringBuilder gốc: " + sb);

        sb.append(" World");  // Thay đổi TRỰC TIẾP, không tạo object mới
        System.out.println("Sau append: " + sb);

        sb.insert(5, " Java");
        System.out.println("Sau insert: " + sb);

        sb.delete(5, 10);
        System.out.println("Sau delete: " + sb);

        sb.reverse();
        System.out.println("Sau reverse: " + sb);

        System.out.println("\n=== 3. STRINGBUFFER - THREAD-SAFE ===\n");

        /*
         * StringBuffer = StringBuilder + Thread-safe
         * - Giống StringBuilder nhưng các method synchronized
         * - An toàn khi nhiều thread cùng xài 1 biến
         * - Chậm hơn StringBuilder một chút
         */
        StringBuffer sbf = new StringBuffer("Multi");
        sbf.append("-Thread");
        System.out.println("StringBuffer: " + sbf);
        System.out.println("→ Dùng khi có nhiều thread cùng sửa 1 biến chuỗi\n");

        System.out.println("=== 4. SO SÁNH HIỆU NĂNG ===\n");

        int iterations = 10000;

        // Test 1: String concatenation (+)
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "a";  // Mỗi lần tạo String MỚI!
        }
        long stringTime = System.currentTimeMillis() - start;

        // Test 2: StringBuilder
        start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            builder.append("a");  // Không tạo object mới
        }
        long builderTime = System.currentTimeMillis() - start;

        // Test 3: StringBuffer
        start = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            buffer.append("a");  // Synchronized overhead
        }
        long bufferTime = System.currentTimeMillis() - start;

        System.out.println("┌────────────────────────────────────┐");
        System.out.println("│  Nối chuỗi 10,000 lần:             │");
        System.out.println("│                                    │");
        System.out.printf("│  String (+):      %6dms ❌      │\n", stringTime);
        System.out.printf("│  StringBuilder:   %6dms ✅      │\n", builderTime);
        System.out.printf("│  StringBuffer:    %6dms ⚠️       │\n", bufferTime);
        System.out.println("└────────────────────────────────────┘");

        System.out.println("\n=== 5. KHI NÀO DÙNG CÁI NÀO? ===\n");

        System.out.println("┌──────────────────────────────────────────────────┐");
        System.out.println("│  String:                                         │");
        System.out.println("│  - Chuỗi CỐ ĐỊNH, ít thay đổi                    │");
        System.out.println("│  - VD: String name = \"John\";                    │");
        System.out.println("│                                                  │");
        System.out.println("│  StringBuilder: ✅ DÙNG NHIỀU NHẤT               │");
        System.out.println("│  - Nối chuỗi trong vòng lặp                      │");
        System.out.println("│  - Xử lý chuỗi phức tạp                          │");
        System.out.println("│  - Single-thread (99% trường hợp)                │");
        System.out.println("│                                                  │");
        System.out.println("│  StringBuffer:                                   │");
        System.out.println("│  - CHỈ khi cần Thread-safe                       │");
        System.out.println("│  - Nhiều thread cùng sửa 1 biến chuỗi            │");
        System.out.println("│  - Hiếm dùng (legacy code)                       │");
        System.out.println("└──────────────────────────────────────────────────┘");

        System.out.println("\n=== 6. VÍ DỤ THỰC TẾ ===\n");

        // ❌ TRÁNH: Nối chuỗi bằng + trong loop
        System.out.println("❌ TRÁNH:");
        System.out.println("String sql = \"SELECT * FROM users WHERE \";");
        System.out.println("for (String field : fields) {");
        System.out.println("    sql += field + \" AND \";  // Chậm!");
        System.out.println("}\n");

        // ✅ NÊN: Dùng StringBuilder
        System.out.println("✅ NÊN:");
        System.out.println("StringBuilder sql = new StringBuilder(\"SELECT * FROM users WHERE \");");
        System.out.println("for (String field : fields) {");
        System.out.println("    sql.append(field).append(\" AND \");  // Nhanh!");
        System.out.println("}");
    }
}
