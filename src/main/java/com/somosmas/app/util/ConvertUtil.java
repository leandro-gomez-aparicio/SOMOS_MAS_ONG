package com.somosmas.app.util;

import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.response.LoginResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConvertUtil {
    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        ConvertUtil.modelMapper = modelMapper;
    }

    public static LoginResponse convertToDto(User user) {
        return modelMapper.map(user, LoginResponse.class);
    }
}
