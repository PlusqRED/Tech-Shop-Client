package ru.course.client.models.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Keyboard extends Product {
    private String connectionType;

    @Override
    public String toString() {
        return "Keyboard: " +
                "connectionType='" + connectionType + '\'' +
                ", backlight=" + backlight +
                ", moistureProtection=" + moistureProtection +
                ", color=" + color +
                ", material=" + material;
    }

    private Material material;
    private boolean backlight;
    private boolean moistureProtection;
    private Color color;


}


