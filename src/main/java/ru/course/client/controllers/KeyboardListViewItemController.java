package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Controller
public class KeyboardListViewItemController implements ProductController {
    @FXML
    private ImageView image;

    @FXML
    private JFXTextArea description;

    @FXML
    private Text type;

    @FXML
    private Text name;

    @FXML
    private Text manufacturer;

    @FXML
    private Text releaseDate;

    @FXML
    private Text weight;

    @FXML
    private Text color;

    @FXML
    private Text material;

    @FXML
    private Text price;

    @FXML
    private Text connectionType;

    @FXML
    private Text backlight;

    @FXML
    private Text moistureProtection;


    @Setter
    private Long id;

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setConnectionType(String connectionType) {
        this.connectionType.setText(connectionType);
    }

    public void setBacklight(String backlight) {
        this.backlight.setText(backlight);
    }

    public void setMoistureProtection(String moistureProtection) {
        this.moistureProtection.setText(moistureProtection);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.setText(manufacturer);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.setText(releaseDate);
    }

    public void setWeight(String weight) {
        this.weight.setText(weight);
    }

    public void setColor(String color) {
        this.color.setText(color);
    }

    public void setMaterial(String material) {
        this.material.setText(material);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }
}
