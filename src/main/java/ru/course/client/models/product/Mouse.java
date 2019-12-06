package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Data
public class Mouse extends Product {
    private String connectionType;
    private String sensorType;


    @Override
    public String toString() {
        return "Mouse: " +
                "connectionType='" + connectionType + '\'' +
                ", sensorType='" + sensorType + '\'' +
                ", buttonAmount=" + buttonAmount +
                ", color=" + color +
                ", material=" + material;
    }

    private Short buttonAmount;
    private Color color;
    private Material material;
}
