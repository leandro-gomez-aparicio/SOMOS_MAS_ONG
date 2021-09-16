package com.somosmas.app.integration;


import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.model.entity.Role;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IRoleRepository;
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
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIntegrationTest extends BaseIntegrationTest {

    @MockBean
    IRoleRepository roleRepository;

    @MockBean
    IUserRepository userRepository;

    @Test
    public void shouldRegisterUser() {
        when(roleRepository.findByName(eq(RoleType.ROLE_USER.name()))).thenReturn(stubRole());
        when(userRepository.save(any(User.class))).thenReturn(stubUser());

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

    private Role stubRole() {
        Role role = new Role();
        role.setIdRole(1L);
        role.setName(RoleType.ROLE_USER.name());
        return role;
    }

    private User stubUser() {
        User user = new User();
        user.setIdUser(1L);
        user.setEmail("paul@wayne.com");
        user.setPassword("abc1234&");
        user.setFirstName("Paul");
        user.setLastName("Wayne");
        user.setPhoto("");
        return user;
    }
}
