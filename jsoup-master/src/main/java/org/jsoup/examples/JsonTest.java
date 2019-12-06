package org.jsoup.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.JsonTreeBuilder;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class JsonTest {

	public static void main(String[] args) {
		/*
		String jsonFile = "{\n" + 
				"   \"data\": {\n" + 
				"      \"id\": \"X12\",\n" + 
				"      \"from\": {\n" + 
				"         \"name\": \"John Kennedy\", \"id\": \"1458633\"\n" + 
				"      },\n" + 
				"      \"message\": \"JSON is much easier and better than XML\"\n" + 
				"   }\n" + 
				"}";
		String simpleJson = "{ \"test\" : \"data\", \"test2\" : 2}";
		Document doc = Jsoup.parse(jsonFile, "", Parser.jsonParser());
		System.out.println(doc.html());*/
		
		File jsonFile;
		try {
			jsonFile = new File(JsonTreeBuilder.class.getResource("/html/json-test.json").toURI());
	        InputStream inStream = new FileInputStream(jsonFile);
	        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.jsonParser());
			System.out.println("Debuging start");
			System.out.println(doc.html());
			System.out.println("Debuging End");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}
}

