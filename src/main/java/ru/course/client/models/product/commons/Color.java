package ru.course.client.models.product.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor

@Builder
@AllArgsConstructor
@Data
public class Color {
    private String name;
    private Integer id;

    @Override
    public String toString() {
        return "Color: " +
                "id=" + id +
                ", name='" + name + '\'';
    }


}
