package com.somosmas.app.service;

import com.somosmas.app.model.entity.Testimonials;
import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.response.TestimonialsResponse;
import com.somosmas.app.repository.ITestimonialsRepository;
import com.somosmas.app.service.abstraction.ITestimonialsService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;

@Service
public class TestimonialsServiceImpl implements ITestimonialsService {

    private static final String TESTIMONIALS_ID_NOT_FOUND = "Testimonials ID: {0} not found.";

    @Autowired
    private ITestimonialsRepository testimonialsRepository;

    @Override
    public void delete(Long id) throws NoSuchElementException {
        Testimonials testimonials = getTestimonials(id);
        testimonials.setSoftDelete(true);
        testimonialsRepository.save(testimonials);
    }

    @Override
    public void create(TestimonialsRequest testimonialsRequest) {
        Testimonials testimonials = ConvertUtil.convertToEntity(testimonialsRequest);
        testimonials.setTimestamp(TimestampUtil.getCurrentTime());
        testimonials.setSoftDelete(false);
        testimonialsRepository.save(testimonials);
    }

    @Override
    public TestimonialsResponse update(TestimonialsRequest testimonialsRequest, Long id) {
        Testimonials testimonials = getTestimonials(id);

        Testimonials testimonialsUpdated = ConvertUtil.convertToEntity(testimonialsRequest);

        testimonialsUpdated.setIdTestimonials(id);
        testimonialsUpdated.setTimestamp(TimestampUtil.getCurrentTime());
        testimonialsUpdated.setSoftDelete(testimonials.isSoftDelete());
        testimonialsUpdated = testimonialsRepository.save(testimonialsUpdated);

        return ConvertUtil.convertToDto(testimonialsUpdated);
    }

    private Testimonials getTestimonials(Long id) {
        return testimonialsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(TESTIMONIALS_ID_NOT_FOUND, id)));
    }

}
