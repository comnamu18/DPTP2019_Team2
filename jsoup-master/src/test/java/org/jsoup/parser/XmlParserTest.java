package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XmlParserTest {
	public static void main(String[] args)throws Exception{
		Document doc = Jsoup.connect("https://mportal.cau.ac.kr/main.do").get();
		for (Element e : doc.select("main")) {
		    System.out.println(e);
		}
		Elements titles = doc.select("div.nb-main-footer div.nb-footer-info ul li");
		for (Element e: titles) {
		    System.out.println(e);
		}
	}
}
