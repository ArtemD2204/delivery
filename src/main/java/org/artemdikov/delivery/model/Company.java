package org.artemdikov.delivery.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Artem Dikov
 */

@Entity
@Table(name = "company")
public class Company {
    @Id
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "company_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "route_part")
    private String routePart;

    @Column(name = "pickup_city")
    private String pickupCity;

    @Column(name = "delivery_city")
    private String deliveryCity;

    @Column(name = "price")
    private Double price;

    @Column(name = "time")
    private Integer timeInHours;

    public Company() {
    }

    public Company(Long id, String name, String routePart, String pickupCity, String deliveryCity, Double price, Integer timeInHours) {
        this.id = id;
        this.name = name;
        this.routePart = routePart;
        this.pickupCity = pickupCity;
        this.deliveryCity = deliveryCity;
        this.price = price;
        this.timeInHours = timeInHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoutePart() {
        return routePart;
    }

    public void setRoutePart(String routePart) {
        this.routePart = routePart;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTimeInHours() {
        return timeInHours;
    }

    public void setTimeInHours(Integer timeInHours) {
        this.timeInHours = timeInHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(routePart, company.routePart) &&
                Objects.equals(pickupCity, company.pickupCity) &&
                Objects.equals(deliveryCity, company.deliveryCity) &&
                Objects.equals(price, company.price) &&
                Objects.equals(timeInHours, company.timeInHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, routePart, pickupCity, deliveryCity);
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", routePart='" + routePart + '\'' +
                ", pickupCity='" + pickupCity + '\'' +
                ", deliveryCity='" + deliveryCity + '\'' +
                ", price=" + price +
                ", timeInHours=" + timeInHours +
                '}';
    }
}
