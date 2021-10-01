package com.somosmas.app.service;

import com.somosmas.app.model.entity.Testimonials;
import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.response.ListTestimonialsResponse;
import com.somosmas.app.model.response.TestimonialsResponse;
import com.somosmas.app.repository.ITestimonialsRepository;
import com.somosmas.app.service.abstraction.ITestimonialsService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TestimonialsServiceImpl implements ITestimonialsService {

    private static final String TESTIMONIALS_ID_NOT_FOUND = "Testimonials ID: {0} not found.";
    private static final String TESTIMONIALS_PAGE_NOT_FOUND = "Page {0} not found.";

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

    @Override
    public ListTestimonialsResponse getTestimonials(int pageReq, UriComponentsBuilder uriBuilder) {
        
        Pageable page = PageRequest.of(pageReq, 10);
        Page<Testimonials> pageTestimonials = testimonialsRepository.findBySoftDeleteIsNullOrSoftDeleteIsFalse(page);
        
        if (pageReq > pageTestimonials.getTotalPages()-1) {
        	throw new NoSuchElementException(MessageFormat.format(TESTIMONIALS_PAGE_NOT_FOUND, pageReq));
        }
        
        List<TestimonialsResponse> testimonialsResponse = new ArrayList<>();
        
        pageTestimonials.getContent().forEach(testimonials -> {
            testimonialsResponse.add(ConvertUtil.convertToDto(testimonials));
        });
        
        ListTestimonialsResponse response = new ListTestimonialsResponse();
        
        response.setNews(testimonialsResponse);
        
        uriBuilder.path("/testimonials/");
        String nextPag = constructNextPageUri(uriBuilder, pageReq);
        String prevPag = constructPrevPageUri(uriBuilder, pageReq);      
        if (page.getPageNumber() == 0) {
        	prevPag = null;
        }
        if (page.getPageNumber() == pageTestimonials.getTotalPages()-1) {
        	nextPag = null;
        }
        
        response.setPrevPag(prevPag);
        response.setNextPag(nextPag);

        return response;
    }
    
    String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page + 1).build().encode().toUriString();
    }
    
    String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page) {
        return uriBuilder.replaceQueryParam("page", page - 1).build().encode().toUriString();
    }

}
