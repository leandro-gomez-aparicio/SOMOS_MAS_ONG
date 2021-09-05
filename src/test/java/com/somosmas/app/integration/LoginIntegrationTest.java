package com.somosmas.app.integration;

import com.somosmas.app.exception.ErrorInfo;
import com.somosmas.app.model.request.LoginRequest;
import com.somosmas.app.model.response.LoginResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTest {

    @LocalServerPort
    private int port;

    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected HttpHeaders headers = new HttpHeaders();

    @Test
    public void shouldReturnBadRequestWhenEmailDoesNotHaveRightFormat() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("abc");
        loginRequest.setPassword("1234");
        HttpEntity<LoginRequest> entity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<ErrorInfo> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Email should be valid.", response.getBody().getMessage());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
