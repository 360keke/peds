package cn.com.peds.fileMgr.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import cn.com.framework.exception.SystemException;
import cn.com.peds.common.JsonUtil;
import cn.com.peds.common.dao.CommUtilsDao;
import cn.com.peds.derby.DataStore;
import cn.com.peds.derby.Sql;
import cn.com.peds.fileMgr.bean.PedsFileBean;

/**
 * 文件处理的数据库交互类
 * 
 * @author yudabo
 * 
 */
public class PedsFileDao {

	/**
	 * 新增文件信息保存类
	 * 
	 * @param file
	 */
	public static int saveFile(PedsFileBean file) {
		String sql = "insert into peds_files(id,path,filename,suffix,length,modifytime,createtime,"
				+ "updatetime,canread,canwrite,canexecute,ishidden,signature,encryptPwd,isencrypt,encryptFileName) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Sql sqlObj = new Sql();
		sqlObj.setSql(sql);
		int id = CommUtilsDao.getSeqValue("seq_peds_file");
		try {
			sqlObj.setInt(1, id);
			sqlObj.setString(2, file.getPath());
			sqlObj.setString(3, file.getFileName());
			sqlObj.setString(4, file.getSuffix());
			sqlObj.setFloat(5, file.getLength());
			sqlObj.setString(6, file.getLastModify());
			sqlObj.setString(7, file.getCreateTime());
			sqlObj.setString(8, file.getUpdateTime());
			sqlObj.setInt(9, file.isCanRead());
			sqlObj.setInt(10, file.isCanWrite());
			sqlObj.setInt(11, file.isCanExecute());
			sqlObj.setInt(12, file.isHidden());
			sqlObj.setString(13, file.getSignature());
			sqlObj.setString(14, file.getEncryptPwd());
			sqlObj.setString(15, file.getIsencrypt());
			sqlObj.setString(16, file.getEncryptFileName());
			sqlObj.executeUpdate(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	public static void updateFile(PedsFileBean file) {
		String sql = "update peds_files set path=?,filename=?,suffix=?," + "length=?,modifytime=?,createtime=?,"
				+ "updatetime=?,canread=?,canwrite=?,canexecute=?,ishidden=?," + "signature=?,encryptPwd=?,isencrypt=?,encryptFileName=? where  signature=?";
		Sql sqlObj = new Sql();
		sqlObj.setSql(sql);
		try {
			sqlObj.setString(1, file.getPath());
			sqlObj.setString(2, file.getFileName());
			sqlObj.setString(3, file.getSuffix());
			sqlObj.setFloat(4, file.getLength());
			sqlObj.setString(5, file.getLastModify());
			sqlObj.setString(6, file.getCreateTime());
			sqlObj.setString(7, file.getUpdateTime());
			sqlObj.setInt(8, file.isCanRead());
			sqlObj.setInt(9, file.isCanWrite());
			sqlObj.setInt(10, file.isCanExecute());
			sqlObj.setInt(11, file.isHidden());
			sqlObj.setString(12, file.getSignature());
			sqlObj.setString(13, file.getEncryptPwd());
			sqlObj.setString(14, file.getIsencrypt());
			sqlObj.setString(15, file.getEncryptFileName());
			sqlObj.setString(16, file.getSignature());
			sqlObj.executeUpdate(null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据文件签名信息来判断文件是否已经存在了
	 * 
	 * @param signature
	 * @return
	 */
	public static boolean fileIsExists(String signature) {
		boolean flag = false;
		String sql = "select 1 from peds_files where signature='" + signature + "'";
		Sql sqlobj = new Sql();
		sqlobj.setSql(sql);
		DataStore ds = null;
		try {
			ds = sqlobj.executeQuery();
			if (ds.rowCount() > 0) {
				flag = true;
			}
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 要查询全部的文件信息是id=-1
	 * 
	 * @param id
	 * @return
	 */
	public static JSONArray getDefaultFiles(int id) {
		String sql = "select * from peds_files ";
		Sql sqlobj = new Sql();
		if (id != -1) {
			sql += " where id =" + id;
		}
		sql += " order by createtime";
		sqlobj.setSql(sql);
		DataStore ds = null;
		try {
			ds = sqlobj.executeQuery();
		} catch (SystemException e) {
			e.printStackTrace();
		}
		return JsonUtil.DataSoreToJSONs(ds);
	}

	public static void main(String[] args) {
		// DataStore ds= PedsFileDao.getDefaultFiles(-1);
		// System.out.println(JsonUtil.DataSoreToJSONs(ds));
	}
}
