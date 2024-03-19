package com.equalexperts.recipefinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class IngredientDTO {
    @JsonProperty("unit_of_measurement")
    private String unitOfMeasurement;
    private Long quantity;
    private String name;

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDTO that = (IngredientDTO) o;
        return Objects.equals(unitOfMeasurement, that.unitOfMeasurement) && Objects.equals(quantity, that.quantity) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitOfMeasurement, quantity, name);
    }

    @Override
    public String toString() {
        return "IngredientDTO{" +
                "unitOfMeasurement='" + unitOfMeasurement + '\'' +
                ", quantity=" + quantity +
                ", name='" + name + '\'' +
                '}';
    }
}
