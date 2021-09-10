package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.entity.Slide;

import java.util.Optional;

public interface ISlideService {

     Optional <Slide> findById(Long id);
     void delete(Long id);
}
