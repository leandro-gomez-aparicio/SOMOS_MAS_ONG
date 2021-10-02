package com.somosmas.app.integration;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.repository.ISlideRepository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
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
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetOrganizationDetailsIntegrationTest extends BaseIntegrationTest {

    @MockBean
    private IOrganizationRepository organizationRepository;
    
    @MockBean
    ISlideRepository slideRepository;

    @Test
    public void shouldReturnPublicOrganizationDetails() throws Exception {
        when(organizationRepository.findAll()).thenReturn(stubOrganization());
        when(slideRepository.findByOrganizationId(any())).thenReturn(stubSlideList());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/organization/public"), HttpMethod.GET, entity, String.class);

        String expected = "{\"name\":\"Somos Mas\"," +
                "\"image\":\"http://s3/somosmas.jpg\"," +
                "\"address\":\"Street 1234\"," +
                "\"phone\":123456," +
                "\"socialMedia\":" +
                "{\"facebookURL\":\"http://facebook.somosmas.com\"," +
                "\"linkedInURL\":null," +
                "\"instagramURL\":null}," +
                "\"slides\":[" +
                "{\"imageUrl\":\"www.photo.com\"," +
                    "\"slideOrder\":1}]}";

        JSONAssert.assertEquals(expected, response.getBody(), true);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private List<Slide> stubSlideList() {
    	List<Slide> slideList = new ArrayList<Slide>();
    	Slide slide = new Slide();
    	slide.setIdSlide(1L);
    	slide.setOrganizationId(1L);
    	slide.setText("Slide Text");
    	slide.setImageUrl("www.photo.com");
    	slide.setSlideOrder(1);
    	slideList.add(slide);
		return slideList;
	}

	private List<Organization> stubOrganization() {
        List<Organization> organizations = new ArrayList<>();
        Organization organization = new Organization();
        organization.setIdOrganization(1L);
        organization.setName("Somos Mas");
        organization.setImage("http://s3/somosmas.jpg");
        organization.setAddress("Street 1234");
        organization.setPhone(123456);
        organization.setFacebookURL("http://facebook.somosmas.com");
        organizations.add(organization);
        return organizations;
    }

}
