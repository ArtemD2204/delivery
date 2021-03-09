package org.artemdikov.delivery.controller.dto.converter.impl;

import org.artemdikov.delivery.controller.dto.RouteDto;
import org.artemdikov.delivery.controller.dto.converter.Converter;
import org.artemdikov.delivery.model.Route;
import org.artemdikov.delivery.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteDtoConverter implements Converter<Route, RouteDto> {

    private CompanyService companyService;

    @Autowired
    public RouteDtoConverter(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Route toModel(RouteDto dto) {
        if (dto == null)
            return null;
        Route route = new Route();
        dto.getRouteParts().forEach((key, value) -> route.put(key, companyService.get(value)));
        return route;
    }

    @Override
    public RouteDto toDto(Route model) {
        if (model == null)
            return null;
        return new RouteDto(model);
    }
}
