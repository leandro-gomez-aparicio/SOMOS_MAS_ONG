package com.somosmas.app.model.response;

import java.util.List;

public class ListCategoryResponse {

	private String name;
	private String prevPage;
	private String nextPage;
	
	public ListCategoryResponse() {
		super();
	}
	public ListCategoryResponse(String name, String prevPage, String nextPage) {
		super();
		this.name = name;
		this.prevPage = prevPage;
		this.nextPage = nextPage;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(String prevPage) {
		this.prevPage = prevPage;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

}
