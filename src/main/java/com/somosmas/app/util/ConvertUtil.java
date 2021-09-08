package com.somosmas.app.util;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.UserDetailsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConvertUtil {
    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        ConvertUtil.modelMapper = modelMapper;
    }

    public static UserDetailsResponse convertToDto(User user) {
        return map(user, UserDetailsResponse.class);
    }
    public static User convertToEntity(UserDetailsRequest request){
        return map(request,User.class);
    }
    
    public static OrganizationResponse convertToDto(Organization organization) {
		return map(organization, OrganizationResponse.class);
	}
    public static <O, I> List<O> convertToDto(List<I> input, Class<O> destinationType){
        return input.stream().map(i-> modelMapper.map(i,destinationType)).collect(Collectors.toList());
    }

    private static <D> D map(Object source, Class<D> destinationType) {
        if(source == null) {
            return null;
        }

        return modelMapper.map(source, destinationType);
    }

}
