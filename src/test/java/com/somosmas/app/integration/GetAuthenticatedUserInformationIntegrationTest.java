package com.somosmas.app.integration;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.model.entity.Role;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IUserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetAuthenticatedUserInformationIntegrationTest extends BaseIntegrationTest {

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    IUserRepository userRepository;

    @Test
    public void shouldGetAuthenticatedUserInformation() {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken());
        headers.put("Authorization", authHeader);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/me"), HttpMethod.GET, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals("user@alkemy.com", response.getBody().getEmail());
        Assert.assertEquals("Bruce", response.getBody().getFirstName());
        Assert.assertEquals("Wayne", response.getBody().getLastName());
    }

    private String getValidJWTToken() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("user@alkemy.com"))).thenReturn(stubUser());

        UserDetailsRequest loginRequest = new UserDetailsRequest();
        loginRequest.setEmail("user@alkemy.com");
        loginRequest.setPassword("abc1234&");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        return response.getBody().getToken();
    }

    private Role stubRole() {
        Role role = new Role();
        role.setIdRole(1L);
        role.setName(RoleType.ROLE_USER.name());
        return role;
    }

    private Optional<User> stubUser() {
        User user = new User();
        user.setIdUser(1L);
        user.setEmail("user@alkemy.com");
        user.setPhoto("photo");
        user.setFirstName("Bruce");
        user.setLastName("Wayne");
        user.setPassword("abc1234&");
        user.setRole(stubRole());
        return Optional.of(user);
    }
}
