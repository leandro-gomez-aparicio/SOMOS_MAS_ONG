package com.somosmas.app.service;

import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.repository.ISlideRepository;
import com.somosmas.app.service.abstraction.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_ID_NOT_FOUND = "Slide ID: {0} not found.";

    @Autowired
    private ISlideRepository slideRepository;

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
