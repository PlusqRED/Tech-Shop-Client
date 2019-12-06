package ru.course.client.models.product.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Material {
    private Integer id;
    @Override
    public String toString() {
        return "Material: " +
                "id=" + id +
                ", name='" + name + '\'';
    }

    private String name;


}
