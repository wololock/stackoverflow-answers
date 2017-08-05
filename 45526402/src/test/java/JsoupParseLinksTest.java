import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JsoupParseLinksTest {

    @Test
    public void testParsingLinksWithoutImagesInside() {
        //given:
        String html = "<a href=\"index.htm\" title=\"tutorialspoint\">\n" +
                "  <img alt=\"tutorialspoint\" src=\"/java/images/logo.png\">\n" +
                "</a>";

        //when:
        Document document = Jsoup.parse(html);
        Elements elements = document.select("a[href]:not(:has(img))");

        //then:
        assertThat(elements.size()).isEqualTo(0);
    }
}
