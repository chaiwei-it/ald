package com.mood.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.sf.json.JSONObject;

import java.io.Serializable;
import java.util.List;

@Data
public class Pager<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer pageIndex;// 页码
	private Integer pageSize;// 每页显示记录数
	private Integer maxPages=0;// 总页数
	private Long total;// 总记录数
	private Integer startIndex;
	private List<T> data;
	private JSONObject params; //条件传递
	private String orderBy;//排序

	public Pager(Integer pageIndex, Integer pageSize, Integer total, List<T> data) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.startIndex=(pageIndex-1)*pageSize;
		this.total = total.longValue();
		this.maxPages = total == 0 ? 1 : (total + pageSize - 1) / pageSize;
		if(data!=null){
			this.data = data;
		}
	}
	
	public Pager(Integer pageIndex, Integer pageSize, Long total, List<T> data) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.startIndex=(pageIndex-1)*pageSize;
		this.total = total;
		this.maxPages = total == 0 ? 1 : (total.intValue() + pageSize - 1) / pageSize;
		if(data!=null){
			this.data = data;
		}
	}
	
	public Pager(Integer pageIndex, Integer pageSize, Integer total) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.startIndex=(pageIndex-1)*pageSize;
		this.total = total.longValue();
		this.maxPages = total == 0 ? 1 : (total + pageSize - 1) / pageSize;
		if(data!=null){
			this.data = data;
		}
	}

	public Pager(Integer pageIndex, Integer pageSize) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.startIndex=(pageIndex-1)*pageSize + 1;
		
	}
	
	public Pager() {
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		if (pageIndex == null) {
			pageIndex = 1;
		}
		this.pageIndex = pageIndex;
		if(pageSize!=null){
			this.startIndex = (pageIndex-1)*pageSize;
		}
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 20;
		}
		this.pageSize = pageSize;
		if(pageIndex!=null){
			this.startIndex = (pageIndex-1)*pageSize;
		}
	}

	public Pager<T> buildPager(Integer maxPages, Long total, List<T> data)  {
		this.maxPages = maxPages;
		this.total = total;
		this.data = data;
		return this;
	}

	public Pager<T> buildPager(Integer maxPages, Integer total, List<T> data)  {
		this.maxPages = maxPages;
		this.total = total.longValue();
		this.data = data;
		return this;
	}

//	public Integer getStartIndex() {
//		return startIndex;
//	}
//
//	public Integer getMaxPages() {
//		return maxPages;
//	}
//
//	public void setMaxPages(Integer maxPages) {
//		this.maxPages = maxPages;
//	}
//
//	public Integer getTotal() {
//		return total;
//	}
//
//	public void setTotal(Integer total) {
//		this.total = total;
//	}
//
//	public List<T> getData() {
//		return data;
//	}
//
//	public void setData(List<T> data) {
//		this.data = data;
//	}
//
	@JsonIgnore
	public JSONObject getParams() {
		return params;
	}
//
//	public void setParams(JSONObject params) {
//		this.params = params;
//	}
//
//	public void setOrderBy(String orderBy) {
//		this.orderBy = orderBy;
//	}
//
	@JsonIgnore
	public String getOrderBy() {
		return orderBy;

	}

//	public void setErrorEnum(ErrorEnum errorEnum) {
//		super.code =  errorEnum.getCode();
//		super.msg = errorEnum.getMsg();
//	}
	
}
