package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.response.TestimonialsResponse;
import com.somosmas.app.model.response.ListTestimonialsResponse;
import org.springframework.web.util.UriComponentsBuilder;


public interface ITestimonialsService {
	
	void create(TestimonialsRequest testimonialsRequest);
	
	void delete (Long id);
        
        TestimonialsResponse update(TestimonialsRequest testimonialsRequest, Long id); 
        
        ListTestimonialsResponse getTestimonials(int page, UriComponentsBuilder uriBuilder);

}
