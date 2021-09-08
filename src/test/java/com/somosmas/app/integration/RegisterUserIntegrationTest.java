package com.somosmas.app.integration;


import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldRegisterUser() {
        UserDetailsRequest request = new UserDetailsRequest();
        request.setEmail("paul@wayne.com");
        request.setPassword("abc1234&");
        request.setFirstName("Paul");
        request.setLastName("Wayne");
        request.setPhoto("");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/register"), HttpMethod.POST, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assert.assertEquals("paul@wayne.com", response.getBody().getEmail());
    }

}
