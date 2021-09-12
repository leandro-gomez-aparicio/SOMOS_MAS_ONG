package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.entity.News;
import com.somosmas.app.repository.INewsRepository;
import java.text.MessageFormat;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
