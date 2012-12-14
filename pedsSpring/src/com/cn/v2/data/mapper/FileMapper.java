package com.cn.v2.data.mapper;

import java.util.List;
import java.util.Map;

import com.cn.v2.data.bean.FileBean;

public interface FileMapper {
	public FileBean getFileById(int id);

	public List<FileBean> search(Map params);

	public int addFile(FileBean file);

	public int updateFile(FileBean file);

	public int updateFilesBatch(FileBean file);

	public int delFileById(int id);

	public int delFiles(String ids);

	public long getFileTotalCount(Map params);

	/**
	 * 对存放在不同路径但是文件内容相同的文件进行判断 判断是不是同一个文件 根据md5 签名，md5是根据文件的md5校验码再加上文件路径再通过md5 加密生成，文件路径大写，对md5值有影响
	 * 
	 * @param signature
	 * @return
	 */
	public int fileIsExists(Map paramsMap);

	/**
	 * @param catalogId
	 * @return
	 */
	public int updateFileCatalog(int catalogId);

	public int addFileToDuplicate(FileBean bean);
}
