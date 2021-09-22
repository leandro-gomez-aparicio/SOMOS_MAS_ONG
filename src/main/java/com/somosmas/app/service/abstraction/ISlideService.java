package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.response.ListSlideResponse;
import com.somosmas.app.model.response.SlideDetailsResponse;

public interface ISlideService {

    void delete(Long id);

    ListSlideResponse list();
    
    SlideDetailsResponse findBy(Long id);
}
