package com.somosmas.app.integration;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.somosmas.app.config.security.RoleType;
import org.json.JSONException;
import org.junit.Assert;
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

import com.somosmas.app.model.entity.Category;
import com.somosmas.app.repository.ICategoryRepository;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListCategoriesIntegrationTest extends BaseIntegrationTest {

	@MockBean
	ICategoryRepository categoryRepository;

	@Test
	public void shouldReturnActivities() throws JSONException {
		when(categoryRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse()).thenReturn(stubCategory());

		HttpEntity<String> entity = new HttpEntity<>(null, headers);
		List<String> authHeader = new ArrayList<>();
		authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
		headers.put("Authorization", authHeader);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/categories"),
				HttpMethod.GET,
				entity,
				String.class);
		
		String expected = "{\"categories\":" +
					"[{\"name\":\"news\"}]}";   
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), true);
	}

	private List<Category> stubCategory() {
		List<Category> categories = new ArrayList<>();
		Category category = new Category();
		category.setIdCategory(1L);
		category.setName("news");
		category.setDescription("description");
		category.setImage("img.jpg");
		categories.add(category);
		return categories;
	}

}
