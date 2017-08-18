import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

final class ExtractFandomExample {

    public static void main(String[] args) {
        String html = "<ol class=\"work index group\">\n" +
                "    <li class=\"work blurb group\" id=\"work_10504812\" role=\"article\">\n" +
                "  <!--title, author, fandom-->\n" +
                "  <div class=\"header module\">\n" +
                "    <h4 class=\"heading\">\n" +
                "      <a href=\"/works/10504812\">Pocket Healer</a>\n" +
                "      by\n" +
                "\n" +
                "      <!-- do not cache -->\n" +
                "      <a rel=\"author\" href=\"/users/OverNoot/pseuds/OverNoot\">OverNoot</a> \n" +
                "    </h4>\n" +
                "    <h5 class=\"fandoms heading\">\n" +
                "      <span class=\"landmark\">Fandoms:</span>\n" +
                "      <a class=\"tag\" href=\"/tags/Overwatch%20(Video%20Game)/works\">Overwatch (Video Game)</a>\n" +
                "      &nbsp;\n" +
                "    </h5>\n" +
                "    <!--required tags-->\n" +
                "    <ul class=\"required-tags\">\n" +
                "<li> <a class=\"help symbol question modal modal-attached\" title=\"Symbols key\" aria-controls=\"#modal\" href=\"/help/symbols-key.html\"><span class=\"rating-general-audience rating\" title=\"General Audiences\"><span class=\"text\">General Audiences</span></span></a></li>\n" +
                "<li> <a class=\"help symbol question modal modal-attached\" title=\"Symbols key\" aria-controls=\"#modal\" href=\"/help/symbols-key.html\"><span class=\"warning-no warnings\" title=\"No Archive Warnings Apply\"><span class=\"text\">No Archive Warnings Apply</span></span></a></li>\n" +
                "<li> <a class=\"help symbol question modal modal-attached\" title=\"Symbols key\" aria-controls=\"#modal\" href=\"/help/symbols-key.html\"><span class=\"category-femslash category\" title=\"F/F\"><span class=\"text\">F/F</span></span></a></li>\n" +
                "<li> <a class=\"help symbol question modal modal-attached\" title=\"Symbols key\" aria-controls=\"#modal\" href=\"/help/symbols-key.html\"><span class=\"complete-no iswip\" title=\"Work in Progress\"><span class=\"text\">Work in Progress</span></span></a></li>\n" +
                "</ul>\n" +
                "    <p class=\"datetime\">17 Aug 2017</p>\n" +
                "  </div>\n" +
                "  <!--warnings again, cast, freeform tags-->\n" +
                "  <h6 class=\"landmark heading\">Tags</h6>\n" +
                "  <ul class=\"tags commas\">\n" +
                "    <li class=\"warnings\"><strong><a class=\"tag\" href=\"/tags/No%20Archive%20Warnings%20Apply/works\">No Archive Warnings Apply</a></strong></li><li class=\"relationships\"><a class=\"tag\" href=\"/tags/Fareeha%20%22Pharah%22%20Amari*s*Angela%20%22Mercy%22%20Ziegler/works\">Fareeha \"Pharah\" Amari/Angela \"Mercy\" Ziegler</a></li><li class=\"characters\"><a class=\"tag\" href=\"/tags/Fareeha%20%22Pharah%22%20Amari/works\">Fareeha \"Pharah\" Amari</a></li> <li class=\"characters\"><a class=\"tag\" href=\"/tags/Angela%20%22Mercy%22%20Ziegler/works\">Angela \"Mercy\" Ziegler</a></li> <li class=\"characters\"><a class=\"tag\" href=\"/tags/Winston%20(Overwatch)/works\">Winston (Overwatch)</a></li> <li class=\"characters\"><a class=\"tag\" href=\"/tags/Lena%20%22Tracer%22%20Oxton/works\">Lena \"Tracer\" Oxton</a></li><li class=\"freeforms\"><a class=\"tag\" href=\"/tags/Tiny%20Pharah%20and%20Tiny%20Mercy/works\">Tiny Pharah and Tiny Mercy</a></li> <li class=\"freeforms\"><a class=\"tag\" href=\"/tags/Fluff/works\">Fluff</a></li> <li class=\"freeforms last\"><a class=\"tag\" href=\"/tags/Cute/works\">Cute</a></li>\n" +
                "  </ul>\n" +
                "  <!--summary-->\n" +
                "    <h6 class=\"landmark heading\">Summary</h6>\n" +
                "    <blockquote class=\"userstuff summary\">\n" +
                "      <p>Angela and Fareeha wake up to find tiny alternate versions of themselves have appeared and are now imprinted on them. How will these tiny Pharahs and Mercies impact their work at Overwatch and more importantly how will it impact the feelings they have for each other.</p>\n" +
                "    </blockquote>\n" +
                "  <!--stats-->\n" +
                "\n" +
                "  <dl class=\"stats\">\n" +
                "      <dt class=\"language\">Language:</dt>\n" +
                "      <dd class=\"language\">English</dd>\n" +
                "    <dt class=\"words\">Words:</dt>\n" +
                "    <dd class=\"words\">35,143</dd>\n" +
                "    <dt class=\"chapters\">Chapters:</dt>\n" +
                "    <dd class=\"chapters\">10/11</dd>\n" +
                "    <dt class=\"comments\">Comments:</dt>\n" +
                "    <dd class=\"comments\"><a href=\"/works/10504812?show_comments=true&amp;view_full_work=true#comments\">168</a></dd>\n" +
                "    <dt class=\"kudos\">Kudos:</dt>\n" +
                "    <dd class=\"kudos\"><a href=\"/works/10504812?view_full_work=true#comments\">438</a></dd>\n" +
                "    <dt class=\"bookmarks\">Bookmarks:</dt>\n" +
                "    <dd class=\"bookmarks\"><a href=\"/works/10504812/bookmarks\">35</a></dd>\n" +
                "    <dt class=\"hits\">Hits:</dt>\n" +
                "    <dd class=\"hits\">5890</dd>\n" +
                "  </dl>\n" +
                "</li></ol>";

        Document doc = Jsoup.parse(html);

        Elements ol = doc.select("ol.work > li");

        for (Element li : ol) {
            String title = li.select("h4.heading a").first().text();
            String author = li.select("h4.heading a[rel=author]").text();
            String id = li.attr("id").replaceAll("work_","");
            String url = "http://archiveofourown.com/works/" + id;
            String summary = li.select("blockquote.summary").text();
            String rating = li.select("span.rating").text();

            System.out.println("Title: " + title);
            System.out.println("Author: " + author);
            System.out.println("ID: " + id);
            System.out.println("URL: " + url);
            System.out.println("Summary: " + summary);
            System.out.println("Rating: " + rating);
        }
    }
}
