import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();

        task4();
    }

    public static void task1(){
        // №1 Массивы (Работа с парком машин)
        // Создайте массив, представляющий годы выпуска 50 случайных машин (от 2000 до 2025).
        int[] carList = new Random().ints(50,2000,2025).toArray();

        System.out.println("Все сгенерированные годы выпуска автомобилей:");
        System.out.println(Arrays.toString(carList));

        // Выведите только машины, выпущенные после 2015 года.
        for (int year: carList){
            if(year > 2015){
                System.out.print(year + " ");
            }
        }
        System.out.print("\n");

        // Посчитайте средний возраст авто.
        int totalAge = 0;
        for (int year : carList) {
            int age = 2026 - year;
            totalAge += age;
        }
        double averageAge = (double) totalAge / carList.length;

        System.out.printf("Средний возраст автомобилей: %.2f лет\n", averageAge);

    }
    public static void task2(){
        // №2 Коллекции (Управление моделями)
        //Создайте список с названиями моделей машин
        List<String> carsList = new ArrayList<>();
        carsList.add("Toyota Camry");
        carsList.add("BMW X5");
        carsList.add("Tesla Model S");
        carsList.add("Toyota Camry"); // дубликат
        carsList.add("Tesla Model 3");

        //Удалите дубликаты, затем отсортируйте модели в обратном алфавитном порядке, выведите на экран, затем сохраните в Set.
        Set<String> uniqCars = new HashSet<>(carsList);
        List<String> sortedCars = new ArrayList<>(uniqCars);
        Collections.sort(sortedCars, Collections.reverseOrder());

        Set<String> finalCarsSet = new LinkedHashSet<>();
        System.out.println("машинки в Set:");
        for (String car : sortedCars) {
            System.out.println(car);
            finalCarsSet.add(car);
        }

        // Реализуйте проверку: если модель содержит слово "Tesla", замените её на "ELECTRO_CAR".
        System.out.println("Замена Tesla на ELECTRO_CAR");
        sortedCars.replaceAll(car -> car.contains("Tesla") ? "ELECTRO_CAR" : car);
        for (String car : sortedCars) {
            System.out.println(car);
        }
    }
    public static void task3(){
        Car car1 = new Car("VIN-111", "Camry", "Toyota", 2020, 50000, 25000);
        Car car2 = new Car("VIN-222", "X5", "BMW", 2023, 15000, 60000);
        Car car3 = new Car("VIN-333", "A4", "Audi", 2018, 90000, 18000);
        Car car4 = new Car("VIN-111", "Corolla", "Toyota", 2015, 100000, 10000);

        HashSet<Car> carSet = new HashSet<>();
        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);
        boolean isAdded = carSet.add(car4);

        System.out.println("Удалось ли добавить машину с дубликатом VIN? " + isAdded);
        System.out.println("\nМашины в HashSet (уникальные по VIN)");
        for (Car car : carSet) {
            System.out.println(car);
        }
        List<Car> sortedCarList = new ArrayList<>(carSet);
        Collections.sort(sortedCarList);

        System.out.println("\nОтсортированные машины (от новых к старым)");
        for (Car car : sortedCarList) {
            System.out.println(car);
        }

    }
    public static void task4(){
        //№4 Stream API (Анализ автопарка)
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("VIN-111", "Camry", "Toyota", 2020, 50000, 25000));
        carList.add(new Car("VIN-222", "X5", "BMW", 2023, 15000, 60000));
        carList.add(new Car("VIN-333", "A4", "Audi", 2018, 90000, 18000));
        carList.add(new Car("VIN-433", "A5", "Audi", 2020, 50001, 18000));
        carList.add(new Car("VIN-122", "Corolla", "Toyota", 2015, 100000, 50000));

        List<Car> top3ExpensiveCars = carList.stream()
                .filter(car -> car.getMileage() >= 50000)
                .sorted((c1, c2) -> Integer.compare(c2.getPrice(), c1.getPrice()))
                .limit(3)
                .collect(Collectors.toList());
        top3ExpensiveCars.forEach(System.out::println);

        System.out.print("\nСредний пробег всех машин ");
        double averageMileage = carList.stream()
                .mapToInt(car -> car.getMileage())
                .average()
                .orElse(0.0);
        System.out.println(averageMileage);


        System.out.println("\nГруппировка по производителю:");
        Map<String, List<Car>> carsByManufacturer = carList.stream()
                .collect(Collectors.groupingBy(car -> car.getManufacturer()));

        carsByManufacturer.forEach((manufacturer, cars) -> {
            System.out.println("Производитель: " + manufacturer);
            cars.forEach(car -> System.out.println("  - " + car));
        });

    }
}