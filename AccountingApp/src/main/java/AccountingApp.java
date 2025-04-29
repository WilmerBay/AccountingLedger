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

            switch (choice) { //dont need break; with arrows - Default is an easier way to keep from user entering something wrong in a while loop to me to keep it user proof

                case "D" -> addTransaction(true);

                case "P" -> addTransaction(false);

                case "L" -> showLedger();

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

        if (!isDeposit) {
            amount = -Math.abs(amount);}

        Transaction printAll = new Transaction(LocalDate.now(), LocalTime.now(), desc, vendor, amount);
        saveTransaction(printAll);

        System.out.println("Saved: " + printAll);
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

        try { //Files.readAllLines read all file lines

            for (String line : Files.readAllLines(Paths.get(FILE))) {

                String[] parts = line.split("\\|");

                list.add(new Transaction(LocalDate.parse(parts[0]), LocalTime.parse(parts[1]), parts[2], parts[3], Double.parseDouble(parts[4])));
            }
        } catch (IOException e) {
            System.out.println("No existing transactions.");
        }
        return list;
    }

    private static void showLedger() {

        List<Transaction> all = loadTransactions();

        all.sort(Comparator.comparing(Transaction::getDate).thenComparing(Transaction::getTime).reversed());

        while (true) {

            System.out.println("\nLedger");

            System.out.println("A) All\nD) Deposits\nP) Payments\nR) Reports\nH) Home");

            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {

                case "A" -> printTransactions(all);

                case "D" -> printTransactions(all.stream().filter(t -> t.getAmount() > 0).collect(Collectors.toList()));

                case "P" -> printTransactions(all.stream().filter(t -> t.getAmount() < 0).collect(Collectors.toList()));

                //case "R" -> showReports(all);

                case "H" -> {
                    return; }

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void printTransactions(List<Transaction> list) {

        if (list.isEmpty()) {
            System.out.println("No transactions found.");

        } else {
            for (Transaction t : list) {
                System.out.println(t);
            }
        }
    }

    private static void showReports(List<Transaction> list) {

    }
    }



