import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

final class JsoupSiblingDivsParser {

    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("http://www.dges.gov.pt/guias/indcurso.asp?letra=A").get();

        Elements boxes = doc.select("div.box10");

        for (Element box : boxes) {
            String linAreaC1 = box.select(".lin-area-c1").text();
            String linAreaC2 = box.select(".lin-area-c2").text();
            String linAreaC3 = box.select(".lin-area-c3").text();

            System.out.printf("%s: %s %s%n", linAreaC1, linAreaC2, linAreaC3);

            Element linCurso = box.nextElementSibling();

            while (linCurso.hasClass("lin-curso")) {
                String linCursoC2 = linCurso.select(".lin-curso-c2").text();
                String linCursoC3 = linCurso.select(".lin-curso-c3").text();
                String linCursoC4 = linCurso.select(".lin-curso-c4").text();

                System.out.printf("%s\t%s\t%s%n", linCursoC2, linCursoC3, linCursoC4);

                linCurso = linCurso.nextElementSibling();
            }

            System.out.println("==============================");
        }
    }
}
