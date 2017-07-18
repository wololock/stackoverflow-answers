package com.github.wololock;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class JsoupParserTest {

    private static final String CHARSET = "UTF-8";
    private static final String BASE_URI = "/";

    @Test
    public void testParsingWithHtmlParser() throws IOException {
    	//given:
    	final InputStream input = getClass().getClassLoader().getResourceAsStream("Data3.txt");

    	//when:
        final Document document = Jsoup.parse(input, CHARSET, BASE_URI, Parser.htmlParser());

    	//then:
        assertThat(document.getElementById("u_0_a")).isNull();
    }

    @Test
    public void testParsingWithXmlParser() throws IOException {
        //given:
        final InputStream input = getClass().getClassLoader().getResourceAsStream("Data3.txt");

        //when:
        final Document document = Jsoup.parse(input, CHARSET, BASE_URI, Parser.xmlParser());

        //then:
        assertThat(document.getElementById("u_0_a")).isNotNull();
        //and:
        assertThat(document.getElementById("u_0_a").text()).isEqualTo("Mark all as read");
    }
}
