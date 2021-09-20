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
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

        response.setSlides(sortBySlideOrder(slidesResponses));
        return response;
    }

    @Override
    public void delete(Long id) {
        getSlide(id);
        slideRepository.deleteById(id);
    }

    private Slide getSlide(Long id) {
        return slideRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(SLIDE_ID_NOT_FOUND, id)));
    }

    private List<SlideResponse> sortBySlideOrder(List<SlideResponse> slidesResponses) {
        return slidesResponses.stream()
                .sorted(Comparator.comparing(SlideResponse::getSlideOrder))
                .collect(Collectors.toList());
    }
}
