import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * OOP TỔNG KẾT
 *
 * Demo tổng hợp các khái niệm OOP đã học từ Day 6-11:
 * - Interface & Abstract Class
 * - Encapsulation
 * - Polymorphism
 * - Static & Final
 * - Immutability
 * - Composition over Inheritance
 */
public class OOPSummaryDemo {
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     OOP SUMMARY - LIBRARY SYSTEM         ║");
        System.out.println("╚══════════════════════════════════════════╝\n");

        // 1. Tạo sách (Immutable)
        Book book1 = new Book("B001", "Clean Code", "Robert Martin");
        Book book2 = new Book("B002", "Effective Java", "Joshua Bloch");
        Book book3 = new Book("B003", "Design Patterns", "Gang of Four");

        System.out.println("=== BOOKS (Immutable) ===");
        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        // 2. Tạo thành viên
        Member member1 = new Member("M001", "Alice");
        Member member2 = new Member("M002", "Bob");

        System.out.println("\n=== MEMBERS ===");
        System.out.println(member1);
        System.out.println(member2);

        // 3. Tạo LibraryService với Composition
        NotificationSender emailSender = new EmailSender();
        LibraryService library = new LibraryService(emailSender);

        System.out.println("\n=== BORROWING (Polymorphism + Composition) ===");
        library.borrowBook(member1, book1);
        library.borrowBook(member1, book2);
        library.borrowBook(member2, book3);

        System.out.println("\n=== MEMBER STATUS ===");
        System.out.println(member1.getName() + " borrowed: " + member1.getBorrowedBooks().size() + " books");
        System.out.println(member2.getName() + " borrowed: " + member2.getBorrowedBooks().size() + " books");

        System.out.println("\n=== RETURNING ===");
        library.returnBook(member1, book1);

        System.out.println("\n=== STATIC: LIBRARY STATS ===");
        System.out.println("Total transactions: " + LibraryService.getTotalTransactions());

        System.out.println("\n=== POLYMORPHISM: DIFFERENT NOTIFICATION ===");
        // Swap notification sender (Composition power!)
        NotificationSender smsSender = new SMSSender();
        LibraryService library2 = new LibraryService(smsSender);
        library2.borrowBook(member2, book1);

        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║           CONCEPTS DEMONSTRATED          ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ ✓ Encapsulation: private fields          ║");
        System.out.println("║ ✓ Immutability: Book class               ║");
        System.out.println("║ ✓ Interface: Borrowable, Notification    ║");
        System.out.println("║ ✓ Polymorphism: NotificationSender       ║");
        System.out.println("║ ✓ Composition: LibraryService            ║");
        System.out.println("║ ✓ Static: Transaction counter            ║");
        System.out.println("║ ✓ Final: Immutable fields                ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }
}

// ==================== IMMUTABLE: BOOK ====================

final class Book {
    private final String id;
    private final String title;
    private final String author;

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }

    @Override
    public String toString() {
        return "📚 " + title + " by " + author + " [" + id + "]";
    }
}

// ==================== INTERFACE: BORROWABLE ====================

interface Borrowable {
    void borrow(Book book);
    void returnBook(Book book);
    List<Book> getBorrowedBooks();
}

// ==================== MEMBER (IMPLEMENTS BORROWABLE) ====================

class Member implements Borrowable {
    private final String id;
    private final String name;
    private final List<Book> borrowedBooks;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public void borrow(Book book) {
        borrowedBooks.add(book);
    }

    @Override
    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    @Override
    public String toString() {
        return "👤 " + name + " [" + id + "]";
    }
}

// ==================== INTERFACE: NOTIFICATION ====================

interface NotificationSender {
    void send(String to, String message);
}

class EmailSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        System.out.println("📧 Email to " + to + ": " + message);
    }
}

class SMSSender implements NotificationSender {
    @Override
    public void send(String to, String message) {
        System.out.println("📱 SMS to " + to + ": " + message);
    }
}

// ==================== COMPOSITION: LIBRARY SERVICE ====================

class LibraryService {
    // Static: Shared counter
    private static int totalTransactions = 0;

    // Composition: NotificationSender được inject
    private final NotificationSender notificationSender;

    public LibraryService(NotificationSender notificationSender) {
        this.notificationSender = notificationSender;
    }

    public void borrowBook(Member member, Book book) {
        member.borrow(book);
        totalTransactions++;
        notificationSender.send(
            member.getName(),
            "You borrowed: " + book.getTitle()
        );
    }

    public void returnBook(Member member, Book book) {
        member.returnBook(book);
        totalTransactions++;
        notificationSender.send(
            member.getName(),
            "You returned: " + book.getTitle()
        );
    }

    public static int getTotalTransactions() {
        return totalTransactions;
    }
}
