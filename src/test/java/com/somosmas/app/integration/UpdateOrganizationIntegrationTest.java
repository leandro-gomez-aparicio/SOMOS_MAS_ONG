package com.somosmas.app.integration;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.ErrorInfo;
import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.Role;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.SocialMediaRequest;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.model.response.UserDetailsResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.repository.IUserRepository;
import com.somosmas.app.util.ConvertUtil;
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateOrganizationIntegrationTest extends BaseIntegrationTest {

    @MockBean
    IOrganizationRepository organizationRepository;

    @MockBean
    AuthenticationManager authenticationManager;

    @MockBean
    IUserRepository userRepository;

    @Test
    public void shouldUpdateOrganization() {
        UpdateOrganizationRequest request = buildRequest();

        when(organizationRepository.findById(1L)).thenReturn(stubOrganization());
        when(organizationRepository.save(any(Organization.class))).thenReturn(stubUpdateOrganization(request));

        HttpEntity<UpdateOrganizationRequest> entity = new HttpEntity<>(request, headers);
        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken());
        headers.put("Authorization", authHeader);

        ResponseEntity<UpdateOrganizationResponse> response = restTemplate.exchange(
                createURLWithPort("/organization/public"), HttpMethod.PUT, entity, UpdateOrganizationResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(request.getIdOrganization(), response.getBody().getIdOrganization());
        Assert.assertEquals(request.getName(), response.getBody().getName());
        Assert.assertEquals(request.getImage(), response.getBody().getImage());
        Assert.assertEquals(request.getEmail(), response.getBody().getEmail());
        Assert.assertEquals(request.getWelcomeText(), response.getBody().getWelcomeText());

    }

    @Test
    public void shouldReturnBadRequestWhenOrganizationNoExist() throws Exception {
        UpdateOrganizationRequest request = buildRequest();

        HttpEntity<UpdateOrganizationRequest> entity = new HttpEntity<>(request, headers);
        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken());
        headers.put("Authorization", authHeader);

        ResponseEntity<ErrorInfo> response = restTemplate.exchange(createURLWithPort("/organization/public"),
                HttpMethod.PUT, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals(MessageFormat.format("Organization ID: {0} not found.", request.getIdOrganization()),
                response.getBody().getMessage());
    }

    @Test
    public void shouldReturnBadRequestWhenNameOrganizationIsNull() {
        UpdateOrganizationRequest request = buildRequest();
        request.setName(null);
        HttpEntity<UpdateOrganizationRequest> entity = new HttpEntity<>(request, headers);
        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken());
        headers.put("Authorization", authHeader);

        ResponseEntity<ErrorInfo> response = restTemplate.exchange(createURLWithPort("/organization/public"),
                HttpMethod.PUT, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Validation error.", response.getBody().getMessage());
    }

    private Optional<Organization> stubOrganization() {
        Organization organization = new Organization();
        organization.setIdOrganization(1L);
        organization.setName("Somos Mas");
        organization.setImage("http://s3/somosmas.jpg");
        organization.setAddress("Street 1234");
        organization.setPhone(123456);
        return Optional.of(organization);
    }

    private UpdateOrganizationRequest buildRequest() {
        UpdateOrganizationRequest organization = new UpdateOrganizationRequest();
        organization.setIdOrganization(1L);
        organization.setName("Somos Mas");
        organization.setImage("http://s3/Nueva_Imagen.jpg");
        organization.setAddress("2500115");
        organization.setPhone(28045555);
        organization.setEmail("somosmas@a.com");
        organization.setWelcomeText("Welcome Text");
        organization.setAboutUsText("AboutText");
        organization.setSocialMedia(new SocialMediaRequest());
        return organization;
    }

    private Organization stubUpdateOrganization(UpdateOrganizationRequest request) {
        return ConvertUtil.convertToEntity(request);
    }

    private String getValidJWTToken() {
        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail(eq("user@alkemy.com"))).thenReturn(stubUser());

        UserDetailsRequest loginRequest = new UserDetailsRequest();
        loginRequest.setEmail("user@alkemy.com");
        loginRequest.setPassword("abc1234&");
        HttpEntity<UserDetailsRequest> entity = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<UserDetailsResponse> response = restTemplate.exchange(createURLWithPort("/auth/login"),
                HttpMethod.POST, entity, UserDetailsResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

        return response.getBody().getToken();
    }

    private Role stubRole() {
        Role role = new Role();
        role.setIdRole(1L);
        role.setName(RoleType.ROLE_ADMIN.name());
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
