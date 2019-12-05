package org.jsoup.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class XmlParserTest {
	public static void main(String[] args)throws Exception{
		//String html = "<tests><test><id>xxx</id><status>xxx</status></test><test><id>xxx</id><status>xxx</status></test></tests></xml>";
		String html = "<test>1</test><tes>2</tes>";
		Document doc = Jsoup.parse(html, "", Parser.xmlParser());
		for (Element e : doc.select("test")) {
		    System.out.println(e);
		}
		for (Element e : doc.select("tes")) {
		    System.out.println(e);
		}
	}
}
