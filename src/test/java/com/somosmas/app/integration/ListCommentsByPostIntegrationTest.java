package com.somosmas.app.integration;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.repository.ICommentsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListCommentsByPostIntegrationTest extends BaseIntegrationTest{

	@MockBean
	private ICommentsRepository commentsRepository;

	@Test
	public void shouldReturnCommentsByPost() throws Exception{
		when(commentsRepository.findBy(eq(1L))).thenReturn(stubComments());

		HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/posts/1/comments"), HttpMethod.GET, entity, String.class);

        String expected = "{\"comments\":[{\"body\":\"CommentBody\"}]}";

        JSONAssert.assertEquals(expected, response.getBody(), true);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

	}

	private List<Comment> stubComments() {
		Comment comment = new Comment();
		comment.setIdComment(1L);
		comment.setBody("CommentBody");
		List<Comment> commentList = new ArrayList<>();
		commentList.add(comment);
		return commentList;
	}

}
