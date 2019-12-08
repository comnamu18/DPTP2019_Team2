package org.jsoup.parser;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.TextUtil;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.nodes.XmlDeclaration;
import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests JsonTreeBuilder.
 *
 * @author Kim SangHeon
 */
public class JsonTreeBuilderTest {
	
    @Test
    public void testSimpleJsonParse() {
        String json = "{ \"test\" : \"data\", \"test2\" : 2}";
        JsonTreeBuilder tb = new JsonTreeBuilder();
        Document doc = tb.parse(json, "http://foo.com/");
        assertEquals("<test>\"data\"</test><test2>2</test2>",
                TextUtil.stripNewlines(doc.html()));
    }
    
    @Test
    public void testRecurrentJsonObject() {
        // test: json value to json object
        String json = "{\"doc\":{\"val\":\"One\"},\"test\":3}";
        JsonTreeBuilder tb = new JsonTreeBuilder();
        Document doc = tb.parse(json, "http://foo.com/");
        assertEquals("<doc><val>\"One\"</val></doc><test>3</test>",
                TextUtil.stripNewlines(doc.html()));
    }
    
    @Test
    public void jsonParseDefaultsToHtmlOutputSyntax() {
        Document doc = Jsoup.parse("x", "", Parser.jsonParser());
        assertEquals(Syntax.json, doc.outputSettings().syntax());
    }
    
    @Test
    public void preservesCaseByDefault() {
        String json = "{\"CHECK\":\"One\", \"TEST\" : \"Check\"}";
        Document doc = Jsoup.parse(json, "", Parser.jsonParser());
        assertEquals("<CHECK>\"One\"</CHECK><TEST>\"Check\"</TEST>", TextUtil.stripNewlines(doc.html()));
    }
    
    @Test
    public void appendPreservesCaseByDefault() {
        String json = "{\"One\":\"One\"}";
        Document doc = Jsoup.parse(json, "", Parser.jsonParser());
        Elements one = doc.select("One");
        one.append("{\"Two\":\"Two\"}");
        assertEquals("<One>\"One\"<Two>\"Two\"</Two></One>", TextUtil.stripNewlines(doc.html()));
    }

    @Test
    public void canNormalizeCase() {
        String json = "{\"TEST\" : \"Check\"}";
        Document doc = Jsoup.parse(json, "", Parser.jsonParser().settings(ParseSettings.htmlDefault));
        assertEquals("<test>\"Check\"</test>", TextUtil.stripNewlines(doc.html()));
    }
    
    @Test
    public void testSupplyParserToDataStream() throws IOException, URISyntaxException {
        File jsonFile = new File(JsonTreeBuilder.class.getResource("/htmltests/json-test.json").toURI());
        InputStream inStream = new FileInputStream(jsonFile);
        Document doc = Jsoup.parse(inStream, null, "http://foo.com", Parser.jsonParser());
        assertEquals("<data><id>\"X12\"</id><from><name>\"John Kennedy\"</name><id>\"1458633\" </id></from><message>\"JSON is much easier and better than XML\" </message></data>",
                TextUtil.stripNewlines(doc.html()));
    }
}
