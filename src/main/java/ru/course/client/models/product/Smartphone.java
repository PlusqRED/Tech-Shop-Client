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
    @Override
    public String toString() {
        return "Smartphone: " +
                "os='" + os + '\'' +
                ", displaySize=" + displaySize +
                ", displayResolution='" + displayResolution + '\'' +
                ", ram='" + ram + '\'' +
                ", numberOfBackCameras=" + numberOfBackCameras +
                ", numberOfFrontCameras=" + numberOfFrontCameras +
                ", color=" + color +
                ", material=" + material;
    }

    private Material material;

    private String displayResolution;


    private Short numberOfFrontCameras;
    private Color color;
    private String ram;
    private Short numberOfBackCameras;
    private Float displaySize;

}
