package org.jsoup.parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.jsoup.helper.Validate;
import org.jsoup.nodes.CDataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

/**
 * Use the {@code JsonTreeBuilder} when you want to parse JSON without any of the HTML DOM rules being applied to the
 * document.
 * <p>Usage example: {@code Document xmlDoc = Jsoup.parse(html, baseUrl, Parser.jsonParser());}</p>
 *
 * @author KimSangHeon, ChooJangYeon, CheonJoKyo, ImgiChan
 */
public class JsonTreeBuilder extends TreeBuilder {
	ParseSettings defaultSettings() {
		return ParseSettings.preserveCase;
	}
    @Override
    protected void initialiseParse(Reader input, String baseUri, Parser parser) {
        super.initialiseParse(input, baseUri, parser);
        stack.add(doc); // place the document onto the stack. differs from HtmlTreeBuilder (not on stack)
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.json);
    }

    Document parse(Reader input, String baseUri) {
        return parse(input, baseUri, new Parser(this));
    }

    Document parse(String input, String baseUri) {
        return parse(new StringReader(input), baseUri, new Parser(this));
    }
    
    
    List<Node> parseFragment(String inputFragment, String baseUri, Parser parser) {
        initialiseParse(new StringReader(inputFragment), baseUri, parser);
        runParser();
        return doc.childNodes();
    }

    List<Node> parseFragment(String inputFragment, Element context, String baseUri, Parser parser) {
        return parseFragment(inputFragment, baseUri, parser);
    }

	@Override
	protected boolean process(Token token) {
        // Json key as start tag, value as Character, end tag
        switch (token.type) {
            case StartTag:
                insert(token.asStartTag());
                break;
            case EndTag:
                popStackToClose(token.asEndTag());
                break;
            case Character:
                insert(token.asCharacter());
                break;
            case EOF: // could put some normalisation here if desired
                break;
            default:
                Validate.fail("Unexpected token type: " + token.type);
        }
        return true;
	}
	
    private void insertNode(Node node) {
        currentElement().appendChild(node);
    }
    
    Element insert(Token.StartTag startTag) {
        Tag tag = Tag.valueOf(startTag.name(), settings);
        
        startTag.attributes.deduplicate(settings);

        Element el = new Element(tag, baseUri, settings.normalizeAttributes(startTag.attributes));
        insertNode(el);
        if (startTag.isSelfClosing()) {
            if (!tag.isKnownTag()) // unknown tag, remember this is self closing for output. see above.
                tag.setSelfClosing();
        } else {
            stack.add(el);
        }
        return el;
    }
    
    void insert(Token.Character token) {
        final String data = token.getData();
        insertNode(new TextNode(data));
    }
    
    /**
     * If the stack contains an element with this tag's name, pop up the stack to remove the first occurrence. If not
     * found, skips.
     *
     * @param endTag tag to close
     */
    private void popStackToClose(Token.EndTag endTag) {
        String elName = settings.normalizeTag(endTag.tagName);
        Element firstFound = null;

        for (int pos = stack.size() -1; pos >= 0; pos--) {
            Element next = stack.get(pos);
            if (next.nodeName().equals(elName)) {
                firstFound = next;
                break;
            }
        }
        if (firstFound == null)
            return; // not found, skip

        for (int pos = stack.size() -1; pos >= 0; pos--) {
            Element next = stack.get(pos);
            stack.remove(pos);
            if (next == firstFound)
                break;
        }
    }
}

