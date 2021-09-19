package com.somosmas.app.service.abstraction;

import com.somosmas.app.exception.custom.NewsAlreadyExistException;
import com.somosmas.app.model.request.CreateNewsRequest;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;

public interface INewsService {

    void delete(Long id);

    NewsResponse update(NewsRequest news, Long id);
    
    void create(CreateNewsRequest newsRequest) throws NewsAlreadyExistException;

}
