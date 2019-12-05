package ru.course.client.controllers;

import javafx.scene.image.Image;

public interface ProductController {


    void setColor(String color);

    void setManufacturer(String manufacturer);

    void setDescription(String description);

    void setName(String name);


    void setMaterial(String material);

    void setType(String type);

    void setId(Long id);

    void setReleaseDate(String releaseDate);

    void setPrice(String price);

    void setWeight(String weight);

    void setImage(Image image);


}
