package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.response.TestimonialsResponse;

public interface ITestimonialsService {
	
	void create(TestimonialsRequest testimonialsRequest);
	
	void delete (Long id);
        
        TestimonialsResponse update(TestimonialsRequest testimonialsRequest, Long id); 

}
