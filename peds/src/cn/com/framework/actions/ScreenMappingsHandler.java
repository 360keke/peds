package cn.com.framework.actions;

import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.com.framework.view.Parameter;
import cn.com.framework.view.Screen;

public class ScreenMappingsHandler extends DefaultHandler {
	private ScreenMappings mappings = new ScreenMappings();
	private StringBuffer strbuf = null;
	private Stack stack = null;

	public ScreenMappings getResult() {
		return this.mappings;
	}

	public void startDocument() throws SAXException {
		this.stack = new Stack();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.strbuf = new StringBuffer();
		if ((localName.equalsIgnoreCase("ScreenMappings"))
				|| (localName.equalsIgnoreCase("Templates")))
			return;
		if (localName.equalsIgnoreCase("Screens"))
			return;
		Screen screen;
		if (attributes.getValue("template") != null) {
			screen = new Screen();
			screen.setName(localName);
			String templateName = attributes.getValue("template");

			Screen template = (Screen) this.mappings.getScreen(templateName);
			if (template == null) {
				throw new SAXException(localName + "页面的template属性不正确或者相关模板不存在。");
			}
			screen.setTemplate(template.getTemplate());
			screen.copyParameters(template);
			this.stack.push(screen);
			this.mappings.putScreen(localName, screen);
		} else if (attributes.getValue("file") != null) {
			screen = new Screen();
			screen.setTemplate(attributes.getValue("file"));
			this.stack.push(screen);
			this.mappings.putScreen(localName, screen);
		} else if (attributes.getValue("value") != null) {
			Parameter parameter = new Parameter();
			parameter.setKey(localName);
			if (attributes.getValue("direct") != null) {
				parameter.setDirect(new Boolean(attributes.getValue("direct"))
						.booleanValue());
			}
			if (attributes.getValue("value") != null)
				parameter.setValue(attributes.getValue("value"));
			((Screen) this.stack.peek()).putParameter(localName, parameter);
			this.stack.push(parameter);
		} else {
			this.stack.push(localName);
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ((localName.equalsIgnoreCase("ScreenMappings"))
				|| (localName.equalsIgnoreCase("Templates")))
			return;
		if (localName.equalsIgnoreCase("Screens")) {
			return;
		}

		if (this.stack.peek().getClass().getName().endsWith("Parameter")) {
			this.stack.pop();
		} else if (this.stack.peek().getClass().getName().endsWith("Screen")) {
			this.stack.pop();
		} else if (this.stack.peek().getClass().getName().endsWith("String")) {
			String key = (String) this.stack.pop();
			this.mappings.putScreen(key, this.strbuf.toString());
			this.strbuf = new StringBuffer();
		}
	}

	public void endDocument() throws SAXException {
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if (this.strbuf == null) {
			this.strbuf = new StringBuffer();
		}
		this.strbuf.append(ch, start, length);
	}
}
