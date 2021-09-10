package com.somosmas.app.integration;

import com.somosmas.app.exception.ErrorInfo;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginIntegrationTest extends BaseIntegrationTest {

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    IUserRepository userRepository;

    @Test
    @Ignore
    public void shouldReturnBadRequestWhenEmailDoesNotHaveRightFormat() {
        UserDetailsRequest loginRequest = new UserDetailsRequest();
        loginRequest.setEmail("abc");
        loginRequest.setPassword("1234");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(loginRequest, headers);

       ResponseEntity<ErrorInfo> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Email should be valid.", response.getBody().getMessage());
    }

    @Test
    public void shouldLoginSuccessfully() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("user@alkemy.com"))).thenReturn(stubUser());

        UserDetailsRequest loginRequest = new UserDetailsRequest();
        loginRequest.setEmail("user@alkemy.com");
       loginRequest.setPassword("abc1234&");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(
                createURLWithPort("/auth/login"), HttpMethod.POST, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertNotNull(response.getBody().getToken());
    }

    private Optional<User> stubUser() {
        User user = new User();
        user.setIdUser(1L);
        user.setEmail("user@alkemy.com");
        user.setPhoto("photo");
       user.setFirstName("Bruce");
        user.setLastName("Wayne");
        user.setPassword("abc1234&");
        return Optional.of(user);
    }

}
