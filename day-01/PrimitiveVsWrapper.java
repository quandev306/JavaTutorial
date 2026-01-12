class PrimitiveVsWrapper {
    public static void main(String[] args) {
        /*
         * 1. Phân cấp kiểu dữ liệu (Widening Conversion):
         * byte (8b) < short (16b) < int (32b) < long (64b) < float (32b) < double (64b)
         *
         * ĐIỂM CẦN LƯU Ý: Tại sao long (64-bit) < float (32-bit)?
         * - Thứ tự này dựa trên KHOẢNG GIÁ TRỊ (Range), không phải số bits.
         * - float dùng cơ chế số thực dấu phẩy động (exponential) nên có thể biểu diễn
         * những con số cực lớn mà long (số nguyên) không thể chứa được.
         * - Do đó, Java cho phép tự động chuyển (widening) từ long sang float.
         */

        // primitive: Lưu giá trị thực, không thể là null. Tốn ít bộ nhớ.
        int a = 10;

        // Wrapper class: Là Object, có thể là null. Dùng trong Collections (ArrayList,
        // HashMap).
        Integer b = null;

        System.out.println("--- So sánh Primitive vs Wrapper ---");
        System.out.println("Giá trị a (int): " + a);
        System.out.println("Giá trị b (Integer): " + b);

        /*
         * 2. Lưu ý về NullPointerException (NPE):
         * Lỗi bạn gặp khi chạy code cũ là do ép kiểu b (đang null) sang int để tính
         * toán.
         */
        try {
            // int sum = a + b; // Sẽ gây lỗi NullPointerException vì b là null
            // System.out.println("Sum: " + sum);
        } catch (NullPointerException e) {
            System.out.println("Lỗi: Không thể tính toán với giá trị null!");
        }

        // 3. Các kiểu dữ liệu khác
        double c = 0.5;
        Double d = 0.5; // Autoboxing: Tự động chuyển 0.5 (double) sang Double object

        boolean e = true;
        Boolean f = Boolean.FALSE;

        float g = 1.2f; // Phải có hậu tố 'f'
        Float h = 1.2f;
        long i = 1000L; // Phải có hậu tố 'L'
        Long j = 1000L;

        System.out.println("\n--- Các kiểu khác ---");
        System.out.println("Double object: " + d);
        System.out.println("Boolean object: " + f);
        System.out.println("Float primitive: " + g);
    }
}
