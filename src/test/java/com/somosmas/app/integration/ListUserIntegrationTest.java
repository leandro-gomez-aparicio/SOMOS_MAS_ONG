package com.somosmas.app.integration;

import com.somosmas.app.model.entity.User;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListUserIntegrationTest extends BaseIntegrationTest {

    @Test
    @Ignore
    public void shouldReturnActiveUserList() throws JSONException {
        when(userRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse()).thenReturn(stubUser());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/users"), HttpMethod.GET, entity, String.class);

        String expected = "[{\"email\":\"user@mail.com\",\"firstName\":\"user\", "
                + "\"lastName\":\"user\", \"photo\":photo}]";

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        JSONAssert.assertEquals(expected, response.getBody(), true);
    }

    private List<User> stubUser() {
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setIdUser(1L);
        user.setEmail("user@mail.com");
        user.setPhoto("photo");
        user.setFirstName("user");
        user.setLastName("user");
        users.add(user);
        return users;
    }

}
