    import org.jsoup.Jsoup;
    import org.jsoup.nodes.Document;
    import org.jsoup.nodes.Element;
    import org.jsoup.select.Elements;

    import java.io.IOException;
    import java.net.URI;
    import java.net.URL;

    final class JsoupTableReadExample {

        public static void main(String[] args) throws IOException {
            final URL url = URI.create("http://www.hmdb.ca/metabolites/HMDB01448").toURL();
            final Document doc = Jsoup.parse(url, 4000);

            final Elements rows = doc.select("table.content-table > tbody > tr:has(td)");

            for (Element row : rows) {
                String name = row.select("th").first().text();
                String value = row.select("td").first().html();

                System.out.println(name + " = " + value);
            }
        }
    }
