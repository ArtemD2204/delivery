package org.artemdikov.delivery.controller.dto;

import org.artemdikov.delivery.model.Route;

import java.util.LinkedHashMap;
import java.util.Map;

public class RouteDto {
    private Map<String, String> routeParts;
    private String totalCost;
    private String totalTime;

    public RouteDto(Route route) {
        routeParts = new LinkedHashMap<>();
        Double totalCost = route.getTotalCost();
        this.totalCost = totalCost + "$";
        Integer totalTime = route.getTotalTime();
        if (totalTime > 23) {
            this.totalTime = (totalTime / 24 )+ "d " + (totalTime % 24) + "h";
        } else {
            this.totalTime = totalTime+"h";
        }
        route.forEach((key, value) -> routeParts.put(key, value.getName()));
    }

    public String getTotalCost() {
        return totalCost;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public Map<String, String> getRouteParts() {
        return routeParts;
    }
}
