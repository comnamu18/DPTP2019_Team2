package org.jsoup.examples;

import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

public class JsonTest {

	public static void main(String[] args) {
		String sampleJson = "{\"test\" : 1}";
		Document doc = Jsoup.parse(sampleJson, "", Parser.jsonParser());
		System.out.println("Debuging start");
		Map<String, String> test = doc.dataset();
		for (String item: test.keySet()) {
			String key = item.toString();
			String value = test.get(item).toString();
			System.out.println(key + " : " + value);
		}
		System.out.println("Debuging End");
	}
}
