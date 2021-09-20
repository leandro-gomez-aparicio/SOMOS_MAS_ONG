package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.response.ListSlideResponse;

public interface ISlideService {

    void delete(Long id);

    ListSlideResponse list();
}
