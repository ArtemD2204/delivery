package org.artemdikov.delivery.controller;

import org.artemdikov.delivery.model.Company;
import org.artemdikov.delivery.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyRepository companyRepository;

    @Test
    public void getCheapestRouteOkTest() throws Exception {
        Company first = new Company(null, "SmartBoy", "First Mile", "A", "A", 1.0, 2);
        Company transport = new Company(null, "SuperMan", "Transport", "A", "B", 10.0, 48);
        Company last = new Company(null, "OldGranny", "Last Mile", "B", "B", 0.5, 8);
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("First Mile", "A", "A"))
                .thenReturn(Arrays.asList(first));
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("Transport", "A", "B"))
                .thenReturn(Arrays.asList(transport));
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("Last Mile", "B", "B"))
                .thenReturn(Arrays.asList(last));
        mockMvc.perform(MockMvcRequestBuilders.get("/route?from=A&to=B"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\n" +
                        "    \"routeParts\": {\n" +
                        "        \"First Mile\": \"SmartBoy\",\n" +
                        "        \"Transport\": \"SuperMan\",\n" +
                        "        \"Last Mile\": \"OldGranny\"\n" +
                        "    },\n" +
                        "    \"totalCost\": \"11.5$\",\n" +
                        "    \"totalTime\": \"2d 10h\"\n" +
                        "}"));
    }

    @Test
    public void getCheapestRouteFailTest() throws Exception {
        Company first = new Company(null, "SmartBoy", "First Mile", "A", "A", 1.0, 2);
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("First Mile", "A", "A"))
                .thenReturn(Arrays.asList(first));
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("Transport", "A", "D"))
                .thenReturn(new ArrayList<>());
        when(companyRepository.getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice("Last Mile", "D", "D"))
                .thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/route?from=A&to=D"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Company not found with parameters: routePart=Transport, from=A, to=D"));
    }
}
