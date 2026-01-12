public class StringBuilderDemo {
    public static void main(String[] args) {
        System.out.println("=== SO SÁNH HIỆU NĂNG NỐI CHUỖI ===\n");

        int iterations = 100000;

        // 1. Dùng String (Concatenation +)
        // Mỗi lần tìm thấy += là Java tạo ra StringBuilder ẩn, nối xong lại toString() -> tạo rác
        long startTime = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < iterations; i++) {
            s += "a";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("String (+): " + (endTime - startTime) + "ms (Rất chậm!)");

        // 2. Dùng StringBuilder (Non-thread-safe) -> NHANH NHẤT
        startTime = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuilder: " + (endTime - startTime) + "ms (Cực nhanh)");

        // 3. Dùng StringBuffer (Thread-safe) -> Chậm hơn StringBuilder chút xíu do synchronized
        startTime = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sbf.append("a");
        }
        endTime = System.currentTimeMillis();
        System.out.println("StringBuffer: " + (endTime - startTime) + "ms (An toàn luồng)");

        System.out.println("\n=== KHI NÀO DÙNG CÁI NÀO? ===");
        System.out.println("1. String: Dùng cho hằng số, ít thay đổi.");
        System.out.println("2. StringBuilder: Dùng trong vòng lặp, xử lý chuỗi nhiều (99% trường hợp).");
        System.out.println("3. StringBuffer: Chỉ dùng trong môi trường Multi-thread (đa luồng) cần chia sẻ biến chuỗi.");

        System.out.println("\n=== DEMO MUTABLE (Thay đổi giá trị) ===");
        StringBuilder demo = new StringBuilder("Hello");
        System.out.println("Gốc: " + demo);
        // append: nối đuôi
        demo.append(" World");
        System.out.println("Sau append: " + demo);
        // insert: chèn giữa
        demo.insert(5, " Java");
        System.out.println("Sau insert: " + demo);
        // delete: xóa
        demo.delete(5, 10);
        System.out.println("Sau delete: " + demo);
        // reverse: đảo ngược
        demo.reverse();
        System.out.println("Sau reverse: " + demo);
    }
}
