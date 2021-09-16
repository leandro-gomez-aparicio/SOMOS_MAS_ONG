package com.somosmas.app.service;

import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.model.response.ListSlideResponse;
import com.somosmas.app.model.response.SlideResponse;
import com.somosmas.app.repository.ISlideRepository;
import com.somosmas.app.service.abstraction.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_ID_NOT_FOUND = "Slide ID: {0} not found.";

    @Autowired
    private ISlideRepository slideRepository;

    @Override
    public ListSlideResponse list() {
        List<Slide> slides = slideRepository.findAll();

        ListSlideResponse response = new ListSlideResponse();
        if (slides.isEmpty()) {
            return response;
        }

        List<SlideResponse> slidesResponses = new ArrayList<>();
        for (Slide slide : slides) {
            SlideResponse slideResponse = new SlideResponse();
            slideResponse.setImageUrl(slide.getImageUrl());
            slideResponse.setSlideOrder(slide.getSlideOrder());
            slidesResponses.add(slideResponse);
        }

        response.setSlides(slidesResponses);
        return response;
    }

    @Override
    public Optional<Slide> findById(Long id) {
        return slideRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        slideRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(MessageFormat.format(SLIDE_ID_NOT_FOUND, id)));
        slideRepository.deleteById(id);
    }
}
