import java.util.Scanner;

// change the compareto manually
// test the code
// comments
/*
    2
    saif
    123
    12345678
    5
    2
    ABC123
    4
    exit

    Mohammad
    456
    87654321
    4
    3
    DEF456
    3
    exit


 */

public class Driver {
    static Scanner scan = new Scanner(System.in);

    public static Customer readCustomer(Customer[] customers) { /* this function is used to read a customer information */
        System.out.print("Enter name : "); // name of the customer
        scan.nextLine();
        String tempName = scan.nextLine();
        System.out.print("\n");

        System.out.print("Enter ID : "); // id of the customer
        int tempID = scan.nextInt();
        // checking validity for the id number
        boolean notFound;
        do{
            notFound = true;
            for (Customer customer : customers) {
                if (customer != null && customer.getId() == tempID) {
                    System.out.println("This ID is already in the system, enter another one.");
                    notFound = false;
                    System.out.print("Enter valid ID : ");
                    tempID = scan.nextInt();
                }
            }
        }while (!notFound);

        System.out.print("\n");
        scan.nextLine();
        System.out.print("Enter license number : ");// license number for the customer
        String tempLicenseNumber = scan.nextLine();
        // check validity for license number
        do{
            notFound = true;
            for (Customer customer : customers) {
                if (customer != null && customer.getLicenseNumber().equals(tempLicenseNumber)) {
                    System.out.println("This License Number is already in the system, enter another one.");
                    notFound = false;
                    System.out.print("Enter valid  License Number : ");
                    tempLicenseNumber = scan.nextLine();
                }
            }
        }while (!notFound);

        System.out.print("\n");

        System.out.print("Enter Maximum number of vehicles can rent by this customer: "); // maximum number of vehicles this customer can rent
        int tempMaxVehiclesCanBeRented = scan.nextInt();
        System.out.print("\n");


        return new Customer(tempName, tempID, tempLicenseNumber, tempMaxVehiclesCanBeRented); // creating a customer object and returning it to the primary customers array

    }

    public static Customer findCustomerById(Customer[] customers, int customerId) {
        /*
            This method searches through an array of Customer objects to find a customer
            by their ID. If found, it returns the Customer object; otherwise, it returns null.
        */
        for (Customer customer : customers) {
            if (customer.getId() == customerId) return customer;
        }
        return null;

    }

    public static Vehicle findVehicleByRegistrationNumber(Vehicle[] vehicles, String regNumber) {
        for (Vehicle vehicle : vehicles) { //   This method searches through an array of Vehicle objects to find a vehicle by its registration number.
            if (vehicle.getRegistrationNumber().equals(regNumber)) // If found, it returns the Vehicle object;
                return vehicle;
        }
        return null;    // otherwise, it returns null

    }


    public static void displayAvailableVehiclesForRental(Vehicle[] vehicles) {
        // This method displays all available vehicles for rental from the given array.
        System.out.println("Available vehicles for rental:");
        int count = 1; // Counter to display serial numbers for available vehicles.
        for (Vehicle vehicle : vehicles) {
            System.out.print(count + ". ");
            if (vehicle.isAvailable()) { // Check if the vehicle is available for rental.
                vehicle.printInfo(); // Print vehicle details using the printInfo method of the Vehicle class.
                count++; // Increment the counter for the next available vehicle.

            }

        }

    }


    // menu functions

    public static void choice1(Customer[] customers) {
        // This method prompts the user to enter a customer ID and displays the details of the corresponding customer.
        System.out.print("Enter customer ID: ");
        int ID = scan.nextInt();// Read the customer ID.
        Customer customer = findCustomerById(customers, ID);// Find the customer by ID.
        if (customer == null) { // Check if the customer is not found.
            System.out.println("Customer not found.\n");
            return;
        }
        customer.printInfo();// Print the customer's information.
        System.out.println(" ");// an empty line for formatting.

    }

