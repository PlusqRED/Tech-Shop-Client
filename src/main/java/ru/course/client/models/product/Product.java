package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Product {
    private String releaseDate;
    private Float weight;
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String type;

    private String imageUrl;

    @Override
    public String toString() {
        return "Product: " +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", manufacturer='" + manufacturer + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", weight=" + weight +
                ", imageUrl='" + imageUrl + '\'';
    }

    private String manufacturer;


}
