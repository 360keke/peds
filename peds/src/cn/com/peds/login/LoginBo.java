package cn.com.peds.login;

import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.framework.exception.SystemException;
import cn.com.peds.common.PropertyManager;
import cn.com.peds.derby.DataObject;

/**
 * 当前登录用户的信息接口
 * @author zhengqisong
 * write on 2007-02-08
 */
public class LoginBo {
 
  /**
  *登录用户的信息的session注册标记 
  */
  public static String SESSION_LOGIN_USERBEAN="session_user_bean";
  public LoginBo(){
  }
  
  /**
   * 取得登录用户的信息
   * @param request HttpServletRequest
   * @return DataObject
   * @throws SystemException
   */
  public static DataObject getUserBean(HttpServletRequest request)
    throws SystemException{
	  return getUserBean(request.getSession());	  
  }
  /**
   * 取得登录用户的信息
   * @param session HttpSession
   * @return DataObject
   * @throws SystemException
   */
  static DataObject getUserBean(HttpSession session)
    throws SystemException{
	  DataObject dataobj = (DataObject) session.getAttribute(LoginBo.SESSION_LOGIN_USERBEAN);
      if (dataobj == null) {
        throw new SystemException("您没有登录或登录时间过长,请重新登录!");//系统错误页面指向screenmapping中的debug-page
      }
      return dataobj;  	  
  }

  /**
   * 取得登录的姓名
   * @param request HttpServletRequest
   * @return String 姓名
   * @throws SystemException
   */
  public static String getUserName(HttpServletRequest request)
   throws SystemException{
	 return getUserBean(request).getString("user_name");
  }

  /**
   * 取得登录的姓名
   * @param session HttpSession
   * @return String 姓名
   * @throws SystemException
   */
  public static String getUserName(HttpSession session)
   throws SystemException{
	 return getUserBean(session).getString("user_name");
  }
  /**
   * 取得用户的登录帐号
   * @param request HttpServletRequest
   * @return 登录帐号
   * @throws SystemException
   */
  public static String getUserLoginAccount(HttpServletRequest request)
    throws SystemException{
 	 return getUserBean(request).getString("login_name");
  }
   
  /**
   * 取得用户的ID
   * @param session HttpSession
   * @return 用户ID
   * @throws SystemException
   */
  public static int getUserId(HttpSession session)
    throws SystemException{
	    return getUserBean(session).getInt("user_id");
  }
  
  /**
   * 取得用户的ID
   * @param request HttpServletRequest
   * @return 用户ID
   * @throws SystemException
   */
  public static int getUserId(HttpServletRequest request)
    throws SystemException{
	    return getUserBean(request).getInt("user_id");
  }
  /**
   * 取得用户的加密密码
   * @param session HttpSession
   * @return 用户加密密码
   * @throws SystemException
   */
  public static String getPassword(HttpSession session)
    throws SystemException{
	    return getUserBean(session).getString("password");
  }
  
  /**
   * 取得用户的加密密码
   * @param request HttpServletRequest
   * @return 用户加密密码
   * @throws SystemException
   */
  public static String getPassword(HttpServletRequest request)
    throws SystemException{
	    return getUserBean(request).getString("password");
  }
  /**
   * 取得用户的等级
   * @param request HttpServletRequest
   * @return 用户等级
   * @throws SystemException
   */
  public static int getUserLevel(HttpServletRequest request)
    throws SystemException{
	    return getUserBean(request).getInt("user_level");
  }
  /**
   * 判断是否是超级管理员
   * @param request HttpServletRequest
   * @return 如果是管理员组返回true,否则返回false;
   * @throws SystemException
   */
  public static boolean isAdmin(HttpServletRequest request)
   throws SystemException{
	  return true;
//	  String zlx=getUserBean(request).getString("zlx");
//	  if(zlx.equals("0"))
//		  return true;
//	  else 
//		  return false;
  }
  
  /**
   * 向session注册日志ID
   * @param session HttpSession
   * @param rydlrz_id 日志ID
   */
  public static void setUserLoginId(HttpSession session,String loginId) throws SystemException {
	  DataObject dataobj = getUserBean(session);
	  dataobj.put("login_id",loginId);
  }
  
  /**
   * 取得用户的登录ID
   * @param session HttpSession
   * @return String 用户的登录ID
   * @throws SystemException
   */
  public static String getUserLoginId(HttpSession session) throws SystemException {
	  DataObject dataobj = getUserBean(session);
	  return dataobj.getString("login_id");
  }
  
