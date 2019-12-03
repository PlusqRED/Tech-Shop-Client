package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Smartphone extends Product {
    private String os;
    private Float displaySize;
    private String displayResolution;
    private String ram;
    private Short numberOfBackCameras;
    private Short numberOfFrontCameras;
    private Color color;
    private Material material;
}
