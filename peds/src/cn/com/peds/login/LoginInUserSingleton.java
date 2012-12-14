package cn.com.peds.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.peds.derby.DataObject;

public class LoginInUserSingleton {
	private static LoginInUserSingleton instance = null;

	public static synchronized LoginInUserSingleton getInstance() {
		if (instance == null){
			instance = new LoginInUserSingleton();
		}
		return instance;
	}

	private Map<String, String> userMap = null;

	private Map<String, HttpSession> sessionMap = null;

	private LoginInUserSingleton() {
		userMap = Collections
				.synchronizedMap(new LinkedHashMap<String, String>());
		sessionMap = Collections
				.synchronizedMap(new LinkedHashMap<String, HttpSession>());
	}

	public void addLoginInfo(String loginname, String Sessionid,
			HttpSession session) {
		userMap.put(loginname, Sessionid);
		sessionMap.put(loginname, session);
	};

	
	public void removeLoginInfo(String sessionid) {
		userMap.remove(sessionid);
		sessionMap.remove(sessionid);
	}

	public boolean isAccountUsedByOther(DataObject user_object,HttpServletRequest req) {
		try {
			if ((userMap != null)
					&& (user_object != null)
					&& (userMap.get(user_object.getString("login_name")) != null)
					&& (!req.getSession().getId().equals(
							userMap.get(user_object.getString("login_name"))))) {
				return true;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}

	public List getOnLineUserInfo(){
		
	    List list=new ArrayList();
	    Iterator iter = sessionMap.entrySet().iterator(); 
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    HttpSession session = (HttpSession)entry.getValue(); 
 		    Map map = new HashMap();
		    try{
			    map.put("user_name", LoginBo.getUserName(session));
			    map.put("user_role", LoginBo.getUserRole(session));
			    map.put("address_ip", LoginBo.getUserAddress(session));
			    map.put("login_time", LoginBo.getUserLoginTime(session)); 
			    map.put("orgname", LoginBo.getOrgName(session)); 
		    }catch(Exception e){
			    e.printStackTrace();
		    }
		    if ((map.get("user_name").toString()!=null)&&(!"".equals(map.get("user_name").toString()))){
		        list.add(map);
			}
	    }
        return list;
    }
}
