package com.somosmas.app.service;

import com.somosmas.app.exception.custom.NewsAlreadyExistException;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.entity.News;
import com.somosmas.app.model.request.CreateNewsRequest;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.response.NewsResponse;
import com.somosmas.app.repository.ICategoryRepository;
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
    
    private static final String CATEGORY_NEWS_NOT_FOUND = "Category: {0} not found";

    @Autowired
    private INewsRepository newsRepository;
    
    @Autowired
    private ICategoryRepository categoryRepository;

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

	@Override
	public void create(CreateNewsRequest newsRequest) throws NewsAlreadyExistException, NoSuchElementException {
    	if(newsRepository.existsByName(newsRequest.getName())) {
    		throw new NewsAlreadyExistException(newsRequest.getName());
    	}
    	
    	News news = ConvertUtil.convertToEntity(newsRequest);
    	news.setSoftDelete(false);
    	news.setTimestamp(TimestampUtil.getCurrentTime());
    	
    	Category category = categoryRepository.findOneByName("news")
    			.orElseThrow(() -> new NoSuchElementException(MessageFormat.format(CATEGORY_NEWS_NOT_FOUND, "news")));
    	news.setCategory(category);
    	
    	newsRepository.save(news);
		
	}

}
