package ru.course.client.models.product.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Material {
    private Integer id;
    private String name;

    @Override
    public String toString() {
        return "Material: " +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
