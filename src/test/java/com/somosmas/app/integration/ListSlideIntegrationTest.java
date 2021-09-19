package com.somosmas.app.integration;

import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.repository.ISlideRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Ignore;
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

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ListSlideIntegrationTest extends BaseIntegrationTest {

    @MockBean
    ISlideRepository slideRepository;

    @Test
    @Ignore
    public void shouldReturnActiveUserList() throws JSONException {
        when(slideRepository.findAll()).thenReturn(stubSlides());

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/slides"), HttpMethod.GET, entity, String.class);

        String expected = "{\"slides\":[{\"imageUrl\":\"slide\",\"slideOrder\":1}]}";

        JSONAssert.assertEquals(expected, response.getBody(), false);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private List<Slide> stubSlides() {
        List<Slide> slides = new ArrayList<>();
        Slide slide = new Slide();
        slide.setIdSlide(1L);
        slide.setImageUrl("slide");
        slide.setText("slide");
        slide.setSlideOrder(1);
        slides.add(slide);
        return slides;
    }

}
