import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class ExtractPhoneNumber {

    public static void main(String[] args) {
        String html = "    <div class=\"contact_address\">\n" +
                "     <div class=\"contact_column_1\">\n" +
                "      2261 N. Clybourn Ave.\n" +
                "    <br> Chicago, IL 60614\n" +
                "    <br> Ph: (773) 348.2226\n" +
                "    <br> \n" +
                "    <a href=\"http://maps.google.com/maps?q=2261+N.+Clybourn+Ave.Chicago,+IL+60614&amp;hl=en&amp;sll=41.923214,-87.666462&amp;sspn=0.014417,0.029268&amp;gl=us&amp;hnear=2261+N+Clybourn+Ave,+Chicago,+Cook,+Illinois+60614&amp;t=m&amp;z=17\" target=\"_new\"><img src=\"/wp-content/themes/artgallery_3.0/images/map.png\" alt=\"map\"></a>\n" +
                "    <br> Hours:\n" +
                "    <br> M-S 7:30am – 7:00pm\n" +
                "    <br> Sun 9:00am – 5:00pm\n" +
                "   </div> \n" +
                "   <div class=\"contact_column_2\">\n" +
                "    &nbsp;\n" +
                "    <br>&nbsp;\n" +
                "   </div> ";


        Document doc = Jsoup.parse(html);
        String regex_num = "\\+?[0-9. ()-]{10,25}";
        Pattern pattern = Pattern.compile(regex_num);

        Elements phones = doc.getElementsMatchingOwnText(pattern);


        phones.forEach(it -> {
            System.out.println(it.text());

            Matcher matcher = pattern.matcher(it.text());
            if (matcher.find()) {
                System.out.println(matcher.group(0).trim());
            }
        });
    }
}
