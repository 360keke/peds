package cn.com.framework.view;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


public class Screen implements Serializable{
	private String template;
	  private HashMap parameters;
	  private String name;

	  public Screen()
	  {
	    this.template = null;
	    this.parameters = new HashMap();
	  }

	  public String getTemplate() {
	    return this.template;
	  }

	  public HashMap getParameters() {
	    return this.parameters;
	  }

	  public Parameter getParameter(String key) {
	    return ((Parameter)this.parameters.get(key));
	  }

	  public void putParameter(String key, Parameter parameter) {
	    this.parameters.put(key, parameter);
	  }

	  public void setParameters(HashMap parameters) {
	    this.parameters = parameters;
	  }

	  public void setTemplate(String template) {
	    this.template = template;
	  }

	  public void copyParameters(Screen sourceScreen) {
	    HashMap parameters = sourceScreen.getParameters();
	    Set keySet = parameters.keySet();
	    Iterator iterator = keySet.iterator();
	    while (iterator.hasNext()) {
	      String key = (String)iterator.next();
	      this.parameters.put(key, parameters.get(key));
	    }
	  }

	  public String getName() {
	    return this.name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
}
