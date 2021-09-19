package com.somosmas.app.integration;

import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.entity.News;
import com.somosmas.app.repository.INewsRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetNewsDetailsIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private INewsRepository newsRepository;

    @Test
    @Ignore
    public void shouldReturnNewsDetails() throws Exception {
        when(newsRepository.findByIdNews(eq(1L))).thenReturn(stubNews());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", "1");

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/news/{id}"), HttpMethod.GET, entity, String.class, urlVariables);

        String expected = "{\"idNews\":1," +
                "\"name\":\"stub news\"," +
                "\"content\":null," +
                "\"image\":\"http://s3/somosmas.jpg\"," +
                "\"timestamp\":null,\"" +
                "category\":" +
                    "{\"idCategory\":1," +
                    "\"description\":\"news\"}}";

        JSONAssert.assertEquals(expected, response.getBody(), true);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private Optional<News> stubNews() {
        News news = new News();
        news.setIdNews(1L);
        news.setName("stub news");
        news.setImage("http://s3/somosmas.jpg");
        news.setCategory(stubCategory());
        return Optional.of(news);
    }

    private Category stubCategory() {
        Category category = new Category();
        category.setIdCategory(1L);
        category.setDescription("news");
        return category;
    }

}
