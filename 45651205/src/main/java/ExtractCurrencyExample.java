import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

final class ExtractCurrencyExample {

    public static void main(String[] args) throws IOException {
        final Document doc = Jsoup.connect("http://www.x-rates.com/table/?from=USD&amount=1").get();

        final Elements rows = doc.select("table.ratesTable > tbody > tr");

        for (Element row : rows) {
            final Elements tds = row.select("td");

            final String currency = tds.get(0).text();
            final Double rate1 = Double.valueOf(tds.get(1).text());
            final Double rate2 = Double.valueOf(tds.get(2).text());

            System.out.printf("Currency: %s, rate1: %s, rate2: %s%n", currency, rate1, rate2);
        }
    }
}
