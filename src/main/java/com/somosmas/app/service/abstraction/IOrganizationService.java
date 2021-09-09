package com.somosmas.app.service.abstraction;

import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.UpdateOrganizationResponse;

public interface IOrganizationService {

    OrganizationResponse getOrganizationDetails();
    
    UpdateOrganizationResponse updateOrganization(UpdateOrganizationRequest organization);
    
    UpdateOrganizationResponse findById(Long id); 

}
