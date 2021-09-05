package com.somosmas.app.service;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

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

}
