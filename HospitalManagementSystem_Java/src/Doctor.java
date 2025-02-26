public class Doctor implements Comparable<Doctor> {
    private String doctorId;
    private String name;
    private String specialty;
    private String degree;
    private double baseSalary;
    private double overtimeHours;
    private double overtimeRate;
    private Department department;


    public Doctor() {
    }

    public Doctor(String doctorId, String name, String specialty, String degree, double baseSalary,
                  double overtimeHours, double overtimeRate, Department department) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialty = specialty;
        this.degree = degree;
        this.baseSalary = baseSalary;
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        department.addDoctor(this);
    }


    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(double overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    public double getOvertimeRate() {
        return overtimeRate;
    }

    public void setOvertimeRate(double overtimeRate) {
        this.overtimeRate = overtimeRate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double calculateSalary() {
        if (this.degree.equalsIgnoreCase("Bachelor")) {
            return 1.1 * this.baseSalary + this.calculateOverTimePay(); //  Base salary + 10% of base salary + overtimePay
        } else if (this.degree.equalsIgnoreCase("Master")) {
            return 1.2 * this.baseSalary + this.calculateOverTimePay();//  Base salary + 20% of base salary + overtimePay
        } else if (this.degree.equalsIgnoreCase("PhD")) {
            return 1.3 * this.baseSalary + this.calculateOverTimePay();//  Base salary + 30% of base salary + overtimePay
        }
        // Default case :
        return 1*this.baseSalary + this.calculateOverTimePay();
    }

    public double calculateOverTimePay() {
        return overtimeHours * overtimeRate;
    }

    @Override
    public int compareTo(Doctor other) {
        if (this.calculateSalary() < other.calculateSalary()) return -1;
        else if (this.calculateSalary() > other.calculateSalary()) return 1;

        return 0;
    }


    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", degree='" + degree + '\'' +
                ", baseSalary=" + baseSalary +
                ", overtimeHours=" + overtimeHours +
                ", overtimeRate=" + overtimeRate +
                ", department=" + department +
                '}';
    }
}