    public static void choice2(Customer[] customers) {
        // This method calculates and displays the total rental cost for a customer.
        System.out.print("Enter customer ID: ");
        int ID = scan.nextInt();// Read the customer ID.
        Customer customer = findCustomerById(customers, ID);// Find the customer by ID.
        if (customer == null) {// Check if the customer is not found.
            System.out.println("Customer not found.\n");
            return;
        }
        // Calculate and display the total rental cost for the customer.
        System.out.println("Total Rental Cost for " + customer.getName() + ": $" + customer.calculateRent());
        System.out.println(" ");//an empty line for formatting.
    }

    public static void choice3(Customer[] customers) {
        // This method displays the count of rented vehicles of a specified type for a specific customer.

        System.out.print("Enter customer ID: ");
        int ID = scan.nextInt(); // Read the customer ID.
        Customer customer = findCustomerById(customers, ID);// Find the customer by ID.
        if (customer == null) {// Check if the customer is not found.
            System.out.println("Customer not found.\n");
            return;
        }
        System.out.print("Enter vehicle type to count: (e.g., Car, Bike): ");
        String type = scan.next();// Read the vehicle type.
        // Display the count of vehicles of the specified type rented by the customer.
        System.out.println("Number of " + type + "(s) rented by John Doe: " + customer.countVehiclesByType(type));

        System.out.println(" ");//an empty line for formatting.
    }

    public static void choice4(Customer[] customers, Vehicle[] availableVehicles) {
        // This method allows a customer to rent a new vehicle.
        System.out.print("Enter customer ID: ");
        int ID = scan.nextInt();// Read the customer ID.
        Customer customer = findCustomerById(customers, ID);// Find the customer by ID.
        if (customer == null) {// Check if the customer is not found.
            System.out.println("Customer not found.\n");
            return;
        }
        // Check if the customer has reached the maximum number of rented vehicles.
        if (customer.getNumberOfCurrentRented() >= customer.getVehiclesRented().length) {
            System.out.println("Maximum number of rented vehicles has been reached for this customer");
            return;
        }
        System.out.print("Enter vehicle type to rent: (e.g., Car, Bike): ");
        String type = scan.next(); // Read the vehicle type.
        int count = 1;// Counter to display available vehicles of the entered type.
        System.out.println("Available vehicles of type '" + type + "'");
        for (Vehicle vehicle : availableVehicles) { // display the available vehicles of the type entered
            if (vehicle.getType().equals(type) && vehicle.isAvailable()) {
                System.out.print(count++ + ". ");
                vehicle.printInfo();// Print the vehicle details.
                System.out.println(" ");// Print a newline for formatting.
            }
        }
        Vehicle vehicle = null;
        boolean flag = true; //Flag to track whether a valid vehicle is rented.
        do {
            System.out.print("Enter registration number of vehicle to rent: ");
            String tempVehicleRegNum = scan.next();// Read the registration number.
            System.out.println(" ");
            // Allow the customer to exit the rental process.
            if (tempVehicleRegNum.equalsIgnoreCase("exit")) {
                System.out.println("Rental process ended by customer...");
                flag = false; // Set the flag to false, indicating no vehicle was rented.
                break;
            }
            vehicle = findVehicleByRegistrationNumber(availableVehicles, tempVehicleRegNum);// Find the vehicle by registration number.
            // Check if the entered registration number is valid and the vehicle is available.
            if (vehicle == null)
                System.out.println("Vehicle not found. Please enter a valid registration number.");
            else if (!vehicle.isAvailable() || !vehicle.getType().equals(type))
                System.out.println("Vehicle not available. Please choose another.");

        } while (vehicle == null || !vehicle.isAvailable() || !vehicle.getType().equals(type));
        // If a valid vehicle is found, proceed with the rental process.
        if (flag) {
            System.out.print("Enter rental days: ");
            int rentalDays = scan.nextInt(); // Read the rental days
            System.out.print("\n");
            customer.rentVehicle(vehicle, rentalDays);// Rent the vehicle to the customer.
        }

    }

