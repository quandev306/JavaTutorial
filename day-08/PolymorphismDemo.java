/**
 * POLYMORPHISM (ĐA HÌNH)
 * 
 * Cùng một hành động, nhiều cách thể hiện khác nhau.
 * JVM tự biết gọi đúng method của object thực tế tại runtime.
 */
public class PolymorphismDemo {
    public static void main(String[] args) {
        System.out.println("=== RUNTIME POLYMORPHISM ===\n");

        // Upcasting: Biến kiểu Animal, object kiểu cụ thể
        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Kitty");
        Animal cow = new Cow("Bella");

        // Đa hình: Cùng gọi makeSound(), kết quả khác nhau
        dog.makeSound();  // Gâu gâu
        cat.makeSound();  // Meo meo
        cow.makeSound();  // Moo moo

        System.out.println("\n=== XỬ LÝ DANH SÁCH ĐA HÌNH ===\n");

        // Một hàm xử lý được tất cả các loại Animal
        Animal[] animals = {dog, cat, cow, new Dog("Max")};
        for (Animal animal : animals) {
            processAnimal(animal);
        }

        System.out.println("\n=== DOWNCASTING VỚI INSTANCEOF ===\n");

        // Downcasting an toàn
        for (Animal animal : animals) {
            if (animal instanceof Dog) {
                Dog d = (Dog) animal;
                d.fetch();  // Method riêng của Dog
            }
        }

        System.out.println("\n=== COMPILE-TIME POLYMORPHISM (OVERLOADING) ===\n");

        Calculator calc = new Calculator();
        System.out.println("add(1, 2) = " + calc.add(1, 2));
        System.out.println("add(1.5, 2.5) = " + calc.add(1.5, 2.5));
        System.out.println("add(1, 2, 3) = " + calc.add(1, 2, 3));
    }

    // Một hàm xử lý TẤT CẢ các loại Animal nhờ đa hình
    static void processAnimal(Animal animal) {
        System.out.print(animal.getName() + ": ");
        animal.makeSound();
    }
}

// ==================== ABSTRACT CLASS ====================

abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Method abstract: Con phải implement
    public abstract void makeSound();
}

// ==================== CONCRETE CLASSES ====================

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Gâu gâu! 🐕");
    }

    // Method riêng của Dog
    public void fetch() {
        System.out.println(getName() + " is fetching the ball! 🎾");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Meo meo! 🐱");
    }
}

class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("Moo moo! 🐄");
    }
}

// ==================== METHOD OVERLOADING ====================

class Calculator {
    // Cùng tên, khác tham số = Overloading
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
