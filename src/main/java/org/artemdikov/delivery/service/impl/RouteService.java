package org.artemdikov.delivery.service.impl;

import javassist.NotFoundException;
import org.artemdikov.delivery.model.Company;
import org.artemdikov.delivery.model.Route;
import org.artemdikov.delivery.service.CheapestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RouteService implements CheapestService<Route> {

    private CompanyService companyService;

    @Autowired
    public RouteService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Override
    public Route getCheapest(String from, String to) throws Exception {
        Route route = new Route();
        route.put("First Mile", getCheapestCompany("First Mile", from, from));
        route.put("Transport", getCheapestCompany("Transport", from, to));
        route.put("Last Mile", getCheapestCompany("Last Mile", to, to));
        return route;
    }

    private Company getCheapestCompany(String routePart, String from, String to) throws Exception {
        List<Company> possibleRoutes = companyService.getCheapestCompaniesForOneRoutePart(routePart, from, to);
        if (possibleRoutes.size() == 0)
            throw new NotFoundException("Company not found with parameters:" +
                    " routePart=" + routePart + ", from=" + from + ", to=" + to);
        if (possibleRoutes.size() > 1) {
            return possibleRoutes.stream().min(Comparator.comparingInt(Company::getTimeInHours)).get();
        }
        return possibleRoutes.get(0);
    }
}