    public static void choice5(Customer[] customers, Vehicle[] availableVehicles) {
        // This method allows a customer to return a rented vehicle.
        System.out.print("Enter customer ID: ");
        int ID = scan.nextInt();// Read the customer ID.
        Customer customer = findCustomerById(customers, ID);
        if (customer == null) {// Check if the customer is not found.
            System.out.println("Customer not found.\n");
            return;
        }
        // Display all vehicles currently rented by the customer.
        System.out.println("List of vehicles currently rented:");
        for (Vehicle vehicle : customer.getVehiclesRented()) {
            if (vehicle == null) continue;
            ; // Skip null entries.
            System.out.print("  - ");
            vehicle.printInfo(); // Print the vehicle details.
        }
        System.out.print("Enter registration number of vehicle to return: ");
        String tempVehicleRegNum = scan.next(); // Read the registration number.
        // Allow the customer to exit the return process.
        if (tempVehicleRegNum.equals("exit")) {
            System.out.println("Exiting the return process by Customer.");
            return;
        }
        // Find the vehicle in the available vehicles array.
        Vehicle tempVehicle = null;
        for (Vehicle vehicle : availableVehicles) {
            if (vehicle.getRegistrationNumber().equals(tempVehicleRegNum)) {
                tempVehicle = vehicle; // Set the found vehicle.
                break;
            }
        }

        customer.returnVehicle(tempVehicle);// Process the vehicle return for the customer.
    }


