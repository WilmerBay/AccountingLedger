import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

public class AccountingApp {

    private static final String FILE = "transactions.csv";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {


            System.out.println("\n Home Screen ");
            System.out.println("D) Add Deposit\nP) Make Payment\nL) Ledger\nX) Exit");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {

                case "D" -> addTransaction(true);

                case "P" -> addTransaction(false);

                case "L" -> System.out.println("showLedger();");

                case "X" -> {
                    System.out.println("Exiting.");
                    return;
                }
                default -> System.out.println("Invalid input.");
            }
        }
    }

    private static void addTransaction(boolean isDeposit) {

        System.out.print("Description: ");
        String desc = scanner.nextLine();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        if (!isDeposit) amount = -Math.abs(amount);

        Transaction t = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);
        saveTransaction(t);

        System.out.println("Saved: " + t);
    }

    private static void saveTransaction(Transaction t) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {

            writer.write(t.toCSV());

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    private static List<Transaction> loadTransactions() {

        List<Transaction> list = new ArrayList<>();

        try {
            for (String line : Files.readAllLines(Paths.get(FILE))) {

                String[] parts = line.split("\\|");

                list.add(new Transaction(LocalDate.parse(parts[0]), LocalTime.parse(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4])));
            }
        } catch (IOException e) {
            System.out.println("No existing transactions.");
        }
        return list;
    }


    }



