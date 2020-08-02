package com.zixue.util.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @desc:数据导出，生成excel文件时的列名称集合
 */
public class ColumnTitleMap {
	// mysql用户表需要导出字段--显示名称对应集合
	private Map<String, String> columnTitleMap = new HashMap<String, String>();
	// mysql用户表需要导出字段集
	private ArrayList<String> titleKeyList = new ArrayList<String>();

	public ColumnTitleMap(Map<String, String> map) {
		init(map);
	}

	public Map<String, String> getColumnTitleMap() {
		return columnTitleMap;
	}

	public ArrayList<String> getTitleKeyList() {
		return titleKeyList;
	}

	/**
	 * 
	 * @Title init
	 * @Description TODO
	 * @param map
	 *            void
	 */
	public void init(Map<String, String> map) {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			titleKeyList.add(entry.getKey());
			columnTitleMap.put(entry.getKey(), entry.getValue());
		}
	}
}
