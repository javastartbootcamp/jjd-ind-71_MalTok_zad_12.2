import java.util.Objects;

class Employee {
    private String firstName;
    private String lastName;
    private final String pesel;
    private String department;
    private double salary;

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Employee(String firstName, String lastName, String pesel, String department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName)
                && Objects.equals(pesel, employee.pesel)
                && Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel, department, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
