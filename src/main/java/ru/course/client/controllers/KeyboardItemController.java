package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;

@Controller
public class KeyboardItemController implements ProductController {
    @FXML
    private ImageView image;
    @FXML
    private Text name;

    @FXML
    private JFXTextArea description;
    private ControllerValidator controllerValidator2;

    @FXML
    private Text type;


    @FXML
    private Text manufacturer;

    @FXML
    private Text releaseDate;

    @FXML
    private Text weight;

    private ControllerValidator controllerValidator1;


    @FXML
    private Text backlight;

    @FXML
    private Text moistureProtection;


    @FXML
    private Text color;

    @FXML
    private Text material;

    @FXML
    private Text price;

    @FXML
    private Text connectionType;

    private ControllerValidator controllerValidator = new ControllerValidator();
    private ReleaseControllerValidator releaseControllerValidator = new ReleaseControllerValidator();


    @Setter
    private Long id;

    public void setBacklight(String backlight) {
        this.backlight.setText(backlight);
    }

    public void setMoistureProtection(String moistureProtection) {
        this.moistureProtection.setText(moistureProtection);
    }

    public void setColor(String color) {
        this.color.setText(color);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.setText(manufacturer);
    }

    public void setWeight(String weight) {
        this.weight.setText(weight);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.setText(releaseDate);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setMaterial(String material) {
        this.material.setText(material);
    }

    public void setConnectionType(String connectionType) {
        this.connectionType.setText(connectionType);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }
}
