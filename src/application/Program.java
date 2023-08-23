package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter full file path: ");
        String path = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> employees = new ArrayList<>();

            String line = br.readLine();

            while (line != null) {
                String[] fields = line.split(",");
                employees.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));

                line = br.readLine();
            }

            System.out.print("Enter salary: ");
            Double salary = sc.nextDouble();

            List<String> emails = employees.stream()
                    .filter(x -> x.getSalary() > salary)
                    .map(x -> x.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            System.out.println("Email of people whose salary is more than " + String.format("%.2f", salary));
            emails.forEach(System.out::println);

            double sum = employees.stream()
                    .filter(x -> x.getName()
                    .charAt(0) == 'M')
                    .map(x -> x.getSalary())
                    .reduce(0.0, (x, y) -> x + y);
            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}
