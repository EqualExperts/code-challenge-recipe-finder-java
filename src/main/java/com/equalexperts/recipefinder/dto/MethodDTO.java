package com.equalexperts.recipefinder.dto;

import java.util.List;
import java.util.Objects;

public class MethodDTO {

    private List<String> method;

    public List<String> getMethod() {
        return method;
    }

    public void setMethod(List<String> method) {
        this.method = method;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodDTO methodDTO = (MethodDTO) o;
        return Objects.equals(method, methodDTO.method);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method);
    }

    @Override
    public String toString() {
        return "MethodDTO{" +
                "method=" + method +
                '}';
    }
}
