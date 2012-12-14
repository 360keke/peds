package cn.com.peds.common.dao;

import java.util.ArrayList;
import java.util.List;

import cn.com.framework.exception.SystemException;
import cn.com.peds.common.bean.SysParam;
import cn.com.peds.derby.DataStore;
import cn.com.peds.derby.Sql;

/**
 * 系统参数操作类
 */
public class SysParamDao {
	private static List sysparamList = null;// 获得参数列表，加载到内存方便使用

	static {
		try {
			loadSysParamList();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 加载参数列表
	 */
	public static void loadSysParamList() throws SystemException {
		sysparamList = getSysParamList();
	}

	/**
	 * 根据参数名称获得参数对象
	 * 
	 * @param paramName
	 *            String 参数名称
	 * @return SysParam 返回参数对象，如果没有则返回null
	 */
	public static SysParam getSysParam(String paramName) throws SystemException {
		SysParam sysParam = new SysParam();
		sysParam.setCode(paramName);
		if (sysparamList != null) {
			int index = sysparamList.indexOf(sysParam);
			if (index >= 0) {
				sysParam = (SysParam) sysparamList.get(index);
			}
		}
		return sysParam;
	}

	/**
	 * 根据参数名称获得参数值
	 * 
	 * @param paramName
	 *            String 参数名称
	 * @return String 返回参数值
	 */
	public static String getSysParamValue(String paramName) {
		SysParam sysParam = new SysParam();
		try {
			sysParam = getSysParam(paramName);
		} catch (SystemException e) {

		}
		return sysParam.getValue();
	}

	/**
	 * 获得所有参数列表
	 * 
	 * @return List 返回参数列表
	 * @throws com.beststar.framework.common.exception.SystemException
	 */
	public static List getSysParamList() throws SystemException {
		String sql = "select code,value,description from peds_runparams";
		Sql sqlobj = new Sql();
		sqlobj.setSql(sql);
		DataStore ds = null;
		List list = new ArrayList();
		try {
			ds = sqlobj.executeQuery();

			for (int i = 0; i < ds.rowCount(); i++) {
				SysParam sysParam = new SysParam();
				SetSysParam(ds, i, sysParam);
				list.add(sysParam);
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void SetSysParam(DataStore ds, int i, SysParam sysParam)
			throws SystemException {
		sysParam.setCode(ds.getString(i, "code"));
		sysParam.setValue(ds.getString(i, "value"));
		sysParam.setDesc(ds.getString(i, "description"));
	}

	/**
	 * 返回指定参数的值(暂未管状态的情况) add by tigerliang
	 * 
	 * @param paramName
	 * @return
	 * @throws SystemException
	 */
	public static String getRunParam(String paramName) {
		String sql = "select paramname,paramtext,paramvalue,paramstaus,paramdesc,paramtemp from venus_sys_runparam where paramName = ?";
		Sql sqlobj = new Sql();
		String paramValue = "";
		try {
			sqlobj.setSql(sql);
			sqlobj.setString(1, paramName);
			DataStore ds = sqlobj.executeQuery();
			if (ds.rowCount() > 0) {
				paramValue = ds.getString(0, "paramvalue");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramValue;
	}

	/**
	 * 返回指定参数的行数据 add by tigerliang
	 * 
	 * @param paramName
	 * @return
	 * @throws SystemException
	 * @throws SystemException
	 */
	public static DataStore getRunParamRow(String paramName) {
		String sql = "select paramname,paramtext,paramvalue,paramstaus,paramdesc,paramtemp from venus_sys_runparam where paramName = ?";
		Sql sqlobj = new Sql();
		DataStore store = new DataStore();
		try {
			sqlobj.setSql(sql);
			store = sqlobj.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return store;
	}

	/**
	 * 返回指定系统资源对应的参数名称 add by zhanggs
	 * 
	 * @param resId
	 * @return
	 * @throws SystemException
	 */
	public static String getParamName(String resId) {
		String paramName = "1087";
		String sql = "select t.unix_systype from venus_auth_sys_res t where t.id = ?";
		try {
			Sql sqlobj = new Sql();
			sqlobj.setSql(sql);
			sqlobj.setString(1, resId);
			DataStore ds = sqlobj.executeQuery();
			if (ds.rowCount() > 0) {
				paramName = paramName + ds.getString(0, "unix_systype");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paramName;
	}
}
