package org.artemdikov.delivery.controller.dto;

import java.util.Objects;

public class CompanyDto {
    private Long id;
    private String name;
    private String routePart;
    private String pickupCity;
    private String deliveryCity;
    private Double price;
    private Integer timeInHours;

    public CompanyDto(Long id, String name, String routePart, String pickupCity, String deliveryCity, Double price, Integer timeInHours) {
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
        CompanyDto that = (CompanyDto) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(routePart, that.routePart) &&
                Objects.equals(pickupCity, that.pickupCity) &&
                Objects.equals(deliveryCity, that.deliveryCity) &&
                Objects.equals(price, that.price) &&
                Objects.equals(timeInHours, that.timeInHours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, routePart, pickupCity, deliveryCity);
    }

    @Override
    public String toString() {
        return "CompanyDto{" +
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
