package com.zhangjiaolong.frame.common;

/** 
* @ClassName: PaginationParameter 
* @Description: 表格查询参数
* @author ZhangZhiBin 
* @date 2013-8-4 上午12:51:42 
* @param <T> 
*/
public class PaginationParameter {

	private Integer pageIndex;
	
	private Integer pageSize;
	
	private String sortField;
	
	private String sortOrder;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
}
