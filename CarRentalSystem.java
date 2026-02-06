import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// --- 1. THE CAR CLASS ---
class Car {
    private String carId;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(String carId, String model, double pricePerDay) {
        this.carId = carId;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = true; // Cars are available by default
    }

    // Getters
    public String getCarId() { return carId; }
    public String getModel() { return model; }
    public double getPricePerDay() { return pricePerDay; }
    public boolean isAvailable() { return isAvailable; }

    // Setters for availability
    public void rent() { isAvailable = false; }
    public void returnCar() { isAvailable = true; }
}

// --- 2. THE CUSTOMER CLASS ---
class Customer {
    private String name;
    private String customerId;

    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    public String getName() { return name; }
    public String getCustomerId() { return customerId; }
}

// --- 3. THE RENTAL AGENCY (The Logic Hub) ---
class RentalAgency {
    private List<Car> cars = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public void addCar(Car car) { cars.add(car); }
    public void addCustomer(Customer customer) { customers.add(customer); }

    public void displayAvailableCars() {
        System.out.println("\n--- Available Cars ---");
        for (Car car : cars) {
            if (car.isAvailable()) {
                System.out.println(car.getCarId() + " : " + car.getModel() + " ($" + car.getPricePerDay() + "/day)");
            }
        }
    }

    public void rentCar(String carId, Customer customer, int days) {
        for (Car car : cars) {
            if (car.getCarId().equals(carId) && car.isAvailable()) {
                car.rent();
                double total = car.getPricePerDay() * days;
                System.out.println("\nSuccessfully Rented!");
                System.out.println("Customer: " + customer.getName());
                System.out.println("Car: " + car.getModel());
                System.out.println("Total Cost: $" + total);
                return;
            }
        }
        System.out.println("Car not available or ID incorrect.");
    }
}

// --- 4. MAIN CLASS (Test Suite & UI) ---
public class CarRentalSystem {
    public static void main(String[] args) {
        RentalAgency agency = new RentalAgency();

        // Adding sample data (Testing addCar)
        agency.addCar(new Car("C001", "Toyota Corolla", 50.0));
        agency.addCar(new Car("C002", "Honda Civic", 55.0));
        agency.addCar(new Car("C003", "Ford Mustang", 120.0));

        // Creating a test customer
        Customer user = new Customer("Alex Smith", "ID101");
        agency.addCustomer(user);

        // Simple Menu Interface
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Car Rental System!");

        while (true) {
            agency.displayAvailableCars();
            System.out.print("\nEnter Car ID to rent (or 'exit' to quit): ");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) break;

            System.out.print("Enter number of days: ");
            int days = Integer.parseInt(scanner.nextLine());

            agency.rentCar(choice, user, days);
            
            System.out.println("\nUpdated list:");
        }
        
        System.out.println("Thank you for using our service!");
        scanner.close();
    }
}