package org.artemdikov.delivery.controller;

import org.artemdikov.delivery.controller.dto.RouteDto;
import org.artemdikov.delivery.controller.dto.converter.Converter;
import org.artemdikov.delivery.controller.dto.converter.impl.RouteDtoConverter;
import org.artemdikov.delivery.model.Route;
import org.artemdikov.delivery.service.CheapestService;
import org.artemdikov.delivery.service.impl.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {

    private CheapestService<Route> routeService;
    private Converter<Route, RouteDto> routeDtoConverter;

    @Autowired
    public RouteController(RouteService routeService, RouteDtoConverter routeDtoConverter) {
        this.routeService = routeService;
        this.routeDtoConverter = routeDtoConverter;
    }

    @GetMapping("/route")
    public ResponseEntity<RouteDto> getCheapestRoute(@RequestParam String from, @RequestParam String to) throws Exception {
        return new ResponseEntity<>(routeDtoConverter.toDto(routeService.getCheapest(from, to)), HttpStatus.OK);
    }
}
