import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.regex.Pattern;

final class ExtractPriceExample {

    public static void main(String[] args) {
        String html = "<a class=\"a-size-small a-link-normal a-text-normal\" href=\"http://rads.stackoverflow.com/amzn/click/B01MZYYWUH\">1</a></div>\n" +
                "<div class=\"a-row a-spacing-mini\"><span class=\"a-size-small a-color-secondary a-text-bold\">Product Description</span><br><span class=\"a-size-small a-color-secondary\">... Cards Radeon&trade; <em>RX</em> 460 Graphics Cards Radeon&trade; R9 <em>390</em> Graphics Cards ...</span></div>\n" +
                "</div></div></div></div></div></div></li>\n" +
                "<li id=\"result_2\" data-asin=\"B00IAAU6SS\" class=\"s-result-item celwidget \">\n" +
                "   <div class=\"s-item-container\">\n" +
                "   <div class=\"a-fixed-left-grid\">\n" +
                "   <div class=\"a-fixed-left-grid-inner\" style=\"padding-left:218px\">\n" +
                "   <div class=\"a-fixed-left-grid-col a-col-left\" style=\"width:218px;margin-left:-218px;_margin-left:-109px;float:left;\">\n" +
                "      <div class=\"a-row\">\n" +
                "         <div aria-hidden=\"true\" class=\"a-column a-span12 a-text-center\">\n" +
                "            <a class=\"a-link-normal a-text-normal\" href=\"http://rads.stackoverflow.com/amzn/click/B00IAAU6SS\"><img src=\"https://images-na.ssl-images-amazon.com/images/I/419c5Ci-UqL._AC_US218_.jpg\" srcset=\"https://images-na.ssl-images-amazon.com/images/I/419c5Ci-UqL._AC_US218_.jpg 1x, https://images-na.ssl-images-amazon.com/images/I/419c5Ci-UqL._AC_US327_FMwebp_QL65_.jpg 1.5x, https://images-na.ssl-images-amazon.com/images/I/419c5Ci-UqL._AC_US436_FMwebp_QL65_.jpg 2x, https://images-na.ssl-images-amazon.com/images/I/419c5Ci-UqL._AC_US500_FMwebp_QL65_.jpg 2.2935x\" width=\"218\" height=\"218\" alt=\"Product Details\" class=\"s-access-image cfMarker\" data-search-image-load></a>\n" +
                "            <div class=\"a-section a-spacing-none a-text-center\"></div>\n" +
                "         </div>\n" +
                "      </div>\n" +
                "   </div>\n" +
                "   <div class=\"a-fixed-left-grid-col a-col-right\" style=\"padding-left:2%;*width:97.6%;float:left;\">\n" +
                "   <div class=\"a-row a-spacing-small\">\n" +
                "      <div class=\"a-row a-spacing-none scx-truncate-medium sx-line-clamp-3 s-list-title-long\">\n" +
                "         <a class=\"a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal\" title=\"Arctic Accelero Xtreme IV 280(X) - High-End Graphics Card Cooler with Backside Cooler for Efficient RAM and VR-Cooling - DCACO-V930001-GBA01\" href=\"http://rads.stackoverflow.com/amzn/click/B00IAAU6SS\">\n" +
                "            <h2 data-attribute=\"Arctic Accelero Xtreme IV 280(X) - High-End Graphics Card Cooler with Backside Cooler for Efficient RAM and VR-Cooling - DCACO-V930001-GBA01\" data-max-rows=\"3\" class=\"a-size-medium s-inline  s-access-title  a-text-normal\">Arctic Accelero Xtreme IV 280(X) - High-End Graphics Card Cooler with Backside Cooler for Efficient RAM and VR-Cooling - DCACO-V930001-GBA01</h2>\n" +
                "         </a>\n" +
                "      </div>\n" +
                "      <div class=\"a-row a-spacing-none\"><span class=\"a-size-small a-color-secondary\">by </span><span class=\"a-size-small a-color-secondary\">ARCTIC</span></div>\n" +
                "   </div>\n" +
                "   <div class=\"a-row\">\n" +
                "   <div class=\"a-column a-span7\">\n" +
                "   <div class=\"a-row a-spacing-none\"><a class=\"a-link-normal a-text-normal\" href=\"http://rads.stackoverflow.com/amzn/click/B00IAAU6SS\"><span aria-label=\"$85.99\" class=\"a-color-base sx-zero-spacing\"><span class=\"sx-price sx-price-large\">\n" +
                "      <sup class=\"sx-price-currency\">$</sup>\n" +
                "      <span class=\"sx-price-whole\">85</span>\n" +
                "      <sup class=\"sx-price-fractional\">99</sup>\n" +
                "      </span>\n" +
                "      </span></a><span class=\"a-letter-space\"></span><i class=\"a-icon a-icon-prime a-icon-small s-align-text-bottom\" aria-label=\"Prime\"><span class=\"a-icon-alt\">Prime</span></i>\n" +
                "   </div>\n" +
                "   <div class=\"a-row a-spacing-mini\">\n" +
                "      <div class=\"a-row a-spacing-none\"><span class=\"a-size-small a-color-secondary\">FREE Shipping on eligible orders</span></div>\n" +
                "      <div class=\"a-row a-spacing-none\"><span class=\"a-size-small a-color-price\">Only 8 left in stock - order soon.</span></div>\n" +
                "   </div>\n" +
                "   <div class=\"a-row a-spacing-mini\">\n" +
                "   <div class=\"a-row a-spacing-none\">\n" +
                "      <div class=\"a-row a-spacing-mini\"></div>\n" +
                "      <span class=\"a-size-small a-color-secondary\">More Buying Choices</span>\n" +
                "   </div>\n" +
                "   <div class=\"a-row a-spacing-none\">\n" +
                "   <a class=\"a-size-small a-link-normal a-text-normal\" href=\"http://rads.stackoverflow.com/amzn/click/B00IAAU6SS\"><span class=\"a-color-secondary a-text-strike\"></span><span class=\"a-size-base a-color-base\">$85.99</span>";

        final Document doc = Jsoup.parse(html);

        final Elements prices = doc.select(".sx-price");

        final Pattern pattern = Pattern.compile("^\\$?([0-9]+)\\.([0-9]{2})$");

        for (Element el : prices) {
            String price = "";
            if (el.parent().hasAttr("aria-label") && pattern.matcher(el.parent().attr("aria-label")).find()) {
                System.out.println("Extracting price from aria-label...");
                price = el.parent().attr("aria-label");

            } else {
                System.out.println("Extracting price from span body...");
                String currency = el.select(".sx-price-currency").text();
                String whole = el.select(".sx-price-whole").text();
                String fractional = el.select(".sx-price-fractional").text();

                price = String.format("%s%s.%s", currency, !whole.isEmpty() ? whole : "00", !fractional.isEmpty() ? fractional : "00");
            }

            System.out.println(price);
        }
    }
}
