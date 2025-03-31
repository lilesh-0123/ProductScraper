import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class ProductScraper {

    public static void main(String[] args) {
        String url = "YOUR_E_COMMERCE_WEBSITE_URL"; // Replace with the actual URL
        String csvFilePath = "products.csv";

        try {
            Document doc = Jsoup.connect(url).get();

            // Replace these selectors with the actual CSS selectors for your target website
            Elements productElements = doc.select("YOUR_PRODUCT_ELEMENT_SELECTOR"); 

            FileWriter csvWriter = new FileWriter(csvFilePath);
            csvWriter.append("Name,Price,Rating\n"); // CSV header

            for (Element productElement : productElements) {
                String name = productElement.select("YOUR_NAME_SELECTOR").text();
                String price = productElement.select("YOUR_PRICE_SELECTOR").text();
                String rating = productElement.select("YOUR_RATING_SELECTOR").text();

                // Clean up the data (remove extra characters, etc.)
                price = price.replaceAll("[^\\d.]", ""); // Remove non-numeric characters from price

                csvWriter.append(name).append(",").append(price).append(",").append(rating).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Data extracted and saved to " + csvFilePath);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}