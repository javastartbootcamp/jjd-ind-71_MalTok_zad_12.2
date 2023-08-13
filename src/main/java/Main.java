import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFileName = "employees.csv";
        String outputFileName = "stats.txt";

        File file = new File(inputFileName);
        if (file.exists()) {
            File stats = new File(outputFileName);

//            stats.createNewFile();
//            FileWriter fileWriter = new FileWriter(stats);
//            fileWriter.write("Średnia wypłata: 5000");
//            fileWriter.close();

            int companySize = getCompanySize(inputFileName);
            Company company = new Company(companySize);
            try (
                    Scanner scanner = new Scanner(file);
                    var bufferedWriter = new BufferedWriter(new FileWriter(stats))
            ) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Employee employee = company.createEmployeeFromLine(line);
                    company.addEmployee(employee);
                }
                String outputFormat = company.getOutputFormat();
                System.out.println("Statystyki firmy:");
                System.out.println(outputFormat);

                bufferedWriter.write(outputFormat);
                System.out.println("Pomyślnie zapisano plik " + outputFileName);

            } catch (IOException e) {
                System.err.println("Błąd odczytu lub zapisu pliku " + inputFileName);
            } catch (DuplicateEmployeeException e) {
                System.err.println("Pracownik już istnieje");
            }
        }
    }

    private static int getCompanySize(String fileName) {
        int size = 0;
        try (
                Scanner scanner = new Scanner(new File(fileName))
        ) {
            while (scanner.hasNextLine()) {
                scanner.nextLine();
                size++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku " + fileName);
        }
        return size;
    }
}