    public static void displayVehiclesByPrice(Vehicle[] vehicles) {
     /*   This method displays an array of Vehicle objects in ascending order of
        rentalRatePerDay.I have used bubble sort algorithm.*/
        Vehicle[] tempArray = new Vehicle[vehicles.length]; // copying the originalArray
        for (int i = 0; i < vehicles.length; i++) tempArray[i] = vehicles[i];
        Vehicle temp;
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray.length; j++) {
                if (tempArray[i].getRentalPerDay() < tempArray[j].getRentalPerDay()) {
                    temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }

            }
        }
        int count = 1;
        for (Vehicle vehicle : tempArray) {
            if (vehicle != null && vehicle.isAvailable()) {
                System.out.print(count++ + " - ");
                vehicle.printInfo();

            }

        }

    }

    public static void displayVehiclesByType(Vehicle[] vehicles) {
    /*This method displays an array of Vehicle objects in alphabetical order of type.
    I have used bubble sort algorithm. */

        Vehicle[] tempArray = new Vehicle[vehicles.length]; // copying the originalArray
        for (int i = 0; i < vehicles.length; i++) tempArray[i] = vehicles[i];
        Vehicle temp;
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray.length; j++) {
                if (tempArray[i].getType().compareTo(tempArray[j].getType()) < 0) {
                    temp = tempArray[i];
                    tempArray[i] = tempArray[j];
                    tempArray[j] = temp;
                }

            }
        }
        int count = 1;
        for (Vehicle vehicle : tempArray) {
            if (vehicle != null && vehicle.isAvailable()) {
                System.out.print(count++ + " - ");
                vehicle.printInfo();

            }

        }

    }

    public static void main(String[] args) {

        Vehicle[] availableVehicles = { // predefined array of available vehicles
                new Vehicle("Car", "ABC123", "Toyota", 50.0, true),
                new Vehicle("Bike", "DEF456", "Honda", 20.0, true),
                new Vehicle("Truck", "GHI789", "Ford", 80.0, true),
                new Vehicle("Car", "JKL012", "Hyundai", 55.0, true),
                new Vehicle("Bike", "MNO345", "Yamaha", 160.0, true)
        };

        System.out.print("Enter number of customers: ");
        int numberOfCustomers = scan.nextInt(); // prompt the user to enter the number of customers
        Customer[] customers = new Customer[numberOfCustomers]; // the primary array for customers in this program
        System.out.print("\n");

        for (int i = 1; i <= numberOfCustomers; i++) { // saving customers details in the primary customers array
            System.out.printf("Enter details for customer %d: \n", i);

            customers[i - 1] = readCustomer(customers); // this function will read all the customers needed information to create an object from class Customer
            System.out.print("Enter the number of vehicles to rent now: "); // how many vehicles this customer will rent now
            int numberOfCurrentRentedForTheCurrentCustomer = scan.nextInt();
            System.out.print("\n");

            while (numberOfCurrentRentedForTheCurrentCustomer >= customers[i - 1].getVehiclesRented().length) {
                System.out.println("This customer cant rent that number of vehicles, please enter a number that is less than the max number of vehicles he can rent.");
                numberOfCurrentRentedForTheCurrentCustomer = scan.nextInt();
            }


            displayAvailableVehiclesForRental(availableVehicles); // displaying all available vehicles (not rented ones)

            for (int j = 1; j <= numberOfCurrentRentedForTheCurrentCustomer; j++) {
                // this loop will save all the required information about the vehicles customer wishes to rent
                Vehicle vehicle = null;
                boolean flag = true; // this flag to make sure we found a valid vehicle to rent
                do {
                    System.out.print("Enter registration number of vehicle " + j + " to rent: "); // asking the user to enter the reg number for the vehicle
                    String tempVehicleRegNum = scan.next();
                    System.out.println(" ");
                    if (tempVehicleRegNum.equalsIgnoreCase("exit")) { // if the user entered "exit" this will break out of the rental loop, ending the process for the current customer
                        System.out.println("Rental process ended by customer...");
                        flag = false; // the operation did not end well
                        j = numberOfCurrentRentedForTheCurrentCustomer + 1; // we saved the value of j (the variable used for iterating for the wished number of vehicles to be rented to the maximum, so it will end the loop for the current customer

                        break; // break out this while loop and the for loop will end because of the j
                    }
                    vehicle = findVehicleByRegistrationNumber(availableVehicles, tempVehicleRegNum); /* if the user doesn't enter "exit" so he entered
                            a registration number now we will check if it is a valid one and if it's not already rented */

                    if (vehicle == null) // the vehicle registration number is not stored in our availableVehicles array, so we will ask the user to enter a valid one
                        System.out.println("Vehicle not found. Please enter a valid registration number.");
                    else if (!vehicle.isAvailable())// the vehicle was found but it is already rented
                        System.out.println("Vehicle not available. Please choose another.");

                } while (vehicle == null || !vehicle.isAvailable());// we will continue iterating through this loop till the user enter a valid reg number or enter exit

                if (flag) { // if flag = true this means we found a valid vehicle to rent
                    System.out.print("Enter rental days: "); // now we will ask the user how many days he wants to rent it
                    int rentalDays = scan.nextInt();
                    System.out.print("\n");
                    customers[i - 1].rentVehicle(vehicle, rentalDays); // rent the vehicle for the current customer
                }


            }
        }

        System.out.println("Welcome to the system");

        int choice;
        do {// we will continue showing this menu till the user enters 8
            System.out.println("Main Menu : Enter the choice of the following :");
            System.out.println("1: Print Customer Information");
            System.out.println("2: Display Total Rental Cost for a Customer");
            System.out.println("3: Count Rented Vehicles by Type");
            System.out.println("4: Rent a New Vehicle");
            System.out.println("5: Return a Vehicle");
            System.out.println("6: Display All Available Vehicles in Ascending Order of Price");
            System.out.println("7: Display All Available Vehicles in alphabetical Order of Type");
            System.out.println("8: Exit");

            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    //Prompt for a customer ID and display all  the customer’s details.
                    choice1(customers);
                    break;
                case 2:
                    /*Prompt for a customer ID and the number of rental days, then calculate
                    and display the total rental cost.             */
                    choice2(customers);
                    break;
                case 3:
                    choice3(customers);
                    /*Prompt for a customer ID and vehicle type, then display the count of that
                    vehicle type for the customer.           */
                    break;
                case 4:
                    // rent a new vehicle
                    choice4(customers, availableVehicles);
                    break;
                case 5:
                    // return a vehicle
                    choice5(customers, availableVehicles);
                    break;
                case 6:
                    // display the available vehicles based on their rentalPerDay price
                    displayVehiclesByPrice(availableVehicles);
                    break;
                case 7:
                    // display the available vehicles based on their alphabetical Order of Type
                    displayVehiclesByType(availableVehicles);
                    break;
                case 8:
                    System.out.println("Goodbye !");
                    break;
            }

        } while (choice != 8);

    }

}
