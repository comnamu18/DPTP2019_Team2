package org.jsoup.examples;

import java.io.File;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class JsonTest {

	public static void main(String[] args) {
		String jsonFile = "{\n" + 
				"   \"data\": [\n" + 
				"      {\n" + 
				"         \"id\": \"X12\",\n" + 
				"         \"from\": {\n" + 
				"            \"name\": \"John Kennedy\", \"id\": \"1458633\"\n" + 
				"         },\n" + 
				"         \"message\": \"JSON is much easier and better than XML\",\n" + 
				"         \"actions\": [\n" + 
				"            {\n" + 
				"               \"name\": \"Comment\",\n" + 
				"               \"link\": \"http://www.facebook.com/X999/posts/Y999\"\n" + 
				"            },\n" + 
				"            {\n" + 
				"               \"name\": \"Like\",\n" + 
				"               \"link\": \"http://www.facebook.com/X999/posts/Y999\"\n" + 
				"            }\n" + 
				"         ],\n" + 
				"         \"type\": \"status\",\n" + 
				"         \"created_time\": \"2010-08-02T22:27:44+0000\",\n" + 
				"         \"updated_time\": \"2010-08-02T22:27:44+0000\"\n" + 
				"      },\n" + 
				"      {\n" + 
				"         \"id\": \"X45\",\n" + 
				"         \"from\": {\n" + 
				"            \"name\": \"Anna Smith\", \"id\": \"12587521\"\n" + 
				"         },\n" + 
				"         \"message\": \"On my way to write my very first JSON snippet\",\n" + 
				"         \"actions\": [\n" + 
				"            {\n" + 
				"               \"name\": \"Comment\",\n" + 
				"               \"link\": \"http://www.facebook.com/X998/posts/Y998\"\n" + 
				"            },\n" + 
				"            {\n" + 
				"               \"name\": \"Like\",\n" + 
				"               \"link\": \"http://www.facebook.com/X998/posts/Y998\"\n" + 
				"            }\n" + 
				"         ],\n" + 
				"         \"type\": \"status\",\n" + 
				"         \"created_time\": \"2010-08-02T25:27:44+0000\",\n" + 
				"         \"updated_time\": \"2010-08-02T25:27:44+0000\"\n" + 
				"      }\n" + 
				"   ]\n" + 
				"}";
		Document doc = Jsoup.parse(jsonFile, "", Parser.jsonParser());
		System.out.println("Debuging start");
		Elements e = doc.select("data");
		System.out.println(e.get(0));
		System.out.println("Debuging End");
	}
}

