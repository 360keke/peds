package cn.com.peds.common.dao;

import cn.com.peds.derby.DataStore;
import cn.com.peds.derby.Sql;

public class CommUtilsDao {
	/**
	 * 获取序列的next value
	 * 
	 * @return
	 */
	public static int getSeqValue(String seqName) {
		String sql = "select next value for " + seqName
				+ " as value from peds_runparams";
		Sql sqlobj = new Sql();
		int id = 0;
		try {
			sqlobj.setSql(sql);
			DataStore store = sqlobj.executeQuery();
			id = store.getInt(0, "value");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
