package org.artemdikov.delivery.service.impl;

import org.artemdikov.delivery.model.Company;
import org.artemdikov.delivery.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company get(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company get(String name) {
        return companyRepository.findByName(name);
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Transactional
    public void create(List<Company> companyList) {
        for(Company company : companyList) {
            companyRepository.save(company);
        }
    }

    public Company update(Company company) {
        return companyRepository.save(company);
    }

    public void remove(Long id) {
        companyRepository.deleteById(id);
    }

    public List<Company> getList() {
        return companyRepository.findAll();
    }

    public List<Company> getCheapestCompaniesForOneRoutePart(String routePart, String from, String to) {
        return companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice(routePart, from, to);
    }

}
