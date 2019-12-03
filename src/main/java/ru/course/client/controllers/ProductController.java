package ru.course.client.controllers;

import javafx.scene.image.Image;

public interface ProductController {
    void setId(Long id);

    void setType(String type);

    void setManufacturer(String manufacturer);

    void setReleaseDate(String releaseDate);

    void setWeight(String weight);

    void setColor(String color);

    void setMaterial(String material);

    void setImage(Image image);

    void setDescription(String description);

    void setName(String name);

    void setPrice(String price);
}
