public class Customer {
    private String name;
    private int id;
    private String licenseNumber;
    private int numberOfCurrentRented;
    private Vehicle[] vehiclesRented;

    public Customer() {

    }

    public Customer(String name, int id, String licenseNumber, int maxVehicles) {
        this.name = name;
        this.id = id;
        this.licenseNumber = licenseNumber;
        this.vehiclesRented = new Vehicle[maxVehicles];
        this.numberOfCurrentRented = 0; // we can delete this line because its zero by default (primitive data type)
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public int getNumberOfCurrentRented() {
        return numberOfCurrentRented;
    }

    public void setNumberOfCurrentRented(int numberOfCurrentRented) {
        this.numberOfCurrentRented = numberOfCurrentRented;
    }

    public Vehicle[] getVehiclesRented() {
        return vehiclesRented;
    }

    public void setVehiclesRented(Vehicle[] vehiclesRented) {
        this.vehiclesRented = vehiclesRented;
    }

    public void rentVehicle(Vehicle vehicle, int days) {
        if (numberOfCurrentRented >= vehiclesRented.length) {
            System.out.println("This user is already renting the maximum number of vehicles he can");
            return;
        }

        for (int i = 0; i < vehiclesRented.length; i++) {
            if (vehiclesRented[i] == null) {
                vehiclesRented[i] = vehicle;
                numberOfCurrentRented++;
                vehicle.setRentDays(days);
                vehicle.setAvailable(false);
                break;
            }

        }
        System.out.println("Vehicle rented successfully.\n");


    }

    public void returnVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            System.out.println("Vehicle not found");
            return;
        }

        boolean flag = false;
        for (Vehicle vehicle1 : vehiclesRented) {
            if (vehicle == vehicle1) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            System.out.println("Vehicle not found.");
            return;
        }

        for (int i = 0; i < vehiclesRented.length; i++) {
            if (vehiclesRented[i] == vehicle) {
                vehiclesRented[i] = null;
            }
        }
        vehicle.setAvailable(true);
        vehicle.setRentDays(0);
        this.numberOfCurrentRented -= 1;

        System.out.println("Vehicle " + vehicle.getRegistrationNumber() + " has been returned successfully.");
    }

    public double calculateRent() {
        double sum = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null)
                sum += vehicle.getRentDays() * vehicle.getRentalPerDay();
        }
        return sum;
    }


    public double calculateRent(String type) {
        double sum = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null && vehicle.getType().equals(type))
                sum += vehicle.getRentDays() * vehicle.getRentalPerDay();
        }
        return sum;
    }

    public int countVehiclesByType(String type) {
        int count = 0;
        for (Vehicle vehicle : vehiclesRented) {
            if (vehicle != null && vehicle.getType().equals(type))
                count++;

        }
        return count;
    }

    public void printInfo() {
        System.out.println("Customer Information");
        System.out.println("Name: " + this.name);
        System.out.println("ID: " + this.id);
        System.out.println("License Number: " + this.licenseNumber);
        System.out.println("Vehicles Rented: ");
        for (Vehicle vehicle : this.vehiclesRented) {
            if (vehicle == null) continue;
            System.out.println("    -Vehicle Code: " + vehicle.getType().toUpperCase() + vehicle.getRegistrationNumber() + ", Type: " + vehicle.getType()
                    + ", Brand: " + vehicle.getBrand() + ", Daily Rate: $" + vehicle.getRentalPerDay());

        }
        System.out.println(" ");

    }

}
