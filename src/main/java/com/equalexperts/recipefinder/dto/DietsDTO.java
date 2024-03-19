package com.equalexperts.recipefinder.dto;

import java.util.List;
import java.util.Objects;

public class DietsDTO {
    private List<String> diets;

    public List<String> getDiets() {
        return diets;
    }

    public void setDiets(List<String> diets) {
        this.diets = diets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DietsDTO dietsDTO = (DietsDTO) o;
        return Objects.equals(diets, dietsDTO.diets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diets);
    }

    @Override
    public String toString() {
        return "DietsDTO{" +
                "diets=" + diets +
                '}';
    }
}
