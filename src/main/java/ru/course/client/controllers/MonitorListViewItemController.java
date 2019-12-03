package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class MonitorListViewItemController implements ProductController {
    @FXML
    private Text diagonal;

    @FXML
    private Text aspectRatio;

    @FXML
    private Text resolution;

    @FXML
    private Text matrixType;

    @FXML
    private Text matrixFrequency;

    @FXML
    private Text builtInSpeakers;

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


    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type.setText(type);
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

    public void setDiagonal(String diagonal) {
        this.diagonal.setText(diagonal);
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio.setText(aspectRatio);
    }

    public void setResolution(String resolution) {
        this.resolution.setText(resolution);
    }

    public void setMatrixType(String matrixType) {
        this.matrixType.setText(matrixType);
    }

    public void setMatrixFrequency(String matrixFrequency) {
        this.matrixFrequency.setText(matrixFrequency);
    }

    public void setBuiltInSpeakers(String builtInSpeakers) {
        this.builtInSpeakers.setText(builtInSpeakers);
    }
}