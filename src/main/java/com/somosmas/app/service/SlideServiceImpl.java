package com.somosmas.app.service;

import com.somosmas.app.exception.custom.SlideOrderAlreadyExistsException;
import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.model.request.CreateSlideRequest;
import com.somosmas.app.model.response.ListSlideResponse;
import com.somosmas.app.model.response.SlideDetailsResponse;
import com.somosmas.app.model.response.SlideResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.repository.ISlideRepository;
import com.somosmas.app.service.abstraction.ISlideService;
import com.somosmas.app.util.ConvertUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class SlideServiceImpl implements ISlideService {

    private static final String SLIDE_ID_NOT_FOUND = "Slide ID: {0} not found.";
    private static final String DEFAULT_CONTENT_TYPE="image/jpg";


    @Autowired
    private ISlideRepository slideRepository;
    @Autowired
    AmazonServiceImpl amazonService;
    @Autowired
    IOrganizationRepository organizationRepository;

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
    public SlideResponse create(CreateSlideRequest request) throws IOException {


        Slide slide = new Slide();
        Integer slideOrder = request.getSlideOrder() != null ? request.getSlideOrder() : slideRepository.getMaxOrder() + 1;
        slide.setSlideOrder(slideOrder);
        slide.setImageUrl(uploadEncodedImage(request));
        slide.setText(request.getDescription());
        slide.setOrganizationId(organizationRepository.findAll().get(0).getIdOrganization());
        slideRepository.save(slide);

        return ConvertUtil.convertToDto(slide);
    }
    private String uploadEncodedImage(CreateSlideRequest request){
        byte[] decoded = Base64.getDecoder().decode(request.getEncodedImage());

        String fileName = request.getDescription().isEmpty() ? null : request.getDescription();
        String contentType = request.getContentType() == null || request.getContentType().isEmpty() ? DEFAULT_CONTENT_TYPE : request.getContentType();

        InputStream inputStream = new ByteArrayInputStream(decoded);
        return amazonService.uploadFile(contentType, fileName, inputStream);
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

    @Override
    public SlideDetailsResponse findBy(Long id) {
		return ConvertUtil.convertToDtoDetails(getSlide(id));
    }

    @Override
    public SlideDetailsResponse update(CreateSlideRequest request, Long id) throws SlideOrderAlreadyExistsException {
        Slide slide=getSlide(id);
        if(slideRepository.existsBySlideOrder(request.getSlideOrder()))
            throw new SlideOrderAlreadyExistsException();
        if (request.getSlideOrder()!=null)
            slide.setSlideOrder(request.getSlideOrder());
        slide.setText(request.getDescription());
        slide.setImageUrl(uploadEncodedImage(request));
        slideRepository.save(slide);
        return ConvertUtil.convertToDtoDetails(slide);
    }

}
