package com.somosmas.app.integration;


import com.somosmas.app.exception.custom.SendEmailException;
import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.util.mail.SendEmail;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterUserIntegrationTest extends BaseIntegrationTest {

    @MockBean
    IOrganizationRepository organizationRepository;

    @MockBean
    SendEmail sendEmail;

    @Test
    public void shouldRegisterUser() throws SendEmailException {
        when(userRepository.save(any(User.class))).thenReturn(stubUser());
        when(organizationRepository.findAll()).thenReturn(stubOrganization());
        doNothing().when(sendEmail).execute(any());

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

    private List<Organization> stubOrganization() {
        Organization organization = new Organization();
        organization.setIdOrganization(1L);
        organization.setEmail("somosmas@org.com");
        organization.setImage("image_logo");
        organization.setName("Somos Mas ONG");
        organization.setWelcomeText("Welcome to Somos Mas. This is the integration test message.");

        List<Organization> organizations = new ArrayList<>();
        organizations.add(organization);
        return organizations;
    }
}
