class StringPoolDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. STRING POOL LÀ GÌ? ===\n");

        /*
         * STRING POOL (vùng nhớ lưu chuỗi):
         * - Là vùng nhớ đặc biệt trong Heap để lưu các String literals
         * - Mục đích: Tiết kiệm bộ nhớ bằng cách tái sử dụng chuỗi giống nhau
         * - Khi tạo String bằng literal ("..."), Java sẽ:
         *   1. Kiểm tra String Pool xem đã có chuỗi này chưa
         *   2. Nếu có: trả về reference đến chuỗi đã tồn tại
         *   3. Nếu chưa: tạo mới và thêm vào Pool
         */

        // ===================================================================
        System.out.println("=== 2. TẠO STRING BẰNG LITERAL (dùng String Pool) ===\n");

        String s1 = "Hello";  // Tạo bằng literal -> vào String Pool
        String s2 = "Hello";  // Cùng literal -> TỪ CÙNG 1 VỊ TRÍ trong Pool
        String s3 = "Hello";  // Vẫn cùng vị trí!

        // So sánh địa chỉ bộ nhớ (reference) bằng ==
        System.out.println("s1 == s2: " + (s1 == s2)); // true (cùng địa chỉ)
        System.out.println("s1 == s3: " + (s1 == s3)); // true (cùng địa chỉ)
        System.out.println("✅ Tất cả đều trỏ đến CÙNG 1 object trong String Pool!\n");

        // ===================================================================
        System.out.println("=== 3. TẠO STRING BẰNG new (KHÔNG dùng String Pool) ===\n");

        String s4 = new String("Hello"); // Tạo bằng new -> TẠO OBJECT MỚI trong Heap
        String s5 = new String("Hello"); // Lại tạo thêm 1 object mới nữa!

        System.out.println("s1 == s4: " + (s1 == s4)); // false (khác địa chỉ)
        System.out.println("s4 == s5: " + (s4 == s5)); // false (2 object riêng biệt)
        System.out.println("❌ Mỗi lần new String() = tạo object mới, TỐN BỘ NHỚ!\n");

        // ===================================================================
        System.out.println("=== 4. SO SÁNH GIÁ TRỊ CHUỖI: PHẢI DÙNG .equals() ===\n");

        // == so sánh ĐỊA CHỈ BỘ NHỚ (reference)
        // .equals() so sánh NỘI DUNG (value)

        char[] s6 = {'J', 'a', 'v', 'a'};
        String s7 = new String("Java");

        System.out.println("s6 == s7: " + (s6 == s7.toCharArray())); // false (khác địa chỉ)
        System.out.println("s6.equals(s7): " + s6.equals(s7)); // true (cùng nội dung "Java")
        System.out.println("✅ LUÔN DÙNG .equals() để so sánh chuỗi!\n");

        // ===================================================================
        System.out.println("=== 5. PHƯƠNG THỨC .intern() - Đưa chuỗi vào Pool ===\n");

        String s8 = new String("World");     // Tạo object mới ngoài Pool
        String s9 = s8.intern();             // Đưa "World" vào Pool và trả về reference
        String s10 = "World";                // Lấy từ Pool

        System.out.println("s8 == s9: " + (s8 == s9));   // false (s8 ở ngoài Pool)
        System.out.println("s9 == s10: " + (s9 == s10)); // true (cùng trong Pool)
        System.out.println("✅ .intern() giúp tái sử dụng chuỗi trong Pool\n");

        // ===================================================================
        System.out.println("=== 6. CHUỖI NỐI (Concatenation) ===\n");

        // Nối literal tại COMPILE TIME -> vào Pool
        String s11 = "Hel" + "lo";  // Compiler tự động tối ưu thành "Hello"
        String s12 = "Hello";
        System.out.println("s11 == s12: " + (s11 == s12)); // true (cùng Pool)

        // Nối variable tại RUNTIME -> TẠO OBJECT MỚI
        String part1 = "Hel";
        String part2 = "lo";
        String s13 = part1 + part2; // Tạo object mới tại runtime
        System.out.println("s13 == s12: " + (s13 == s12)); // false (khác địa chỉ)
        System.out.println("s13.equals(s12): " + s13.equals(s12)); // true (cùng nội dung)

        // ===================================================================
        System.out.println("\n=== 7. MAPPING VỚI PHP/JS ===");
        System.out.println("\n--- PHP: ---");
        System.out.println("$s1 = 'Hello';");
        System.out.println("$s2 = 'Hello';");
        System.out.println("$s1 === $s2; // true (PHP so sánh VALUE, không có String Pool)");

        System.out.println("\n--- JavaScript: ---");
        System.out.println("const s1 = 'Hello';");
        System.out.println("const s2 = 'Hello';");
        System.out.println("s1 === s2; // true (JS engine có String Interning tương tự)");

        System.out.println("\n--- Java: ---");
        System.out.println("String s1 = \"Hello\";");
        System.out.println("String s2 = \"Hello\";");
        System.out.println("s1 == s2; // true (cùng reference trong Pool)");
        System.out.println("NHƯNG NÊN DÙNG: s1.equals(s2); // Best practice!");

        // ===================================================================
        System.out.println("\n=== 8. VISUALIZE BỘ NHỚ ===\n");

        String literal1 = "ABC";
        String literal2 = "ABC";
        String object1 = new String("ABC");
        String object2 = new String("ABC");

        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│         STRING POOL (Heap)          │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  \"ABC\" ← literal1, literal2        │");
        System.out.println("└─────────────────────────────────────┘");
        System.out.println("");
        System.out.println("┌─────────────────────────────────────┐");
        System.out.println("│          HEAP (ngoài Pool)          │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  String@123 (\"ABC\") ← object1      │");
        System.out.println("│  String@456 (\"ABC\") ← object2      │");
        System.out.println("└─────────────────────────────────────┘");

        System.out.println("\nliteral1 == literal2: " + (literal1 == literal2)); // true
        System.out.println("object1 == object2: " + (object1 == object2));       // false
    }

    /*
     * =====================================================================
     * TÓM TẮT QUAN TRỌNG:
     * =====================================================================
     *
     * 1. CÁC CÁCH TẠO STRING:
     *    String s1 = "Hello";          // Literal -> vào String Pool
     *    String s2 = new String("Hi"); // new -> tạo object mới ngoài Pool
     *
     * 2. QUY TẮC SO SÁNH:
     *    ==        : So sánh địa chỉ bộ nhớ (reference)
     *    .equals() : So sánh nội dung (value) ✅ LUÔN DÙNG CÁI NÀY!
     *
     * 3. TẠI SAO PHẢI DÙNG .equals()?
     *    - Vì không biết String được tạo bằng literal hay new
     *    - == có thể cho kết quả sai khi so sánh String từ new
     *    - .equals() LUÔN so sánh nội dung, an toàn 100%
     *
     * 4. LỢI ÍCH CỦA STRING POOL:
     *    - Tiết kiệm bộ nhớ (tái sử dụng chuỗi giống nhau)
     *    - Tăng hiệu suất (không tạo object trùng lặp)
     *
     * 5. KHI NÀO DÙNG .intern()?
     *    - Khi có nhiều String từ nguồn bên ngoài (DB, file, API)
     *    - Muốn tái sử dụng chuỗi để tiết kiệm bộ nhớ
     *    - CHÚ Ý: Không lạm dụng vì String Pool có giới hạn
     *
     * 6. SO VỚI PHP/JS:
     *    PHP:  $s1 === $s2; // So sánh value trực tiếp
     *    JS:   s1 === s2;   // Có String Interning nhưng nên dùng ===
     *    Java: s1.equals(s2); // PHẢI dùng .equals() để an toàn!
     *
     * =====================================================================
     * LỖI THƯỜNG GẶP:
     * =====================================================================
     *
     * ❌ SAI:
     * String name = getUserName(); // Lấy từ DB/API
     * if (name == "admin") { ... } // SAI! So sánh địa chỉ, có thể false!
     *
     * ✅ ĐÚNG:
     * String name = getUserName();
     * if ("admin".equals(name)) { ... } // An toàn, tránh NullPointerException
     *
     * TIP: Đặt literal trước .equals() để tránh lỗi NullPointerException:
     * "admin".equals(name)  ✅ An toàn (name null cũng không lỗi)
     * name.equals("admin")  ❌ Lỗi nếu name = null
     *
     * =====================================================================
     */
}
