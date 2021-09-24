package com.somosmas.app.util;

import com.somosmas.app.model.entity.Activity;
import com.somosmas.app.model.entity.Category;
import com.somosmas.app.model.entity.Contact;
import com.somosmas.app.model.entity.Member;
import com.somosmas.app.model.entity.News;
import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.Slide;
import com.somosmas.app.model.entity.Testimonials;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.entity.Comment;
import com.somosmas.app.model.request.ActivityRequest;
import com.somosmas.app.model.request.CategoryRequest;
import com.somosmas.app.model.request.ContactRequest;
import com.somosmas.app.model.request.CreateNewsRequest;
import com.somosmas.app.model.request.MemberRequest;
import com.somosmas.app.model.request.NewsRequest;
import com.somosmas.app.model.request.TestimonialsRequest;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.request.UserDetailsRequest;
import com.somosmas.app.model.response.NewsResponse;
import com.somosmas.app.model.response.ActivityResponse;
import com.somosmas.app.model.response.CategoryResponse;
import com.somosmas.app.model.response.CommentResponse;
import com.somosmas.app.model.response.MemberResponse;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.SlideDetailsResponse;
import com.somosmas.app.model.response.SlideResponse;
import com.somosmas.app.model.response.SocialMediaResponse;
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
    
    public static Member convertToEntity(MemberRequest request) {
        return map(request, Member.class);
    }

    public static OrganizationResponse convertToDto(Organization organization) {
        OrganizationResponse organizationResponse = map(organization, OrganizationResponse.class);
        organizationResponse.setSocialMedia(
                new SocialMediaResponse(organization.getFacebookURL(),
                        organization.getInstagramURL(),
                        organization.getLinkedInURL()));
        return organizationResponse;
    }

    public static UpdateOrganizationResponse convertToDtoUpdate(Organization organization) {
        UpdateOrganizationResponse updateOrganizationResponse = map(organization, UpdateOrganizationResponse.class);
        updateOrganizationResponse.setSocialMedia(
                new SocialMediaResponse(
                        organization.getFacebookURL(),
                        organization.getInstagramURL(),
                        organization.getLinkedInURL()));
        return updateOrganizationResponse;
    }

    public static CategoryResponse convertToDto(Category category) {
        return map(category, CategoryResponse.class);
    }
    
    public static CommentResponse convertToDto(Comment comment) {
        return map(comment, CommentResponse.class);
    }
    
    public static NewsResponse convertToDto(News news) {
        return map(news, NewsResponse.class);
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
    
    public static SlideResponse convertToDto(Slide slide) {
        return map(slide, SlideResponse.class);
    }

    public static MemberResponse convertToDto(Member request) {
        return map(request, MemberResponse.class);
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
	
	public static News convertToEntity(NewsRequest request) {
		return map(request,News.class);
	}
	
	public static News convertToEntity(CreateNewsRequest request) {
		return map(request,News.class);
	}
	
	public static ActivityResponse convertToDto(Activity activity) {
        return map(activity, ActivityResponse.class);
    }
	
	public static Testimonials convertToEntity(TestimonialsRequest request) {
        return map(request, Testimonials.class);
    }
	public static SlideDetailsResponse convertToDtoDetails(Slide slide) {
        return map(slide, SlideDetailsResponse.class);
	}
	
    public static <O, I> List<O> convertToDto(List<I> input, Class<O> destinationType){
        return input.stream().map(i-> modelMapper.map(i,destinationType)).collect(Collectors.toList());
    }
 
    private static <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }

        return modelMapper.map(source, destinationType);
    }
}
