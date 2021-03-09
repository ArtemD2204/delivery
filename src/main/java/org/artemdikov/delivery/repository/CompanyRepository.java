package org.artemdikov.delivery.repository;

import org.artemdikov.delivery.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByName(String name);

    @Query (value = "SELECT * FROM company " +
            "WHERE route_part=:routePart AND pickup_city=:pickupCity AND delivery_city=:deliveryCity AND price IN " +
            "(SELECT MIN(price) FROM company " +
            "WHERE route_part=:routePart AND pickup_city=:pickupCity AND delivery_city=:deliveryCity)", nativeQuery = true)
    List<Company> getByRoutePartAndPickupCityAndDeliveryCityAndMinPrice(@Param("routePart") String routePart,
                                     @Param("pickupCity") String pickupCity,
                                     @Param("deliveryCity") String deliveryCity);
}
