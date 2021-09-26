package com.somosmas.app.integration;

import com.somosmas.app.model.entity.Role;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IUserRepository;
import org.junit.Assert;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public abstract class BaseIntegrationTest {

    @MockBean
    protected AuthenticationManager authenticationManager;
    @MockBean
    protected IUserRepository userRepository;
    protected TestRestTemplate restTemplate = new TestRestTemplate();
    protected HttpHeaders headers = new HttpHeaders();
    @LocalServerPort
    private int port;

    protected String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    protected String getValidJWTToken(String role) {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("user@alkemy.com"))).thenReturn(stubUser(role));

        UserDetailsRequest loginRequest = new UserDetailsRequest();
        loginRequest.setEmail("user@alkemy.com");
        loginRequest.setPassword("abc1234&");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        return response.getBody().getToken();
    }

    private Role stubRole(String name) {
        Role role = new Role();
        role.setIdRole(1L);
        role.setName(name);
        return role;
    }

    private Optional<User> stubUser(String role) {
        User user = new User();
        user.setIdUser(1L);
        user.setEmail("user@alkemy.com");
        user.setPhoto("photo");
        user.setFirstName("Bruce");
        user.setLastName("Wayne");
        user.setPassword("abc1234&");
        user.setRole(stubRole(role));
        return Optional.of(user);
    }
}
