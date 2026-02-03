# Ngày 8: Polymorphism (Đa hình)

> [!TIP]
> **Mental Model:**
>
> - **PHP:** Đa hình thường dùng qua duck typing, không cần khai báo type.
> - **Java:** Đa hình là **TIM của OOP**. Một biến kiểu cha có thể chứa bất kỳ con nào. JVM tự biết gọi đúng method của con tại runtime.

---

## 1. Đa hình là gì?

**Polymorphism** = "Nhiều hình dạng". Cùng một hành động nhưng biểu hiện khác nhau tùy đối tượng.

### Ví dụ thực tế:

- Gọi `makeSound()` trên `Dog` -> "Gâu gâu"
- Gọi `makeSound()` trên `Cat` -> "Meo meo"
- Gọi `makeSound()` trên `Cow` -> "Moo moo"

**Cùng 1 hành động, nhiều kết quả khác nhau.**

---

## 2. Hai loại Đa hình

### 2.1. Compile-time Polymorphism (Method Overloading)

Xảy ra khi có **nhiều method cùng tên, khác tham số** trong cùng class.

```java
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
    int add(int a, int b, int c) { return a + b + c; }
}
```

**Compiler quyết định** gọi method nào dựa trên tham số truyền vào.

### 2.2. Runtime Polymorphism (Method Overriding)

Xảy ra khi **class con ghi đè method của class cha**. JVM quyết định gọi method nào **tại runtime**.

```java
Animal animal = new Dog();  // Biến kiểu Animal, object kiểu Dog
animal.makeSound();         // Gọi makeSound() của Dog, không phải Animal
```

> [!IMPORTANT]
> **Đây là loại đa hình quan trọng nhất!** Spring Boot, Design Patterns đều dựa vào Runtime Polymorphism.

---

## 3. Upcasting & Downcasting

### Upcasting (Tự động)

Biến kiểu cha giữ object kiểu con. **An toàn, tự động.**

```java
Animal animal = new Dog();  // Upcasting: Dog -> Animal
```

### Downcasting (Thủ công, nguy hiểm)

Ép kiểu từ cha về con. **Có thể lỗi runtime nếu sai.**

```java
Animal animal = new Dog();
Dog dog = (Dog) animal;     // Downcasting: Animal -> Dog (OK vì thực sự là Dog)

Animal animal2 = new Cat();
Dog dog2 = (Dog) animal2;   // ❌ ClassCastException! Cat không phải Dog
```

### Kiểm tra an toàn với `instanceof`

```java
if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
    dog.fetch();  // Method riêng của Dog
}
```

---

## 4. Tại sao Đa hình quan trọng?

### ❌ Không dùng Đa hình

```java
void processAnimal(Dog dog) { dog.makeSound(); }
void processAnimal(Cat cat) { cat.makeSound(); }
void processAnimal(Cow cow) { cow.makeSound(); }
// Thêm 10 loài nữa = 10 hàm nữa 😱
```

### ✅ Dùng Đa hình

```java
void processAnimal(Animal animal) {
    animal.makeSound();  // Tự động gọi đúng method của từng loài
}
// 1 hàm cho tất cả 🎉
```

---

## 5. Đa hình với Interface

Interface là nơi **đa hình tỏa sáng nhất**.

```java
interface Drawable {
    void draw();
}

class Circle implements Drawable {
    public void draw() { System.out.println("Drawing Circle"); }
}

class Square implements Drawable {
    public void draw() { System.out.println("Drawing Square"); }
}

// Sử dụng
List<Drawable> shapes = List.of(new Circle(), new Square());
for (Drawable shape : shapes) {
    shape.draw();  // Đa hình: Circle.draw() hoặc Square.draw()
}
```

---

## 6. Tổng kết

| Khái niệm       | Giải thích                                    |
| :-------------- | :-------------------------------------------- |
| **Overloading** | Cùng tên, khác tham số (Compile-time)         |
| **Overriding**  | Con ghi đè method cha (Runtime)               |
| **Upcasting**   | Biến cha = object con (An toàn, tự động)      |
| **Downcasting** | Ép về con (Nguy hiểm, dùng `instanceof`)      |
| **Lợi ích**     | Viết code linh hoạt, dễ mở rộng, ít duplicate |

> [!IMPORTANT]
> **Quy tắc:** Khi thiết kế, nghĩ về **hành vi chung** (interface/abstract) trước, rồi mới nghĩ **implementation cụ thể**.
