package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Monitor extends Product {
    private Float diagonal;
    private String aspectRatio;
    private String resolution;
    private String matrixType;
    private String matrixFrequency;
    private boolean builtInSpeakers;
    private Color color;
    private Material material;
}
