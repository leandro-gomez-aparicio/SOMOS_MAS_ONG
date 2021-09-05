package com.somosmas.app.service;

import java.util.List;

import com.somosmas.app.model.response.OrganizationResponse;

public interface IOrganizationService {
	
	public List<OrganizationResponse> getOrganizations();

}
