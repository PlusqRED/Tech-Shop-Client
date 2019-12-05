package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Setter;
import org.springframework.stereotype.Controller;

@Controller
public class MouseItemController implements ProductController {

    @FXML
    private Text material;
    @FXML
    private Text connectionType;


    @FXML
    private Text weight;
    @FXML
    private Text sensorType;
    @FXML
    private Text name;
    @FXML
    private ImageView image;
    @FXML
    private Text color;
    @FXML
    private Text type;


    @FXML
    private JFXTextArea description;
    @FXML
    private Text releaseDate;


    @FXML
    private Text price;
    @FXML
    private Text manufacturer;


    @FXML
    private Text buttonAmount;
    @Setter
    private Long id;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.setText(releaseDate);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public void setButtonAmount(String buttonAmount) {
        this.buttonAmount.setText(buttonAmount);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.setText(manufacturer);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setSensorType(String sensorType) {
        this.sensorType.setText(sensorType);
    }


    public void setMaterial(String material) {
        this.material.setText(material);
    }

    public void setColor(String color) {
        this.color.setText(color);
    }

    public void setConnectionType(String connectionType) {
        this.connectionType.setText(connectionType);
    }

    public void setWeight(String weight) {
        this.weight.setText(weight);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }


}
