package com.somosmas.app.util;

import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.entity.Contact;
import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.model.response.UserDetailsResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public static User convertToEntity(UserDetailsRequest request) {
        return map(request, User.class);
    }

    public static Category convertToEntity(CategoryRequest request) {
        return map(request, Category.class);
    }

    public static OrganizationResponse convertToDto(Organization organization) {
        return map(organization, OrganizationResponse.class);
    }

    public static UpdateOrganizationResponse convertToDtoUpdate(Organization organization) {
        return map(organization, UpdateOrganizationResponse.class);
    }

    public static CategoryResponse convertToDto(Category category) {
        return map(category, CategoryResponse.class);
    }

    public static Organization convertToEntity(UpdateOrganizationRequest request) {
        return map(request, Organization.class);
    }

    public static Contact convertToEntity(ContactRequest request) {
        return map(request, Contact.class);
    }

    public static Activity convertToEntity(ActivityRequest request) {
        return map(request, Activity.class);
    }

    public static List<UserDetailsResponse> convertToDto(List<User> users) {
        List<UserDetailsResponse> responses = new ArrayList<>();
        for (User user : users) {
            UserDetailsResponse response = modelMapper.map(user, UserDetailsResponse.class);
            response.setRole(user.getRole().getName());
            responses.add(response);
        }
        return responses;
    }

    public static <O, I> List<O> convertToDto(List<I> input, Class<O> destinationType) {
        return input.stream().map(i -> modelMapper.map(i, destinationType)).collect(Collectors.toList());
    }

    private static <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }

        return modelMapper.map(source, destinationType);
    }


}
