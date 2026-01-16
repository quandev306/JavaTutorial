/**
 * Ngày 1: Primitive Types vs Wrapper Classes
 *
 * Mục tiêu:
 * - Phân biệt Primitive types và Wrapper Classes
 * - Hiểu Autoboxing/Unboxing
 */
public class PrimitiveVsWrapper {
    public static void main(String[] args) {
        System.out.println("=== 1. PRIMITIVE TYPES ===\n");

        /*
         * Primitive types: Lưu GIÁ TRỊ THỰC trong Stack
         * - Không thể là null
         * - Tốn ít bộ nhớ
         * - Không có methods
         */
        int a = 10;
        double b = 3.14;
        boolean c = true;
        char d = 'A';

        System.out.println("int a = " + a);
        System.out.println("double b = " + b);
        System.out.println("boolean c = " + c);
        System.out.println("char d = " + d);

        // int e = null; // ❌ LỖI! Primitive không thể null

        System.out.println("\n=== 2. WRAPPER CLASSES ===\n");

        /*
         * Wrapper classes: Lưu OBJECT trong Heap
         * - Có thể là null trong nhiều trường hợp phổ biến như dùng cho DTO, Entity, Form hoặc Request 
         * - Dùng trong Collections (ArrayList, HashMap)
         * - Có methods hữu ích
         * - Tốn nhiều bộ nhớ hơn primitive
         */
        Integer x = 10;        // Integer thay cho int
        Double y = 3.14;       // Double thay cho double
        Boolean z = true;      // Boolean thay cho boolean
        Character w = 'A';     // Character thay cho char

        Integer nullable = null; // ✅ OK! Wrapper có thể null

        System.out.println("Integer x = " + x);
        System.out.println("Double y = " + y);
        System.out.println("Boolean z = " + z);
        System.out.println("Integer nullable = " + nullable);

        System.out.println("\n=== 3. AUTOBOXING (Primitive → Wrapper) ===\n");

        /*
         * Autoboxing: Java TỰ ĐỘNG chuyển primitive → wrapper
         */
        int primitiveInt = 100;
        Integer wrapperInt = primitiveInt; // Autoboxing: int → Integer

        System.out.println("primitiveInt = " + primitiveInt);
        System.out.println("wrapperInt (autoboxed) = " + wrapperInt);

        System.out.println("\n=== 4. UNBOXING (Wrapper → Primitive) ===\n");

        /*
         * Unboxing: Java TỰ ĐỘNG chuyển wrapper → primitive
         */
        Integer wrapperNum = 200;
        int primitiveNum = wrapperNum; // Unboxing: Integer → int

        System.out.println("wrapperNum = " + wrapperNum);
        System.out.println("primitiveNum (unboxed) = " + primitiveNum);

        System.out.println("\n=== 5. NGUY HIỂM: NullPointerException ===\n");

        /*
         * ⚠️ CẢNH BÁO: Unboxing null sẽ gây NullPointerException!
         */
        Integer nullWrapper = null;

        try {
            int result = nullWrapper; // ❌ NullPointerException!
            System.out.println("Result: " + result);
        } catch (NullPointerException e) {
            System.out.println("❌ LỖI NullPointerException!");
            System.out.println("   Không thể unbox null thành primitive!");
        }

        System.out.println("\n=== 6. SO SÁNH: == vs .equals() ===\n");

        /*
         * Primitive: dùng == (so sánh giá trị)
         * Wrapper: nên dùng .equals() (so sánh nội dung)
         */
        int p1 = 100;
        int p2 = 100;
        System.out.println("Primitive: p1 == p2 → " + (p1 == p2)); // true

        Integer w1 = 1000;
        Integer w2 = 1000;
        System.out.println("Wrapper: w1 == w2 → " + (w1 == w2)); // false (khác object)
        System.out.println("Wrapper: w1.equals(w2) → " + w1.equals(w2)); // true ✅

        // Lưu ý: Integer từ -128 đến 127 được cache, == có thể true
        Integer cached1 = 100;
        Integer cached2 = 100;
        System.out.println("Cached: cached1 == cached2 → " + (cached1 == cached2)); // true (do cache)
    }
}
