import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

final class Example {

    public static void main(String[] args) {
        String html = "<p class=\"MsoNormal\" style=\"margin-bottom:0in;margin-bottom:.0001pt;line-height:normal\">\n" +
                "<w:Sdt CheckBox=\"t\" CheckBoxIsChecked=\"t\" >\n" +
                "    <span style=\"font-family:&quot;MS Gothic&quot;\">y</span>\n" +
                "</w:Sdt> I Need this value since CheckBoxIsChecked is true \n" +
                "<w:Sdt CheckBox=\"t\" CheckBoxIsChecked=\"f\" >\n" +
                "    <span style=\"font-family:&quot;MS Gothic&quot;\">n</span>\n" +
                "</w:Sdt> This is not needed since CheckBoxIsChecked is false \n" +
                "<w:Sdt CheckBox=\"t\" CheckBoxIsChecked=\"f\">\n" +
                "    <span style=\"font-family:&quot;MS Gothic&quot;\">n</span>\n" +
                "</w:Sdt> This is not needed since CheckBoxIsChecked is false<o:p/>";

        Document doc = Jsoup.parse(html);

        doc.select("p > w|sdt[checkboxischecked=t]").forEach(it -> {
            String text = it.ownText();
            System.out.println(text);
        });
    }
}
