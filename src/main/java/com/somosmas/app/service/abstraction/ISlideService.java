package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.SlideOrderAlreadyExistsException;
import com.somosmas.app.model.request.CreateSlideRequest;
import com.somosmas.app.model.response.ListSlideResponse;
import com.somosmas.app.model.response.SlideDetailsResponse;
import com.somosmas.app.model.response.SlideResponse;

import java.io.IOException;
import java.util.List;

public interface ISlideService {
    SlideResponse create(CreateSlideRequest request) throws IOException;
    void delete(Long id);

    ListSlideResponse list();

    SlideDetailsResponse findBy(Long id);

    SlideDetailsResponse update(CreateSlideRequest request, Long id) throws SlideOrderAlreadyExistsException;

    public List<SlideResponse> findByOrganizationId(Long organizationId);
}
