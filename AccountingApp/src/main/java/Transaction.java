
import java.time.LocalDate;
import java.time.LocalTime;


public class Transaction {

    private final LocalDate date;
    private final LocalTime time;
    private final String description;
    private final String vendor;
    private final double amount;

    public Transaction(LocalDate date, LocalTime time, String description, String vendor, double amount) {

        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;}

    public LocalTime getTime() {
        return time; }

    public String getDescription() {
        return description; }

    public String getVendor() {
        return vendor; }

    public double getAmount() {
        return amount; }

    public String toCSV() {
        return date + "|" + time + "|" + description + "|" + vendor + "|" + amount; //originally sectioned off by commas
    }

    public String toString() {
        return date + " | " + time + " | " + description + " | " + vendor + " | " + amount;
    }
}