package org.jsoup.examples;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class JsonTest {

	public static void main(String[] args) {
		String sampleJson = "{\"test\" : 1, \"test2\" : 2}";
		Document doc = Jsoup.parse(sampleJson, "", Parser.jsonParser());
		System.out.println("Debuging start");
		Elements e = doc.select("test");
		System.out.println(e.get(0));
		Elements e1 = doc.select("test2");
		System.out.println(e1.get(0));
		System.out.println("Debuging End");
	}
}

