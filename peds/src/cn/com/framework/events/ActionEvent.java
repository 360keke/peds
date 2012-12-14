package cn.com.framework.events;

import java.util.EventObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.framework.actions.Action;

public class ActionEvent extends EventObject {
	public static final int BEFORE_ACTION = 0;
	  public static final int AFTER_ACTION = 1;
	  private int eventType;
	  private HttpServletRequest request;
	  private HttpServletResponse response;

	  public ActionEvent(Action action, int eventType, HttpServletRequest request, HttpServletResponse response)
	  {
	    super(action);
	    this.eventType = eventType;
	    this.request = request;
	    this.response = response;
	  }

	  public int getEventType() {
	    return this.eventType;
	  }

	  public HttpServletRequest getRequest() {
	    return this.request;
	  }

	  public HttpServletResponse getResponse() {
	    return this.response;
	  }

	  public void setEventType(int eventType) {
	    this.eventType = eventType;
	  }
}
