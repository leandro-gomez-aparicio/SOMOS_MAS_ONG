package com.somosmas.app.service;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.request.SocialMediaRequest;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.SlideResponse;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.service.abstraction.IOrganizationService;
import com.somosmas.app.util.ConvertUtil;
import com.somosmas.app.util.TimestampUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    private static final String ORGANIZATION_ID_NOT_FOUND = "Organization ID: {0} not found.";

    @Autowired
    private IOrganizationRepository organizationRepository;
    @Autowired
	private SlideServiceImpl slideService;

    @Override
    public OrganizationResponse getOrganizationDetails() {
        List<Organization> organizations = organizationRepository.findAll();

        if (CollectionUtils.isEmpty(organizations)) {
            return null;
        }
        OrganizationResponse organization = ConvertUtil.convertToDto(organizations.get(0));
        List<SlideResponse> slides = slideService.findByOrganizationId(organizations.get(0).getIdOrganization());
        organization.setSlides(slides);
        return organization;
    }

    @Override
    public UpdateOrganizationResponse updateOrganization(UpdateOrganizationRequest updateOrganizationRequest) throws NoSuchElementException {
        Long idOrganization = updateOrganizationRequest.getIdOrganization();
        Organization organization = organizationRepository.findById(idOrganization)
                .orElseThrow(() -> new NoSuchElementException(MessageFormat.format(ORGANIZATION_ID_NOT_FOUND, idOrganization)));
        organization.setPhone(updateOrganizationRequest.getPhone());
        organization.setAddress(updateOrganizationRequest.getAddress());
        organization.setImage(updateOrganizationRequest.getImage());
        organization.setName(updateOrganizationRequest.getName());
        organization.setAddress(updateOrganizationRequest.getAddress());
        organization.setWelcomeText(updateOrganizationRequest.getWelcomeText());
        organization.setAboutUsText(updateOrganizationRequest.getAboutUsText());
        organization.setEmail(updateOrganizationRequest.getEmail());
        organization.setTimestamp(TimestampUtil.getCurrentTime());
        organization.setSoftDelete(false);

        SocialMediaRequest socialMediaRequest = updateOrganizationRequest.getSocialMedia();
        organization.setFacebookURL(socialMediaRequest.getFacebookURL());
        organization.setInstagramURL(socialMediaRequest.getInstagramURL());
        organization.setLinkedInURL(socialMediaRequest.getLinkedInURL());
        return ConvertUtil.convertToDtoUpdate(organizationRepository.save(organization));
    }

}
