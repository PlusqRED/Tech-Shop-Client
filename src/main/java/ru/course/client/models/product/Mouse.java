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
public class Mouse extends Product {
    private String connectionType;
    private String sensorType;
    private Short buttonAmount;
    private Color color;
    private Material material;
}
