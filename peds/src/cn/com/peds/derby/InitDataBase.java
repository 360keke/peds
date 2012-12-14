package cn.com.peds.derby;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.com.framework.exception.DataNotFoundException;
import cn.com.framework.exception.SystemException;

public class InitDataBase {
	public static Map generCommand() {
		Map<String, String> commands = new HashMap<String, String>();
		String key = "";
		String sql = "";
		// 创建runparams 表 key 一类和一个参数标示，一个参数时key=code ，列表 code 存放值，value 存放显示值
		key = "create_peds_runparams";
		sql = "create table peds_runparams(key varchar(50),code varchar(50),value varchar(500),rsrv1 varchar(200),rsrv2 varchar(200))";
		// commands.put(key, sql);
		// 创建文件属性表
		key = "create_peds_files";
		sql = "drop table peds_files;create table peds_files(id int primary key,path varchar(500),filename varchar(500),suffix varchar(50),"
				+ " length float,modifytime date,canread int,canwrite int ,canexecute int ,ishidden int ,"
				+ "signature varchar(500),createtime varchar(50),updatetime varchar(50),"
				+ "rsrv1 varchar(100),rsrv2 varchar(100) ,encryptpwd varchar(500),isencrypt varchar(1),encryptFileName varchar(500))";
		commands.put(key, sql);
		return commands;
	}

	public static void main(String[] args) throws SQLException,
			DataNotFoundException, SystemException {
		// Statement st = DerbyManager.open().createStatement();
		Sql obj = new Sql();
		Map commMap = InitDataBase.generCommand();
		for (Iterator it = commMap.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String[] sqls = ((String) commMap.get(key)).split(";");
			for (int i = 0; i < sqls.length; i++) {
				obj.setSql(sqls[i]);
				obj.executeUpdate(null);
				
			}
		}
		DerbyManager.closeDataBase();
	}
}
