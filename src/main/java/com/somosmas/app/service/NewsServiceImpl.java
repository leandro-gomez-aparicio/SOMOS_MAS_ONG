package com.somosmas.app.service;

import com.somosmas.app.model.entity.News;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;
import com.somosmas.app.repository.INewsRepository;
import com.somosmas.app.service.abstraction.INewsService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

@Service
public class NewsServiceImpl implements INewsService {

    private static final String NEWS_ID_NOT_FOUND = "News ID: {0} not found.";

    @Autowired
    private INewsRepository newsRepository;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(NEWS_ID_NOT_FOUND, id)));
        news.setSoftDelete(true);
        newsRepository.save(news);
    }

    @Override
    public NewsResponse update(NewsRequest newsRequest, Long id) {
        newsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(NEWS_ID_NOT_FOUND, id)));
        News news = ConvertUtil.convertToEntity(newsRequest);
        news.setIdNews(id);
        news.setTimestamp((TimestampUtil.getCurrentTime()));
        News news2 = newsRepository.save(news);
        return ConvertUtil.convertToDto(news2);
    }

}
