package com.somosmas.app.integration;

import com.somosmas.app.config.security.RoleType;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAuthenticatedUserInformationIntegrationTest extends BaseIntegrationTest {

    @Test
    public void shouldGetAuthenticatedUserInformation() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken(RoleType.ROLE_USER.name()));
        headers.put("Authorization", authHeader);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/me"), HttpMethod.GET, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("user@alkemy.com", response.getBody().getEmail());
        Assert.assertEquals("Bruce", response.getBody().getFirstName());
        Assert.assertEquals("Wayne", response.getBody().getLastName());
    }

}
