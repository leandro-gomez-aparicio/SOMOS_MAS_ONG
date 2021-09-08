package com.somosmas.app.integration;

import com.somosmas.app.model.entity.User;
import com.somosmas.app.repository.IUserRepository;
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
public class DeleteUserIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private IUserRepository userRepository;

    @Test
    @Ignore
    public void shouldDeleteUserWhenUserExist() {
        when(userRepository.findById(eq(1L))).thenReturn(stubUser());


        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    @Ignore
    public void shouldReturnBadRequestWhenUserDoesNotExist() {
        when(userRepository.findById(eq(1L))).thenReturn(Optional.empty());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users/1"), HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private Optional<User> stubUser() {
        User user = new User();
        user.setIdUser(1L);
        user.setSoftDelete(null);
        return Optional.of(user);
    }

}
