package org.artemdikov.delivery.model;

import java.util.LinkedHashMap;

public class Route extends LinkedHashMap<String, Company> {
    public Double getTotalCost() {
        return values().stream().mapToDouble(Company::getPrice).reduce(Double::sum).orElse(0);
    }
    public Integer getTotalTime() {
        return values().stream().mapToInt(Company::getTimeInHours).reduce(Integer::sum).orElse(0);
    }
}
