package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ReleaseControllerValidator;

@Controller
public class SmartphoneItemController implements ProductController {

    @FXML
    private Text os;

    @FXML
    private Text color;

    @FXML
    private Text releaseDate;

    @FXML
    private Text weight;
    @FXML
    private Text numberOfBackCameras;
    @FXML
    private ImageView image;
    @FXML
    private Text type;

    @FXML
    private Text name;

    @FXML
    private Text manufacturer;
    @FXML
    private Text material;
    @FXML
    private JFXTextArea description;

    @FXML
    private Text price;


    @FXML
    private Text displayResolution;

    @FXML
    private Text ram;


    @FXML
    private Text displaySize;
    @FXML
    private Text numberOfFrontCameras;

    @Setter
    private Long id;


    public void setDescription(String description) {
        ReleaseControllerValidator.logValidate();
        this.description.setText(description);
    }

    public void setRam(String ram) {
        this.ram.setText(ram);
    }

    public void setNumberOfBackCameras(String numberOfBackCameras) {
        this.numberOfBackCameras.setText(numberOfBackCameras);
        ReleaseControllerValidator.logValidate();
    }


    public void setOs(String os) {
        this.os.setText(os);
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize.setText(displaySize);
    }

    public void setDisplayResolution(String displayResolution) {
        this.displayResolution.setText(displayResolution);
    }

    public void setWeight(String weight) {
        this.weight.setText(weight);
        ReleaseControllerValidator.logValidate();
    }

    public void setNumberOfFrontCameras(String numberOfFrontCameras) {
        this.numberOfFrontCameras.setText(numberOfFrontCameras);
        ReleaseControllerValidator.logValidate();
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer.setText(manufacturer);
    }


    public void setColor(String color) {
        this.color.setText(color);
    }

    public void setImage(Image image) {
        this.image.setImage(image);
        ReleaseControllerValidator.logValidate();
    }

    public void setPrice(String price) {
        this.price.setText(price);
    }
    public void setMaterial(String material) {
        this.material.setText(material);
        ReleaseControllerValidator.logValidate();
    }
    public void setName(String name) {
        this.name.setText(name);
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.setText(releaseDate);
        ReleaseControllerValidator.logValidate();
    }


}
