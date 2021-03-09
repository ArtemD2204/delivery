package org.artemdikov.delivery.controller.dto.converter.impl;

import org.artemdikov.delivery.controller.dto.CompanyDto;
import org.artemdikov.delivery.controller.dto.converter.Converter;
import org.artemdikov.delivery.model.Company;
import org.artemdikov.delivery.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyDtoConverter implements Converter<Company, CompanyDto> {
    private CompanyService companyService;

    @Autowired
    public CompanyDtoConverter(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Company toModel(CompanyDto dto) {
        if (dto == null)
            return null;
        if (dto.getId() == null) {
            return new Company(null, dto.getName(), dto.getRoutePart(), dto.getPickupCity(), dto.getDeliveryCity(),
                    dto.getPrice(), dto.getTimeInHours());
        }
        Company company = companyService.get(dto.getId());
        if(company != null) {
            company.setName(dto.getName());
            company.setRoutePart(dto.getRoutePart());
            company.setPickupCity(dto.getPickupCity());
            company.setDeliveryCity(dto.getDeliveryCity());
            company.setPrice(dto.getPrice());
            company.setTimeInHours(dto.getTimeInHours());
        }
        return company;
    }

    @Override
    public CompanyDto toDto(Company model) {
        if (model == null)
            return null;

        return new CompanyDto(model.getId(), model.getName(), model.getRoutePart(), model.getPickupCity(), model.getDeliveryCity(),
                model.getPrice(), model.getTimeInHours());
    }
}
