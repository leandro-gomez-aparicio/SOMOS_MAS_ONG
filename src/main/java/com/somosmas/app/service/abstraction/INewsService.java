package com.somosmas.app.service.abstraction;

import org.springframework.web.util.UriComponentsBuilder;

import com.somosmas.app.exception.custom.NewsAlreadyExistException;
import com.somosmas.app.model.request.CreateNewsRequest;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;
import com.somosmas.app.model.response.ListNewsResponse;

public interface INewsService {

    void delete(Long id);

    NewsResponse findBy(Long id);

    NewsResponse update(NewsRequest news, Long id);

    void create(CreateNewsRequest newsRequest) throws NewsAlreadyExistException;
    
    ListNewsResponse getNews(int page, UriComponentsBuilder uriBuilder);

}
