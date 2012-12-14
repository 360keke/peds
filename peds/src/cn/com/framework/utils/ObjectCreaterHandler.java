package cn.com.framework.utils;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import cn.com.framework.exception.SystemException;
import cn.com.peds.common.StringUtils;

public class ObjectCreaterHandler extends DefaultHandler
{
  private StringBuffer strbuf = null;
  private Stack stack = null;
  private Stack arrayComponentTypeStack = null;
  private Object root = null;

  public void characters(char[] ch, int start, int length)
    throws SAXException
  {
    if (this.strbuf == null) {
      this.strbuf = new StringBuffer();
    }
    this.strbuf.append(ch, start, length);
  }

  private String convertName(String itemName) {
    return itemName.substring(0, 1).toUpperCase() + itemName.substring(1);
  }

  public void startDocument() throws SAXException {
    this.stack = new Stack();
    this.arrayComponentTypeStack = new Stack();
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes)
    throws SAXException
  {
    this.strbuf = new StringBuffer();
    Object obj = null;
    Vector Vector = null;
    String formatedName = convertName(localName);
    try {
      if (attributes.getValue("ClassName") != null) {
        Class elementClass = Class.forName(attributes.getValue("ClassName"));

        if (localName.equalsIgnoreCase("array")) {
          this.arrayComponentTypeStack.push(elementClass);
          obj = new Vector();
        }
        else if (Class.forName("java.sql.Clob").isAssignableFrom(elementClass))
        {
          try {
            obj = StringUtils.converStringToClob("");
          }
          catch (SystemException ex) {
            if (ex.getSourceException() != null) {
              throw new SAXException(ex.getSourceException());
            }

            throw new SAXException(ex);
          }
        }
        else
        {
          obj = elementClass.newInstance();
          int countAttributes = attributes.getLength();

          for (int i = 0; i < countAttributes; ++i) {
            String key = attributes.getLocalName(i);
            try {
              Method getMethod = null;
              String subName = key.substring(0, 1).toUpperCase() + key.substring(1);
              try
              {
                getMethod = obj.getClass().getMethod("get" + subName, new Class[0]);
              }
              catch (NoSuchMethodException n)
              {
                getMethod = obj.getClass().getMethod("is" + subName, new Class[0]);
              }

              Class type = getMethod.getReturnType();
              Method setMethod = obj.getClass().getMethod("set" + subName, new Class[] { type });

              setMethod.invoke(obj, new Object[] { StringUtils.convert(type, attributes.getValue(i)) });
            }
            catch (NoSuchMethodException n)
            {
            }
            catch (InvocationTargetException e)
            {
              throw new SAXException(e);
            }
            catch (SystemException e) {
              throw new SAXException(e);
            }
          }
        }
      }
      else {
        Class type = null;

        if ((!(Class.forName("java.util.Collection").isAssignableFrom(this.stack.peek().getClass()))) && (!(Class.forName("java.util.Map").isAssignableFrom(this.stack.peek().getClass()))))
        {
          try
          {
            type = this.stack.peek().getClass().getMethod("get" + formatedName, new Class[0]).getReturnType();
          }
          catch (NoSuchMethodException n)
          {
            type = this.stack.peek().getClass().getMethod("is" + formatedName, new Class[0]).getReturnType();
          }

          type = convertTypeClass(type);

          if (type.isArray())
          {
            obj = new Vector();
          }
          else {
            try
            {
              obj = type.getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (NoSuchMethodException e)
            {
              obj = type.getConstructor(new Class[] { Class.forName("java.lang.String") }).newInstance(new Object[] { new String("0") });
            }

          }

        }
        else
        {
          obj = new String();
        }
      }
      this.stack.push(obj);
    }
    catch (NoSuchMethodException n) {
      throw new SAXException(n);
    }
    catch (ClassNotFoundException c) {
      throw new SAXException(c);
    }
    catch (IllegalAccessException e) {
      throw new SAXException(e);
    }
    catch (InstantiationException e) {
      throw new SAXException(e);
    }
    catch (InvocationTargetException e) {
      throw new SAXException(e);
    }

    if (this.root == null)
      this.root = obj;
  }

  public void endElement(String uri, String localName, String qName)
    throws SAXException
  {
    Object obj = null;
    Object parent = null;
    String formatedName = convertName(localName);
    obj = this.stack.pop();

    if ((this.strbuf != null) && (!(this.strbuf.toString().trim().equalsIgnoreCase("")))) {
      String content = this.strbuf.toString().trim();
      try
      {
        if (Class.forName("java.lang.String").isAssignableFrom(obj.getClass()))
        {
          obj = content;
        }
        if (Class.forName("java.sql.Clob").isAssignableFrom(obj.getClass())) {
          try
          {
            obj = StringUtils.converStringToClob(content);
          }
          catch (SystemException ex) {
            if (ex.getSourceException() != null) {
              throw new SAXException(ex.getSourceException());
            }

            throw new SAXException(ex);
          }

        }
        else
        {
          obj = obj.getClass().getConstructor(new Class[] { Class.forName("java.lang.String") }).newInstance(new Object[] { content });
        }
      }
      catch (ClassNotFoundException e) {
      }
      catch (NoSuchMethodException e) {
      }
      catch (InvocationTargetException e) {
        throw new SAXException(e);
      }
      catch (IllegalAccessException e) {
        throw new SAXException(e);
      }
      catch (InstantiationException e) {
        throw new SAXException(e);
      }

      this.strbuf = null;
    }
    try
    {
      if ((localName.equalsIgnoreCase("array")) && (Class.forName("java.util.Collection").isAssignableFrom(obj.getClass())))
      {
        Class componentType = (Class)this.arrayComponentTypeStack.pop();
        obj = convertCollectionToArray((Collection)obj, componentType);
      }
    }
    catch (ClassNotFoundException ex)
    {
    }

    if (!(this.stack.empty())) {
      parent = this.stack.peek();
      try
      {
        if (Class.forName("java.util.Collection").isAssignableFrom(parent.getClass()))
        {
          ((Collection)parent).add(obj);
        }
        else if (Class.forName("java.util.Map").isAssignableFrom(parent.getClass()))
        {
          ((Map)parent).put(localName, obj);
        }
        else
        {
          Method getMethod = null;
          try {
            getMethod = parent.getClass().getMethod("get" + formatedName, new Class[0]);
          }
          catch (NoSuchMethodException e)
          {
            getMethod = parent.getClass().getMethod("is" + formatedName, new Class[0]);
          }

          Class parameterType = getMethod.getReturnType();

          Method setMethod = parent.getClass().getMethod("set" + formatedName, new Class[] { parameterType });

          if ((parameterType.isArray()) && (!(localName.equalsIgnoreCase("array"))))
          {
            Object array = convertCollectionToArray((Collection)obj, parameterType.getComponentType());

            setMethod.invoke(parent, new Object[] { array });
          }
          else
          {
            setMethod.invoke(parent, new Object[] { obj });
          }

        }

      }
      catch (ClassNotFoundException e)
      {
        throw new SAXException(e);
      }
      catch (NoSuchMethodException parameterType) {
        throw new SAXException("没有与" + localName + "相关的get/set方法。");
      }
      catch (InvocationTargetException e) {
        throw new SAXException(e);
      }
      catch (IllegalAccessException e) {
        throw new SAXException(e);
      }
    }
  }

  public void endDocument() throws SAXException
  {
  }

  public Object getResult()
  {
    return this.root;
  }

  private Object convertCollectionToArray(Collection obj, Class componentType)
  {
    int size = obj.size();
    Object array = Array.newInstance(componentType, size);
    Iterator iterator = obj.iterator();
    int i = 0;
    while (iterator.hasNext()) {
      Array.set(array, i++, iterator.next());
    }
    return array;
  }

  private Class convertTypeClass(Class type) throws ClassNotFoundException {
    if (type.getName().equalsIgnoreCase("boolean")) {
      return Class.forName("java.lang.Boolean");
    }
    if (type.getName().equalsIgnoreCase("int")) {
      return Class.forName("java.lang.Integer");
    }
    if (type.getName().equalsIgnoreCase("long")) {
      return Class.forName("java.lang.Long");
    }
    if (type.getName().equalsIgnoreCase("double")) {
      return Class.forName("java.lang.Double");
    }
    if (type.getName().equalsIgnoreCase("float")) {
      return Class.forName("java.lang.Float");
    }
    if (type.getName().equalsIgnoreCase("char")) {
      return Class.forName("java.lang.Character");
    }

    return type;
  }
}
