package com.cn.v2.data.mapper;

import java.util.List;
import java.util.Map;

import com.cn.v2.data.bean.CatalogBean;

public interface CatalogMapper {
	public CatalogBean getCatalogById(int id);

	public List<CatalogBean> getCatalogsByType(Map parasMap);

	public int updateCatalog(CatalogBean catalog);

	public int delCatalogById(int id);

	public int addCatalog(CatalogBean catalog);

	public float getTotalCount(Map parasMap);//获取总条数
}
