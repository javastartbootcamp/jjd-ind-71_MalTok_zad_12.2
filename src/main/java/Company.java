public class Company {
    private static final String IT_DEPARTMENT = "IT";
    private static final String SUPPORT_DEPARTMENT = "Support";
    private static final String MANAGEMENT_DEPARTMENT = "Management";
    private final Employee[] employees;
    private int counter = 0;

    public Company(int size) {
        this.employees = new Employee[size];
    }

    public Employee createEmployeeFromLine(String line) {
        String[] arrayFromLine = line.split(";");
        String firstName = arrayFromLine[0];
        String lastName = arrayFromLine[1];
        String pesel = arrayFromLine[2];
        String department = arrayFromLine[3];
        double salary = Double.parseDouble(arrayFromLine[4]);
        return new Employee(firstName, lastName, pesel, department, salary);
    }

    public void addEmployee(Employee employee) {
        for (int i = 0; i < counter; i++) {
            if (employees[i].equals(employee)) {
                throw new DuplicateEmployeeException("Employee already exists");
            }
        }
        employees[counter] = employee;
        counter++;
    }

    public double averageSalary() {
        return sumSalaries() / employees.length;
    }

    private double sumSalaries() {
        double sumSalaries = 0;
        for (int i = 0; i < counter; i++) {
            sumSalaries += employees[i].getSalary();
        }
        return sumSalaries;
    }

    public double lowestSalary() {
        double lowestSalary = employees[0].getSalary();
        for (int i = 0; i < counter; i++) {
            if (employees[i].getSalary() < lowestSalary) {
                lowestSalary = employees[i].getSalary();
            }
        }
        return lowestSalary;
    }

    public double highestSalary() {
        double highestSalary = employees[0].getSalary();
        for (int i = 0; i < counter; i++) {
            if (employees[i].getSalary() > highestSalary) {
                highestSalary = employees[i].getSalary();
            }
        }
        return highestSalary;
    }

    public int countEmployeesInDepartment(String department) {
        int result = 0;
        for (int i = 0; i < counter; i++) {
            if (employees[i].getDepartment().equals(department)) {
                result++;
            }
        }
        return result;
    }

    public String getOutputFormat() {
        return String.format("Średnia wypłata: %.1f\n", averageSalary()) +
                String.format("Minimalna wypłata: %.1f\n", lowestSalary()) +
                String.format("Maksymalna wypłata: %.1f\n", highestSalary()) +
                String.format("Liczba pracowników %s: %d\n", IT_DEPARTMENT, countEmployeesInDepartment(IT_DEPARTMENT)) +
                String.format("Liczba pracowników %s: %d\n", SUPPORT_DEPARTMENT, countEmployeesInDepartment(SUPPORT_DEPARTMENT)) +
                String.format("Liczba pracowników %s: %d\n", MANAGEMENT_DEPARTMENT, countEmployeesInDepartment(MANAGEMENT_DEPARTMENT));

    }
}
