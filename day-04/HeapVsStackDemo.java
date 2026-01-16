public class HeapVsStackDemo {
    public static void main(String[] args) {
        System.out.println("=== 1. STACK MEMORY (Ngăn xếp) ===");
        System.out.println("- Lưu trữ: Biến cục bộ (primitive), tham chiếu (reference), method call frame.");
        System.out.println("- Đặc điểm: Nhanh, tự động giải phóng khi ra khỏi hàm (scope).");
        System.out.println("- Lỗi phổ biến: StackOverflowError (khi đệ quy vô hạn).");

        int x = 10; // Lưu trực tiếp trong STACK
        System.out.println("\n-> Biến x = " + x + " được lưu trong Stack.");

        System.out.println("\n=== 2. HEAP MEMORY (Vùng nhớ Heap) ===");
        System.out.println("- Lưu trữ: Tất cả các OBJECT (new ...).");
        System.out.println("- Đặc điểm: Sống lâu, cần Garbage Collector (GC) dọn dẹp.");
        System.out.println("- Lỗi phổ biến: OutOfMemoryError (khi tạo quá nhiều object mà không giải phóng).");

        Person p = new Person("Nam", 25);
        // 1. Biến 'p' (reference) nằm trong STACK
        // 2. Object 'Person("Nam", 25)' thực sự nằm trong HEAP
        // 3. 'p' trỏ tới địa chỉ của Object trong Heap

        System.out.println("\n-> Object Person " + p + " nằm trong Heap.");
        System.out.println("-> Biến reference 'p' nằm trong Stack để trỏ tới Heap.");

        System.out.println("\n=== 3. VÍ DỤ CHUYỀN THAM TRỊ vs THAM CHIẾU ===");

        // Primitive (nguyên thủy) - Pass by Value (Copy giá trị)
        int a = 5;
        changePrimitive(a);
        System.out.println("Sau khi gọi hàm changePrimitive(a), a = " + a + " (Không đổi)");

        // Object (đối tượng) - Pass by Value of Reference (Copy địa chỉ)
        // Tuy nhiên, vì copy địa chỉ nên hàm con có thể sửa nội dung object thật trong Heap
        changeObject(p);
        System.out.println("Sau khi gọi hàm changeObject(p), p.name = " + p.name + " (Đã đổi!)");

        // Wrapper Class (Integer) - Immutable Object
        // Integer là Object, nhưng bất biến (Immutable).
        // Khi thay đổi giá trị, Java tạo ra Object mới, không sửa Object cũ.
        Integer b = 10;
        changeInteger(b);
        System.out.println("Sau khi gọi hàm changeInteger(b), b = " + b + " (Không đổi - do Immutable)");
    }

    // Biến 'num' là bản copy của 'a', nằm trong Stack frame mới của hàm này
    // Thay đổi 'num' không ảnh hưởng 'a' ở hàm main
    public static void changePrimitive(int num) {
        num = 99;
    }

    // Biến 'personRef' là bản copy của 'p', cũng trỏ tới CÙNG object trong Heap
    // Dùng 'personRef' để thay đổi dữ liệu trong Heap -> 'p' cũng thấy thay đổi
    public static void changeObject(Person personRef) {
        personRef.name = "Updated Name";
    }

    // Biến 'numWrapper' là bản copy của tham chiếu 'b'.
    // Nhưng Integer là IMMUTABLE (Bất biến).
    // Dòng 'numWrapper = 88;' KHÔNG thay đổi giá trị trong Object cũ,
    // mà nó tạo ra một Object Integer(88) mới và gán cho biến local 'numWrapper'.
    // Biến 'b' ở hàm main vẫn trỏ tới Object Integer(10) cũ.
    public static void changeInteger(int numWrapper) {
        numWrapper = 88;
    }
}

class Person {
    String name; // Instance variable -> Nằm trong Heap (cùng với Object Person)
    int age;     // Instance variable -> Nằm trong Heap

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{name='" + name + "', age=" + age + "}";
    }
}
