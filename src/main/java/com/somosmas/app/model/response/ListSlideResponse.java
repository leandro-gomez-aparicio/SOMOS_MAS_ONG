package com.somosmas.app.model.response;

import java.util.List;

public class ListSlideResponse {
	
	private List<SlideResponse> slides;

    public ListSlideResponse() {
    }

    public List<SlideResponse> getSlides() {
        return slides;
    }

    public void setSlides(List<SlideResponse> slides) {
        this.slides = slides;
    }

}
