package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.CreateSlideRequest;
import com.somosmas.app.model.response.ListSlideResponse;
import com.somosmas.app.model.response.SlideDetailsResponse;
import com.somosmas.app.model.response.SlideResponse;

import java.io.IOException;

public interface ISlideService {
    SlideResponse create(CreateSlideRequest request) throws IOException;
    void delete(Long id);

    ListSlideResponse list();

    SlideDetailsResponse findBy(Long id);
}
