package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;

public interface INewsService {

    void delete(Long id);

    NewsResponse update(NewsRequest news, Long id);

}
