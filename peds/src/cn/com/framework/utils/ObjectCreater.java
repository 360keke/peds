package cn.com.framework.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import cn.com.framework.exception.SystemException;

public class ObjectCreater
{
  private XMLReader parser = null;
  private static String DEFAULT_PARSER_NAME = "org.apache.xerces.parsers.SAXParser";
  private ObjectCreaterHandler handler = null;
  private InputSource xml = null;
  private String xmlStr = null;

  public ObjectCreater(File xmlFileName) throws SystemException {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(xmlFileName);
    }
    catch (FileNotFoundException ex) {
      throw new SystemException(ex);
    }
    this.xml = new InputSource(fis);
  }

  public ObjectCreater(String xmlStr) {
    ByteArrayInputStream bis = new ByteArrayInputStream(xmlStr.getBytes());
    this.xmlStr = xmlStr;
    this.xml = new InputSource(bis);
  }

  public ObjectCreater(InputStream is) {
    this.xml = new InputSource(is);
  }

  public Object createObject() throws SystemException
  {
    if (this.parser == null)
    {
      try
      {
        this.parser = XMLReaderFactory.createXMLReader(DEFAULT_PARSER_NAME);
        this.handler = new ObjectCreaterHandler();
        this.parser.setContentHandler(this.handler);
        this.parser.setErrorHandler(this.handler);
        this.parser.parse(this.xml);
      }
      catch (SAXException ex) {
        throw new SystemException(ex);
      }
      catch (IOException ex) {
        throw new SystemException(ex);
      }
    }
    return this.handler.getResult();
  }
}