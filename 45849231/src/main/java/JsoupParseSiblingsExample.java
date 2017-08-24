import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.util.Set;
import java.util.stream.Collectors;

final class JsoupParseSiblingsExample {

    public static void main(String[] args) {
        String html = "<p>\n" +
                "&nbsp; <w:Sdt DropDown=\"t\" id=\"-537820932\">\n" +
                "        <w:ListItem ListValue=\"SELECT ONE\" DataValue=\"SELECT ONE\"/>\n" +
                "        <w:ListItem ListValue=\"test1\" DataValue=\"test1\"/>\n" +
                "        <w:ListItem ListValue=\"test2\" DataValue=\"test2\"/>Here is a Value1\n" +
                "        <w:ListItem ListValue=\"test3\" DataValue=\"test3\"/>\n" +
                "        <w:ListItem ListValue=\"test4\" DataValue=\"test4\"/>Test\n" +
                "        </w:Sdt>\n" +
                "    <o:p/>\n" +
                "</p>";

        Document doc = Jsoup.parse(html);

        Set<String> texts = doc.select("p > w|Sdt > w|ListItem")
                .stream()
                .map(Node::nextSibling)
                .map(Node::toString)
                .map(String::trim)
                .filter(el -> !el.isEmpty())
                .collect(Collectors.toSet());

        System.out.println(texts);
    }
}
