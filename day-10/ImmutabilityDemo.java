import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * IMMUTABILITY (TÍNH BẤT BIẾN)
 *
 * Object không thể thay đổi sau khi tạo = An toàn, Thread-safe, Dễ debug.
 */
public class ImmutabilityDemo {
    public static void main(String[] args) {
        System.out.println("=== STRING LÀ IMMUTABLE ===\n");

        String name = "Alice";
        String upper = name.toUpperCase();

        System.out.println("Original: " + name);   // Alice (không đổi)
        System.out.println("Upper: " + upper);     // ALICE (object mới)

        System.out.println("\n=== IMMUTABLE CLASS: MONEY ===\n");

        Money m1 = new Money(100, "USD");
        Money m2 = new Money(50, "USD");
        Money m3 = m1.add(m2);  // Tạo object MỚI, không sửa m1 hay m2

        System.out.println("m1: " + m1);
        System.out.println("m2: " + m2);
        System.out.println("m1 + m2 = m3: " + m3);
        System.out.println("m1 vẫn là: " + m1);  // Không đổi!

        System.out.println("\n=== DEFENSIVE COPY ===\n");

        // Tạo Event với defensive copy
        Date now = new Date();
        Event event = new Event("Meeting", now);

        System.out.println("Before modification: " + event.getDate());

        // Cố tình sửa date bên ngoài
        now.setTime(0);
        System.out.println("After modifying 'now': " + event.getDate());
        // Event vẫn giữ nguyên giá trị ban đầu!

        System.out.println("\n=== IMMUTABLE LIST ===\n");

        // Mutable list
        List<String> mutableList = new ArrayList<>();
        mutableList.add("A");
        mutableList.add("B");
        System.out.println("Mutable: " + mutableList);

        // Immutable list (Java 9+)
        List<String> immutableList = List.of("X", "Y", "Z");
        System.out.println("Immutable: " + immutableList);

        try {
            immutableList.add("W");  // ❌ UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify immutable list!");
        }

        System.out.println("\n=== IMMUTABLE PERSON (FULL EXAMPLE) ===\n");

        List<String> hobbies = new ArrayList<>();
        hobbies.add("Reading");
        hobbies.add("Gaming");

        ImmutablePerson person = new ImmutablePerson("Bob", 25, hobbies);
        System.out.println("Person: " + person);

        // Cố tình sửa list bên ngoài
        hobbies.add("Hacking");
        System.out.println("After modifying hobbies list:");
        System.out.println("Person hobbies: " + person.getHobbies());
        // Person không bị ảnh hưởng!

        // Cố tình sửa list trả về
        try {
            person.getHobbies().add("Breaking");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify returned hobbies list!");
        }
    }
}

// ==================== IMMUTABLE CLASS: MONEY ====================

final class Money {
    private final double amount;
    private final String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    // Thay vì sửa, tạo object MỚI
    public Money add(Money other) {
        if (!this.currency.equals(other.currency)) {
            throw new IllegalArgumentException("Currency mismatch");
        }
        return new Money(this.amount + other.amount, this.currency);
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}

// ==================== DEFENSIVE COPY: EVENT ====================

final class Event {
    private final String name;
    private final Date date;

    public Event(String name, Date date) {
        this.name = name;
        this.date = new Date(date.getTime());  // Defensive copy khi nhận
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(date.getTime());  // Defensive copy khi trả
    }
}

// ==================== IMMUTABLE WITH COLLECTION ====================

final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutablePerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        // Defensive copy: Tạo list mới, không giữ reference
        this.hobbies = new ArrayList<>(hobbies);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        // Trả về unmodifiable view
        return Collections.unmodifiableList(hobbies);
    }

    @Override
    public String toString() {
        return "ImmutablePerson{name='" + name + "', age=" + age + ", hobbies=" + hobbies + "}";
    }
}
