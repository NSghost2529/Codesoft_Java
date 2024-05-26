import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String EXCHANGE_RATE_API = "https://api.exchangerate-api.com/v4/latest/";
    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose the base currency (e.g. USD, EUR, JPY):");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.println("Choose the target currency (e.g. USD, EUR, JPY):");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.println("Enter the amount to convert:");
        double amount = scanner.nextDouble();

        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            double convertedAmount = convertCurrency(amount, exchangeRate);

            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching exchange rate: " + e.getMessage());
        }
    }

    private static double getExchangeRate(String baseCurrency, String targetCurrency) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
              .uri(URI.create(EXCHANGE_RATE_API + baseCurrency))
              .GET()
              .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();

        int startIndex = responseBody.indexOf(targetCurrency + "\"") + targetCurrency.length() + 2;
        int endIndex = responseBody.indexOf('"', startIndex);
        String exchangeRateStr = responseBody.substring(startIndex, endIndex);
        return Double.parseDouble(exchangeRateStr);
    }

    private static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}