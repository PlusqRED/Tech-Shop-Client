package ru.course.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.models.product.Smartphone;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.SmartphoneService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class SmartphoneAddController {
    private final SmartphoneService smartphoneService;
    @FXML
    private JFXTextField type;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField description;

    @FXML
    private JFXTextField price;

    @FXML
    private JFXTextField manufacturer;

    @FXML
    private JFXTextField weight;

    @FXML
    private JFXDatePicker releaseDate;

    @FXML
    private JFXTextField color;

    @FXML
    private JFXTextField material;

    @FXML
    private JFXTextField os;

    @FXML
    private JFXTextField displaySize;

    @FXML
    private JFXTextField displayResolution;

    @FXML
    private JFXTextField ram;

    @FXML
    private JFXTextField numberOfBackCameras;

    @FXML
    private JFXTextField numberOfFrontCameras;
    private MainFrameController mainFrameController;

    public void initialize() {
        Stream.of(type, name, description, price, manufacturer, weight, color, material, os, displaySize, displayResolution, ram, numberOfBackCameras, numberOfFrontCameras)
                .forEach(this::setUpRequiredFieldValidator);
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить поле!");
        jfxTextField.getValidators().add(requiredFieldValidator);
    }

    @FXML
    void add(ActionEvent event) {
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, os, displaySize, displayResolution, ram, numberOfBackCameras, numberOfFrontCameras)
                .allMatch(JFXTextField::validate)) {
            Smartphone smartphone = new Smartphone();
            smartphone.setType(type.getText());
            smartphone.setName(name.getText());
            smartphone.setDescription(description.getText());
            smartphone.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            smartphone.setManufacturer(manufacturer.getText());
            smartphone.setReleaseDate(releaseDate.getValue().toString());
            smartphone.setWeight(Float.valueOf(weight.getText()));
            smartphone.setColor(Color.builder().name(color.getText()).build());
            smartphone.setMaterial(Material.builder().name(material.getText()).build());
            smartphone.setOs(os.getText());
            smartphone.setDisplaySize(Float.valueOf(displaySize.getText()));
            smartphone.setDisplayResolution(displayResolution.getText());
            smartphone.setRam(ram.getText());
            smartphone.setNumberOfBackCameras(Short.valueOf(numberOfBackCameras.getText()));
            smartphone.setNumberOfFrontCameras(Short.valueOf(numberOfFrontCameras.getText()));
            smartphone.setImageUrl("smartphone1.jpeg");
            smartphoneService.save(smartphone);
            mainFrameController.loadSmartphoneItems(smartphoneService.findAll());
        }
    }

    public void setParentController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
}
