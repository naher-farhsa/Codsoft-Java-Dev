import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class CurrencyConverterApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello User: ");

        String[] currency = {"SGD", "MYR", "EUR", "USD", "AUD", "JPY", "CNH", "HKD", "CAD", "INR", "DKK", "GBP", "RUB", "NZD", "MXN", "IDR", "TWD", "THB", "VND"};
        System.out.println("Please select from following currency: " + Arrays.toString(currency));

        System.out.print("Enter base currency: ");
        String baseCurrency = scanner.nextLine();
        validateCurrency(baseCurrency, currency);

        System.out.print("Enter target currency: ");
        String targetCurrency = scanner.nextLine();
        validateCurrency(targetCurrency, currency);

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        validateAmount(amount);

        double convertedAmount = convertCurrency(baseCurrency, targetCurrency, amount);


        System.out.printf("%f %s = %f %s\n", amount, baseCurrency.toUpperCase(), convertedAmount, targetCurrency.toUpperCase());
        System.out.println("⭐⭐Thank You ⭐⭐");
    }

    // Method to perform currency conversion
    private static double convertCurrency(String baseCurrency, String targetCurrency, double amount) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://currency-exchange.p.rapidapi.com/exchange?from=" + baseCurrency + "&to=" + targetCurrency + "&q=" + amount))
                    .header("X-RapidAPI-Key", "e9f830996bmsh1f1da821e7faa45p1c1f5bjsn3e67a7482f3a")
                    .header("X-RapidAPI-Host", "currency-exchange.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            double conversionRate = Double.parseDouble(response.body());

            return amount * conversionRate;
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }


    private static void validateCurrency(String currency, String[] validCurrencies) {
        String lowerCaseCurrency = currency.toLowerCase();
        boolean isValidCurrency = false;

        for (String validCurrency : validCurrencies) {
            if (validCurrency.equalsIgnoreCase(lowerCaseCurrency)) {
                isValidCurrency = true;
                break;
            }
        }

        if (!isValidCurrency) {
            throw new IllegalArgumentException("Invalid currency selected: " + currency);
        }
    }
    private static void validateAmount(double amount) {
        boolean isValidAmount=false;
          if (amount>=0){
              isValidAmount=true;
          }
          else{
              throw new IllegalArgumentException("Invalid amount entered:"+ amount);
          }
    }
}
