import java.util.Scanner;
import java.time.Year;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("\n1 - BMI\n2 - Обменник\n3 - Обменник с окончаниями\n4 - Автомобили\n0 - Выход");
            int choice = input.nextInt();
            if (choice == 0) break;
            switch (choice) {
                case 1: bmi(input); break;
                case 2: exchange(input, false); break;
                case 3: exchange(input, true); break;
                case 4: cars(); break;
                default: System.out.println("Нет такого пункта.");
            }
        }
        input.close();
    }

    static void bmi(Scanner input) {
        System.out.print("Вес (кг): ");
        double weight = Double.parseDouble(input.next().replace(',', '.'));
        System.out.print("Рост (см): ");
        double height = Double.parseDouble(input.next().replace(',', '.'));
        if (weight <= 0 || height <= 0) {
            System.out.println("Вес и рост должны быть больше нуля.");
            return;
        }
        double bmi = weight / (height / 100 * height / 100);
        System.out.printf("BMI: %.2f%n", bmi);
        if (bmi < 18.5) System.out.println("Недостаточный вес");
        else if (bmi < 25) System.out.println("Норма");
        else if (bmi < 30) System.out.println("Избыточный вес");
        else System.out.println("Ожирение");
    }

    // Два варианта обменника используют одну формулу.
    static void exchange(Scanner input, boolean withEndings) {
        final double ROUBLES_PER_YUAN = 11.91;
        System.out.print("Введите целое количество юаней: ");
        int yuan = input.nextInt();
        if (yuan < 0) {
            System.out.println("Сумма не может быть отрицательной.");
            return;
        }
        double roubles = ROUBLES_PER_YUAN * yuan;
        String word = "CNY";
        if (withEndings) {
            int digit = yuan % 10;
            int lastTwo = yuan % 100;
            if (lastTwo >= 11 && lastTwo <= 14) word = "китайских юаней";
            else if (digit == 1) word = "китайский юань";
            else if (digit >= 2 && digit <= 4) word = "китайских юаня";
            else word = "китайских юаней";
        }
        System.out.printf("%d %s = %.2f руб.%n", yuan, word, roubles);
    }

    static void cars() {
        Car first = new Car();
        Car second = new Car("Toyota Camry", "А123ВС77", "Чёрный", 2020);
        Car third = new Car("Lada Vesta", 2022);
        first.To_String();
        second.To_String();
        third.To_String();

        first.setModel("Kia Rio");
        first.setLicense("М456ОР77");
        first.setColor("Белый");
        first.setYear(2021);
        System.out.println("После изменения:");
        System.out.println(first.getModel() + ", " + first.getLicense()
                + ", " + first.getColor() + ", " + first.getYear());
        System.out.println("Возраст автомобиля: " + first.getAge());
    }
}

// Класс Car размещён в том же файле.
class Car {
    private String model;
    private String license;
    private String color;
    private int year;

    public Car() {
        this("Не указана", "Не указан", "Не указан", Year.now().getValue());
    }

    public Car(String model, String license, String color, int year) {
        this.model = model;
        this.license = license;
        this.color = color;
        this.year = year;
    }

    public Car(String model, int year) {
        this(model, "Не указан", "Не указан", year);
    }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getLicense() { return license; }
    public void setLicense(String license) { this.license = license; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getAge() {
        return Year.now().getValue() - year;
    }

    public void To_String() {
        System.out.println(model + ", " + license + ", " + color + ", " + year);
    }
}
