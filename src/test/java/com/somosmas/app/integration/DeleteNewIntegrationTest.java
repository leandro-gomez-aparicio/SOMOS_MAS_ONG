package com.somosmas.app.integration;

import com.somosmas.app.model.entity.News;
import com.somosmas.app.repository.INewsRepository;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteNewIntegrationTest extends BaseIntegrationTest {
    
    @MockBean
    private INewsRepository newsRepository;
    
    @Test
    @Ignore
    public void shouldDeleteNewsWhenNewsExist() {
        when(newsRepository.findById(eq(1L))).thenReturn(stubNews());
        
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/news/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    
    @Test
    @Ignore
    public void shouldReturnBadRequestWhenUserDoesNotExist() {
        when(newsRepository.findById(eq(1L))).thenReturn(Optional.empty());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/news/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    
    private Optional<News> stubNews() {
        News news = new News();
        news.setIdNews(1L);
        news.setSoftDelete(null);
        return Optional.of(news);
    }
}
