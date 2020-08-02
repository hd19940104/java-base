package com.zixue.util.jdbc;

import java.util.List;

/**
 * 分页参数
 * @author houdo
 *
 * @param <T>
 */
public class Page<T> {
	private int pageSize = 10;// 每页数量默认10
	private int pageNo = 1;// 默认当前第一页
	private List<T> data;// 返回的数据
	private String message;// 返回的信息
	private boolean result;// 返回的状态
	private int total; // 总的数量

	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
