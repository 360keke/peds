package cn.com.framework.actions;

import java.util.HashMap;

import cn.com.framework.view.Screen;

public class ScreenMappings {
	private HashMap screens;

	  public ScreenMappings()
	  {
	    this.screens = new HashMap(); }

	  public void putScreen(String mappingName, Screen screen) { this.screens.put(mappingName, screen); }

	  public void putScreen(String mappingName, String pageName) {
	    this.screens.put(mappingName, pageName); }

	  public Object getScreen(String mappingName) {
	    return this.screens.get(mappingName);
	  }
}
