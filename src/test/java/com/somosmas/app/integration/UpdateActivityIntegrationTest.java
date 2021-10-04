package com.somosmas.app.integration;

import com.somosmas.app.config.security.RoleType;
import com.somosmas.app.exception.ErrorInfo;
import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.response.ActivityResponse;
import com.somosmas.app.repository.IActivityRepository;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateActivityIntegrationTest extends BaseIntegrationTest {

    @MockBean
    IActivityRepository activityRepository;

    @Test
    public void shouldUpdateActivity() {
        ActivityRequest request = buildRequest();

        when(activityRepository.findById(eq(1L))).thenReturn(stubActivity());
        when(activityRepository.save(any(Activity.class))).thenReturn(buildSaveResponse());

        HttpEntity<ActivityRequest> entity = new HttpEntity<>(request, headers);

        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
        headers.put("Authorization", authHeader);

        ResponseEntity<ActivityResponse> response = restTemplate.exchange(
                createURLWithPort("/activities/1"), HttpMethod.PUT, entity, ActivityResponse.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(request.getName(), response.getBody().getName());
        Assert.assertEquals(request.getImage(), response.getBody().getImage());
    }

    @Test
    public void shouldReturnBadRequestWhenActivityNoExist() {
        ActivityRequest request = buildRequest();

        HttpEntity<ActivityRequest> entity = new HttpEntity<>(request, headers);

        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
        headers.put("Authorization", authHeader);

        ResponseEntity<ErrorInfo> response = restTemplate.exchange(
                createURLWithPort("/activities/1"), HttpMethod.PUT, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Activity ID: 1 not found.", response.getBody().getMessage());

        verify(activityRepository, times(1)).findById(eq(1L));
    }

    @Test
    public void shouldReturnBadRequestWhenNameActivityIsNull() {
        ActivityRequest request = buildRequest();
        request.setName(null);

        HttpEntity<ActivityRequest> entity = new HttpEntity<>(request, headers);

        List<String> authHeader = new ArrayList<>();
        authHeader.add(getValidJWTToken(RoleType.ROLE_ADMIN.name()));
        headers.put("Authorization", authHeader);

        ResponseEntity<ErrorInfo> response = restTemplate.exchange(
                createURLWithPort("/activities/1"), HttpMethod.PUT, entity, ErrorInfo.class);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Assert.assertEquals("Validation error.", response.getBody().getMessage());

        verify(activityRepository, never()).findById(eq(1L));
    }

    private Optional<Activity> stubActivity() {
        Activity activity = new Activity();
        activity.setIdActivity(1L);
        activity.setName("news");
        activity.setImage("img.png");
        activity.setContent("my-content");
        return Optional.of(activity);
    }

    private ActivityRequest buildRequest() {
        ActivityRequest activityRequest = new ActivityRequest();
        activityRequest.setName("news");
        activityRequest.setImage("img.jpg");
        activityRequest.setContent("my-content-update");
        return activityRequest;
    }

    private Activity buildSaveResponse() {
        Activity activity = new Activity();
        activity.setIdActivity(1L);
        activity.setName("news");
        activity.setImage("img.jpg");
        activity.setContent("my-content-update");
        return activity;
    }

}
