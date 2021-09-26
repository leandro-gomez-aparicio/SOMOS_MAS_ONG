package com.somosmas.app.integration;

import com.somosmas.app.model.entity.Category;
import com.somosmas.app.repository.ICategoryRepository;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeleteCategoryIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private ICategoryRepository categoryRepository;

    @Test
    @Ignore
    public void shouldDeleteUserWhenUserExist() {
        when(categoryRepository.findById(eq(1L))).thenReturn(stubCategory());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/categories/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Ignore
    public void shouldReturnBadRequestWhenUserDoesNotExist() {
        when(categoryRepository.findById(eq(1L))).thenReturn(Optional.empty());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/categories/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private Optional<Category> stubCategory() {
        Category category = new Category();
        category.setIdCategory(1L);
        category.setSoftDelete(null);
        return Optional.of(category);
    }

}
