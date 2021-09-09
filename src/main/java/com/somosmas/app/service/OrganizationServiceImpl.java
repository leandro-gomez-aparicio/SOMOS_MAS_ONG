package com.somosmas.app.service;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.entity.User;
import com.somosmas.app.model.request.UpdateOrganizationRequest;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.model.response.UpdateOrganizationResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.service.abstraction.IOrganizationService;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

	private static final String ORGANIZATION_ID_NOT_FOUND = "Organization ID: {0} not found.";

	@Autowired
	private IOrganizationRepository organizationRepository;

	@Override
	public OrganizationResponse getOrganizationDetails() {
		List<Organization> organizations = organizationRepository.findAll();

		if (CollectionUtils.isEmpty(organizations)) {
			return null;
		}

		return ConvertUtil.convertToDto(organizations.get(0));
	}

	@Override
	public UpdateOrganizationResponse updateOrganization(UpdateOrganizationRequest organization) throws NoSuchElementException {
		organizationRepository.findById(organization.getIdOrganization()).orElseThrow(() -> new NoSuchElementException(
				MessageFormat.format(ORGANIZATION_ID_NOT_FOUND, organization.getIdOrganization())));
		Organization OrganizacionUpdate= organizationRepository.save(ConvertUtil.convertToEntity(organization));
		return ConvertUtil.convertToDtoUpdate(OrganizacionUpdate);
	}

	@Override
	public UpdateOrganizationResponse findById(Long id) {
		Organization organization = organizationRepository.findById(id).orElseThrow(() -> new NoSuchElementException(
				MessageFormat.format(ORGANIZATION_ID_NOT_FOUND, id)));
		return ConvertUtil.convertToDtoUpdate(organization);
	}

}
