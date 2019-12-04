package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private Long id;
    private String type;
    private String name;
    private String description;
    private BigDecimal price;
    private String manufacturer;
    private String releaseDate;
    private Float weight;
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
}
