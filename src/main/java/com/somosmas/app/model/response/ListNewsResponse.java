package com.somosmas.app.model.response;

import java.util.List;

public class ListNewsResponse {
	
	private List<NewsResponse> news;
	private String prevPag;
    private String nextPag;
    
    public ListNewsResponse() {
        
    }

    public List<NewsResponse> getNews() {
        return news;
    }

    public void setNews(List<NewsResponse> news) {
        this.news = news;
    }

	public String getPrevPag() {
		return prevPag;
	}

	public void setPrevPag(String prevPag) {
		this.prevPag = prevPag;
	}

	public String getNextPag() {
		return nextPag;
	}

	public void setNextPag(String nextPag) {
		this.nextPag = nextPag;
	}

}
