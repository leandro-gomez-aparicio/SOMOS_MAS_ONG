package com.somosmas.app.service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somosmas.app.model.entity.Testimonials;
import com.somosmas.app.repository.ITestimonialsRepository;
import com.somosmas.app.service.abstraction.ITestimonialsService;

@Service
public class TestimonialsServiceImpl implements ITestimonialsService {

	private static final String TESTIMONIALS_ID_NOT_FOUND = "Testimonials ID: {0} not found.";

	@Autowired
	private ITestimonialsRepository testimonialsRepository;

	@Override
	public void delete(Long id) throws NoSuchElementException {
		Testimonials testimonials = testimonialsRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException(MessageFormat.format(TESTIMONIALS_ID_NOT_FOUND, id)));
		testimonials.setSoftDelete(true);
		testimonialsRepository.save(testimonials);
	}

}
