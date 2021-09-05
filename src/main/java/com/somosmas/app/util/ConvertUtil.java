package com.somosmas.app.util;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.response.LoginResponse;
import com.somosmas.app.model.response.OrganizationResponse;

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
        return map(user, LoginResponse.class);
    }
    
    public static OrganizationResponse convertToDto(Organization organization) {
		return map(organization, OrganizationResponse.class);
	}

    private static <D> D map(Object source, Class<D> destinationType) {
        if(source == null) {
            return null;
        }

        return modelMapper.map(source, destinationType);
    }

}
