package com.somosmas.app.service;

import com.somosmas.app.model.entity.Organization;
import com.somosmas.app.model.response.OrganizationResponse;
import com.somosmas.app.repository.IOrganizationRepository;
import com.somosmas.app.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private IOrganizationRepository organizationRepository;

    @Override
    public List<OrganizationResponse> getOrganizations() {
        List<Organization> organizations = organizationRepository.findAll();

        return organizations.stream().map(param -> ConvertUtil.convertToDto(param))
                .collect(Collectors.toList());
    }

}
