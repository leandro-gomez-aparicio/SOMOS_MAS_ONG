package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.TestimonialsRequest;

public interface ITestimonialsService {
	
	void create(TestimonialsRequest testimonialsRequest);
	
	void delete (Long id);

}
