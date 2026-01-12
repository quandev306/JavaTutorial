import java.util.ArrayList;
import java.util.List;

class GenericsDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. LÝ DO CẦN GENERICS ===\n");

        // CÁCH CŨ (không dùng Generics) - NGUY HIỂM!
        ArrayList oldList = new ArrayList(); // Không chỉ định kiểu
        oldList.add("Hùng");
        oldList.add("Long");
        oldList.add(123); // Thêm số vào? Compiler KHÔNG BÁO LỖI!

        // Runtime Error khi lấy ra!
        try {
            String name = (String) oldList.get(2); // Phải ép kiểu thủ công
        } catch (ClassCastException e) {
            System.out.println("❌ Lỗi: Không thể convert Integer sang String!");
        }

        // CÁCH MỚI (dùng Generics) - AN TOÀN!
        ArrayList<String> newList = new ArrayList<String>();
        newList.add("Hùng");
        newList.add("Long");
        // newList.add(123); // Compiler BÁO LỖI NGAY! Type safety!

        String name = newList.get(0); // Không cần ép kiểu
        System.out.println("✅ Tên đầu tiên: " + name + "\n");

        // ===================================================================
        System.out.println("=== 2. GENERIC CLASS (Tạo class tổng quát) ===\n");

        // Box có thể chứa bất kỳ kiểu dữ liệu nào
        Box<String> stringBox = new Box<>("Java Generics");
        Box<Integer> intBox = new Box<>(2024);
        Box<Boolean> boolBox = new Box<>(true);

        System.out.println("String Box: " + stringBox.get());
        System.out.println("Integer Box: " + intBox.get());
        System.out.println("Boolean Box: " + boolBox.get());

        // ===================================================================
        System.out.println("\n=== 3. GENERIC METHODS (Hàm tổng quát) ===\n");

        String[] names = { "Hùng", "Long", "Minh" };
        Integer[] numbers = { 1, 2, 3 };

        System.out.print("Mảng String: ");
        printArray(names); // Tự động nhận dạng kiểu String

        System.out.print("Mảng Integer: ");
        printArray(numbers); // Tự động nhận dạng kiểu Integer

        // ===================================================================
        System.out.println("\n=== 4. BOUNDED TYPES (Giới hạn kiểu) ===\n");

        // Chỉ chấp nhận Number và các class con (Integer, Double, Float...)
        System.out.println("Tổng Integer: " + sum(10, 20));
        System.out.println("Tổng Double: " + sum(10.5, 20.3));
        // System.out.println(sum("A", "B")); // Lỗi! String không phải Number

        // ===================================================================
        System.out.println("\n=== 5. WILDCARDS (Đại diện kiểu không xác định) ===\n");

        List<Integer> intList = List.of(1, 2, 3);
        List<String> stringList = List.of("A", "B", "C");

        printList(intList); // Chấp nhận List của bất kỳ kiểu nào
        printList(stringList);

        // ===================================================================
        System.out.println("\n=== 6. MAPPING VỚI PHP/JS ===");
        System.out.println("PHP: $arr = ['string', 123, true]; // Mảng chứa bừa bãi");
        System.out.println("Java: ArrayList<String> list; // CHỈ chứa String!");
        System.out.println("\nJS: const arr = [1, 'text']; // TypeScript có Generics tương tự");
        System.out.println("Java: ArrayList<Integer> nums; // Type-safe!");
    }

    // =================== GENERIC CLASS ===================
    // T là Type Parameter (tham số kiểu), có thể đặt tên bất kỳ (T, E, K, V...)
    // Quy ước: T = Type, E = Element, K = Key, V = Value
    static class Box<T> {
        private T value;

        public Box(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }

        public void set(T value) {
            this.value = value;
        }
    }

    // =================== GENERIC METHOD ===================
    // <T> trước return type để khai báo method này dùng Generics
    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.print(item + " ");
        }
        System.out.println();
    }

    // =================== BOUNDED TYPE PARAMETER ===================
    // <T extends Number> = T phải là Number hoặc class con của Number
    public static <T extends Number> double sum(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    // =================== WILDCARD <?> ===================
    // <?> = chấp nhận List của bất kỳ kiểu nào
    // Chỉ đọc được, không thêm được phần tử mới
    public static void printList(List<?> list) {
        System.out.print("List: ");
        for (Object item : list) { // Phải dùng Object vì không biết kiểu cụ thể
            System.out.print(item + " ");
        }
        System.out.println();
    }

    /*
     * LƯU Ý QUAN TRỌNG:
     *
     * 1. TYPE ERASURE (Xóa kiểu sau compile):
     * - Compiler sẽ xóa tất cả thông tin Generic sau khi compile
     * - ArrayList<String> và ArrayList<Integer> đều thành ArrayList trong
     * bytecode
     * - Mục đích: Tương thích ngược với Java phiên bản cũ (trước Java 5)
     *
     * 2. KHÔNG thể tạo Generic array:
     * - T[] arr = new T[10]; // SAI!
     * - Phải dùng: T[] arr = (T[]) new Object[10]; // Đúng nhưng có warning
     *
     * 3. KHÔNG thể dùng Primitive types:
     * - ArrayList<int> list; // SAI! Phải dùng Integer
     * - ArrayList<Integer> list; // Đúng!
     *
     * 4. SO SÁNH WILDCARDS:
     * - <? extends T>: Chỉ ĐỌC được (Producer)
     * - <? super T>: Chỉ GHI được (Consumer)
     * - <?>: Đọc được nhưng chỉ nhận Object
     */
}
