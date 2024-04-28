import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter");

        // Currency selection
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter target currency (e.g., EUR): ");
        String targetCurrency = scanner.nextLine().toUpperCase();

        // Amount input
        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        // Fetch exchange rate
        double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

        // Currency conversion
        double convertedAmount = amount * exchangeRate;

        // Display result
        System.out.println("Converted amount: " + convertedAmount + " " + targetCurrency);
        
        scanner.close();
    }

    // Method to fetch exchange rate (dummy implementation)
    private static double fetchExchangeRate(String baseCurrency, String targetCurrency) {
        // In a real implementation, you would fetch exchange rates from a reliable API
        // For this example, let's assume a static exchange rate
        // In real-world scenario, consider using a currency exchange rate API
        if (baseCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            return 0.85; // 1 USD = 0.85 EUR (dummy value)
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            return 1.18; // 1 EUR = 1.18 USD (dummy value)
        } else {
            System.out.println("Exchange rate not available for the specified currencies.");
            System.exit(1);
            return 0; // Dummy return to satisfy compiler
        }
    }
}
