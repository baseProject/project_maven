package com.zhangjiaolong.frame.common;

import java.util.List;

public class PageView<T> {
	
	private Class<T> clazz;
	
	private long rowSize;
	
	private List<T> data;

	private int pageIndex = 1;
	
	private int pageSize = GlobalConfigure.DEFAULT_PAGE_SIZE;
	
	private long start;
	
	private long end;
	
	public PageView() {
    }
	
	public PageView(Class<T> clazz) {
		this.clazz = clazz;
    }
	
	public void generatePagination(PaginationParameter pagination){
		Integer pageIndex = pagination.getPageIndex();
		Integer pageSize = pagination.getPageSize();
		if(pageIndex == null || pageIndex <= 0) {
			pageIndex = 1;
		}
		if(pageSize == null || pageSize <= 0) {
			pageSize = GlobalConfigure.DEFAULT_PAGE_SIZE;
		}
		if(GlobalConfigure.MAX_PAGE_SIZE < pageSize.intValue()){
			pageSize = GlobalConfigure.MAX_PAGE_SIZE;
		}
		long first = (pageIndex - 1) * pageSize;
		this.pageIndex = pageIndex.intValue();
		this.pageSize = pageSize.intValue();
		if(first < 0){
			first = 0;
		}
		this.start = first;
		this.end = pageIndex*pageSize;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PageView [clazz=").append(clazz).append(", rowSize=")
				.append(rowSize).append(", data=").append(data)
				.append(", pageIndex=").append(pageIndex).append(", pageSize=")
				.append(pageSize).append(", start=").append(start)
				.append(", end=").append(end).append("]");
		return builder.toString();
	}
	
	public long getRowSize() {
    	return rowSize;
    }

	public void setRowSize(long rowSize) {
    	this.rowSize = rowSize;
    }

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getPageIndex() {
    	return pageIndex;
    }

	public void setPageIndex(int pageIndex) {
    	this.pageIndex = pageIndex;
    }

	public int getPageSize() {
    	return pageSize;
    }

	public void setPageSize(int pageSize) {
    	this.pageSize = pageSize;
    }

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}

}
