package com.somosmas.app.integration;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.response.CategoryResponse;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.ErrorInfo;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.repository.ICategoryRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UpdateCategoryIntegrationTest extends BaseIntegrationTest {

	@MockBean
	ICategoryRepository categoryRepository;

	@Test
	public void shouldUpdateCategory() {
		CategoryRequest request = buildRequest();

		when(categoryRepository.findById(eq(1L))).thenReturn(stubCategory());
		when(categoryRepository.save(any(Category.class))).thenReturn(buildSaveResponse());

		HttpEntity<CategoryRequest> entity = new HttpEntity<>(request, headers);

		List<String> authHeader = new ArrayList<>();
		authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
		headers.put("Authorization", authHeader);

		ResponseEntity<CategoryResponse> response = restTemplate.exchange(
				createURLWithPort("/categories/1"), HttpMethod.PUT, entity, CategoryResponse.class);

		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assert.assertEquals(request.getName(), response.getBody().getName());
		Assert.assertEquals(request.getDescription(), response.getBody().getDescription());
		Assert.assertEquals(request.getImage(), response.getBody().getImage());
	}

	@Test
	public void shouldReturnBadRequestWhenCategoryNoExist() {
		CategoryRequest request = buildRequest();

		HttpEntity<CategoryRequest> entity = new HttpEntity<>(request, headers);

		List<String> authHeader = new ArrayList<>();
		authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
		headers.put("Authorization", authHeader);

		ResponseEntity<ErrorInfo> response = restTemplate.exchange(
				createURLWithPort("/categories/1"), HttpMethod.PUT, entity, ErrorInfo.class);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Category ID: 1 not found.", response.getBody().getMessage());

		verify(categoryRepository, times(1)).findById(eq(1L));
	}

	@Test
	public void shouldReturnBadRequestWhenNameCategoryIsNull() {
		CategoryRequest request = buildRequest();
		request.setName(null);

		HttpEntity<CategoryRequest> entity = new HttpEntity<>(request, headers);

		List<String> authHeader = new ArrayList<>();
		authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
		headers.put("Authorization", authHeader);

		ResponseEntity<ErrorInfo> response = restTemplate.exchange(
				createURLWithPort("/categories/1"), HttpMethod.PUT, entity, ErrorInfo.class);

		Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		Assert.assertEquals("Validation error.", response.getBody().getMessage());

		verify(categoryRepository, never()).findById(eq(1L));
	}

	private Optional<Category> stubCategory() {
		Category category = new Category();
		category.setIdCategory(1L);
		category.setName("news");
		category.setDescription("description");
		category.setImage("img.png");
		category.setNews(null);
		return Optional.of(category);
	}

	private CategoryRequest buildRequest() {
		CategoryRequest request = new CategoryRequest();
		request.setDescription("description");
		request.setName("news");
		request.setImage("img.jpg");
		return request;
	}

	private Category buildSaveResponse() {
		Category category = new Category();
		category.setName("news");
		category.setIdCategory(1L);
		category.setDescription("description");
		category.setImage("img.jpg");
		return category;
	}
	
}