  /**
   * 取出用户登录IP
   * @param request HttpServletRequest
   * @return 用户登录的IP
   * @throws SystemException
   */
  public static String getUserAddress(HttpServletRequest request)
   throws SystemException{
	 return getUserBean(request).getString("remoteAddress");
  }
  /**
   * 取出用户登录IP
   * @param session HttpSession
   * @return String 用户登录的IP
   */
  public static String getUserAddress(HttpSession session) throws SystemException{
	  return getUserBean(session).getString("remoteAddress");
  }
  
  /**
   * 取出用户登录的时间
   * @param session HttpSession
   * @return 用户登录的时间
   * @throws SystemException
   */
  public static Date getUserLoginTime(HttpSession session) throws SystemException{
	  return getUserBean(session).getDate("login_time");
  }
  
  /**
   * 取出登录用户的角色
   * @param session HttpSession
   * @return 用户的角色
   * @throws SystemException
   */
  public static String getUserRole(HttpSession session) throws SystemException{
	  return getUserBean(session).getString("user_role");
  }
  /**
   * 取出登录用户的角色ID
   * @param session HttpSession
   * @return 用户的角色ID
   * @throws SystemException
   */
  public static int getUserRoleId(HttpServletRequest request)
  throws SystemException{
	  return getUserBean(request).getInt("role_id");
  }
  /**
   * 取出登录用户的所属业务网ID
   * @param session HttpSession
   * @return 所属业务网ID
   * @throws SystemException
   */
//  public static String getBusinessNet(HttpServletRequest request)
//  throws SystemException{
//	  String businessnet = getUserBean(request).getString("businessnet");
//	  if(businessnet==null || "".equals(businessnet)){
//		  //如果没有分配业务网，则默认为省中心
//		  businessnet = "1";
//	  }
//	  return businessnet;
//  }
  /**
   * 取出登录用户的所管理的业务系统列表，以逗号分隔
   * @param session HttpSession
   * @return 业务系统列表，以逗号分隔
   * @throws SystemException
   */
  public static String getBusinessSys(HttpServletRequest request)
  throws SystemException{
	  return getUserBean(request).getString("businesssys");
  }
 
  /**
   * 获取当前用户权限范围内的资源列表
   * @param request 
   * @return 获取当前用户权限范围内的资源列表(ID)
   */
  public static String[] getResourceList(HttpServletRequest request)throws SystemException{
	  Vector v=(Vector)getUserBean(request).getObject("resourceList");
	    String[] str=new String[v.size()];
	    for(int i=0;i<v.size();i++){
	    	str[i]=(String)v.get(i);
	    }
	    return str;
  }

  /**
   * 根据传进来的控制点ID，判断该控制点是否在控制点列表中
   * @param request
   * @param Control_id 控制点ID
   * @return 如果该列表中存在参数ID，返回true，否则返回false
   * @throws SystemException
   */
  public static boolean isHaveControl(HttpServletRequest request,int Control_id)throws SystemException{
	  boolean bn = false;
	  Vector v=(Vector)getUserBean(request).getObject("controlList");
	  int len = v.indexOf(Integer.toString(Control_id));
	  if(len != -1){
		  bn = true;
	  }
	  return bn;
  }
  /**
   * 获取当前用户权限范围内的控制点列表
   * @param request 
   * @return 获取当前用户权限范围内的资源列表(ID)
   */
  public static String[] getControlList(HttpServletRequest request)throws SystemException{
	  	Vector v=(Vector)getUserBean(request).getObject("controlList");
	    String[] str=new String[v.size()];
	    for(int i=0;i<v.size();i++){
	    	str[i]=(String)v.get(i);
	    }
	    return str;
  }
  
  /**
   * 获取当前用户权限范围内的控制点列表
   * @param request 
   * @return 获取当前用户权限范围内的控制点列表串
   */
  public static String getControlListString(HttpServletRequest request)throws SystemException{
	  	Vector v=(Vector)getUserBean(request).getObject("controlList");
	  	String str="";
	    //String[] str=new String[v.size()];
	  	if(v!=null&&v.size()>0){
	    for(int i=0;i<v.size();i++){
	    	if(i==v.size()-1){
	    		str=str+(String)v.get(i);
	    		continue;
	    	}else{
	    	str=str+(String)v.get(i)+",";
	    	}
	    }
	  	}
	    return str;
  }
  /**
   * 取出用户所属组织
   * @param request HttpServletRequest
   * @return 用户所属组织
   * @throws SystemException
   */
  public static String getOrgName(HttpSession session) throws SystemException{
	  return getUserBean(session).getString("orgname");
  }
}
