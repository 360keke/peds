package cn.com.peds.login.dao;

import cn.com.framework.exception.SystemException;
import cn.com.peds.derby.DataStore;
import cn.com.peds.derby.Sql;

public class LoginDao {
	public static DataStore validLoginUser(String name) throws SystemException {
		String sql = "select name,password,role,lockflag,faillogin from peds_user a where a.name=?";
		System.out.println(sql);
		Sql obj = new Sql();
		obj.setSql(sql);
		obj.setString(1, name);
		return obj.executeQuery();
	}

}
