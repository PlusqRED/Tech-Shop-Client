package ru.course.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.Smartphone;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.ServerSmartphoneService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class InsertSmartphoneController {
    private final ServerSmartphoneService serverSmartphoneService;
    private final ControllerValidator controllerValidator;
    private final ReleaseControllerValidator releaseControllerValidator;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField numberOfBackCameras;
    @FXML
    private JFXTextField numberOfFrontCameras;
    private MainWindowController mainWindowController;
    @FXML
    private JFXTextField displaySize;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXDatePicker releaseDate;
    @FXML
    private JFXTextField color;
    @FXML
    private JFXTextField manufacturer;
    @FXML
    private JFXTextField displayResolution;
    @FXML
    private JFXTextField os;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField material;
    @FXML
    private JFXTextField ram;

    public void initialize() {
        releaseControllerValidator.validate();
        Stream.of(type, name, description, price, manufacturer, weight, color, material, os, displaySize, displayResolution, ram, numberOfBackCameras, numberOfFrontCameras)
                .forEach(this::setUpRequiredFieldValidator);
        ReleaseControllerValidator.logValidate();
        controllerValidator.validate();
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        releaseControllerValidator.validate();
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить полеControllerValidator.checkVm();");
        controllerValidator.validate();
        ReleaseControllerValidator.logValidate();
        jfxTextField.getValidators().add(requiredFieldValidator);
        releaseControllerValidator.validate();
    }

    @FXML
    void add(ActionEvent event) {
        controllerValidator.validate();
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, os, displaySize, displayResolution, ram, numberOfBackCameras, numberOfFrontCameras)
                .allMatch(JFXTextField::validate)) {
            releaseControllerValidator.validate();
            Smartphone smartphone = new Smartphone();
            controllerValidator.validate();
            smartphone.setType(type.getText());
            smartphone.setName(name.getText());
            releaseControllerValidator.validate();
            ReleaseControllerValidator.logValidate();
            smartphone.setDescription(description.getText());
            controllerValidator.validate();
            smartphone.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            smartphone.setManufacturer(manufacturer.getText());
            smartphone.setReleaseDate(releaseDate.getValue().toString());
            smartphone.setWeight(Float.valueOf(weight.getText()));
            smartphone.setColor(Color.builder().name(color.getText()).build());
            ReleaseControllerValidator.logValidate();
            smartphone.setMaterial(Material.builder().name(material.getText()).build());
            smartphone.setOs(os.getText());
            ReleaseControllerValidator.logValidate();
            smartphone.setDisplaySize(Float.valueOf(displaySize.getText()));
            smartphone.setDisplayResolution(displayResolution.getText());
            smartphone.setRam(ram.getText());
            ReleaseControllerValidator.logValidate();
            smartphone.setNumberOfBackCameras(Short.valueOf(numberOfBackCameras.getText()));
            ReleaseControllerValidator.logValidate();
            smartphone.setNumberOfFrontCameras(Short.valueOf(numberOfFrontCameras.getText()));
            smartphone.setImageUrl("smartphone1.jpeg");
            serverSmartphoneService.save(smartphone);
            ReleaseControllerValidator.logValidate();
            mainWindowController.loadSmartphoneItems(serverSmartphoneService.findAll());
        }
    }

    public void setParentController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
        ReleaseControllerValidator.logValidate();
    }
}
