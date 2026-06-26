import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        task1();
        task2();
        task3();
        task4();
    }

    public static void task1() {
        System.out.println("Задание 1: Массивы\n");
        int[] years = new Random().ints(50, 2000, 2026).toArray();

        System.out.println("Все годы выпуска:");
        System.out.println(Arrays.toString(years));

        System.out.println("\nМашины после 2015 года:");
        for (int year : years) {
            if (year > 2015) {
                System.out.print(year + " ");
            }
        }
        System.out.println();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int totalAge = 0;
        for (int year : years) {
            totalAge += currentYear - year;
        }
        double averageAge = (double) totalAge / years.length;
        System.out.printf("Средний возраст автомобилей: %.2f лет%n%n", averageAge);
    }

    public static void task2() {
        System.out.println("Задание 2: Коллекции\n");

        List<String> models = new ArrayList<>(List.of(
                "Toyota Camry",
                "BMW X5",
                "Tesla Model S",
                "Toyota Camry",   // дубликат
                "Tesla Model 3",
                "Audi A4"
        ));

        System.out.println("Исходный список: " + models);

        List<String> uniqueSorted = models.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("\nУникальные модели (обратный порядок):");
        uniqueSorted.forEach(System.out::println);

        Set<String> carsSet = new LinkedHashSet<>(uniqueSorted);

        Set<String> replacedSet = new LinkedHashSet<>();
        for (String car : carsSet) {
            replacedSet.add(car.contains("Tesla") ? "ELECTRO_CAR" : car);
        }

        System.out.println("\nПосле замены Tesla на ELECTRO_CAR:");
        replacedSet.forEach(System.out::println);
        System.out.println();
    }

    public static void task3() {
        System.out.println("Задание 3: Equals/hashCode\n");

        Car car1 = new Car("VIN-111", "Camry", "Toyota", 2020, 50000, 25000);
        Car car2 = new Car("VIN-222", "X5", "BMW", 2023, 15000, 60000);
        Car car3 = new Car("VIN-333", "A4", "Audi", 2018, 90000, 18000);
        Car car4 = new Car("VIN-111", "Corolla", "Toyota", 2015, 100000, 10000); // дубликат VIN

        HashSet<Car> carSet = new HashSet<>();
        System.out.println("Добавлена car1 (VIN-111): " + carSet.add(car1));
        System.out.println("Добавлена car2 (VIN-222): " + carSet.add(car2));
        System.out.println("Добавлена car3 (VIN-333): " + carSet.add(car3));
        System.out.println("Добавлена car4 (VIN-111, дубликат): " + carSet.add(car4));

        System.out.println("\nМашины в HashSet (уникальные по VIN):");
        carSet.forEach(System.out::println);

        List<Car> sortedCars = new ArrayList<>(carSet);
        Collections.sort(sortedCars);

        System.out.println("\nОтсортированные машины (от новых к старым):");
        sortedCars.forEach(System.out::println);
        System.out.println();
    }

    public static void task4() {
        System.out.println("Задание 4: Stream API\n");

        List<Car> carList = List.of(
                new Car("VIN-111", "Camry", "Toyota", 2020, 30000, 25000),
                new Car("VIN-222", "X5", "BMW", 2023, 15000, 60000),
                new Car("VIN-333", "A4", "Audi", 2018, 90000, 18000),
                new Car("VIN-444", "Model S", "Tesla", 2022, 10000, 55000),
                new Car("VIN-555", "Corolla", "Toyota", 2021, 45000, 20000),
                new Car("VIN-666", "A6", "Audi", 2019, 70000, 35000)
        );

        List<Car> lowMileageCars = carList.stream()
                .filter(car -> car.getMileage() < 50000)
                .collect(Collectors.toList());

        System.out.println("Машины с пробегом < 50 000 км:");
        lowMileageCars.forEach(System.out::println);

        List<Car> top3 = carList.stream()
                .sorted(Comparator.comparingInt(Car::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\nТоп-3 самые дорогие машины:");
        top3.forEach(System.out::println);

        double averageMileage = carList.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0.0);
        System.out.printf("%nСредний пробег всех машин: %.2f км%n", averageMileage);

        Map<String, List<Car>> byManufacturer = carList.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));

        System.out.println("\nГруппировка по производителю:");
        byManufacturer.forEach((manufacturer, cars) -> {
            System.out.println(manufacturer + ":");
            cars.forEach(car -> System.out.println("  - " + car));
        });
    }
}