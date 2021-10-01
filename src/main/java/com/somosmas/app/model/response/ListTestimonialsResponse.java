package com.somosmas.app.model.response;

import java.util.List;

public class ListTestimonialsResponse {

    private List<TestimonialsResponse> testimonials;
    private String prevPag;
    private String nextPag;
    
    public ListTestimonialsResponse() {
    
    }

    public List<TestimonialsResponse> getNews() {
        return testimonials;
    }

    public void setNews(List<TestimonialsResponse> news) {
        this.testimonials = news;
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
