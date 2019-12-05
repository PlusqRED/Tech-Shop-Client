package ru.course.client.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.Keyboard;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.ServerKeyboardService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class InsertKeyboardController {
    private final ServerKeyboardService serverKeyboardService;
    private final ReleaseControllerValidator releaseControllerValidator;
    private final ControllerValidator controllerValidator;
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
    private JFXTextField connectionType;

    @FXML
    private JFXCheckBox moistureProtection;

    @FXML
    private JFXCheckBox backlight;
    private MainWindowController mainWindowController;

    public void initialize() {
        controllerValidator.validate();
        Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType)
                .forEach(this::setUpRequiredFieldValidator);
        releaseControllerValidator.validate();
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        controllerValidator.validate();
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить полеControllerValidator.checkVm();");
        releaseControllerValidator.validate();
        jfxTextField.getValidators().add(requiredFieldValidator);
    }

    @FXML
    void add(ActionEvent event) {
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType)
                .allMatch(JFXTextField::validate)) {
            controllerValidator.validate();
            Keyboard keyboard = new Keyboard();
            releaseControllerValidator.validate();
            keyboard.setType(type.getText());
            keyboard.setName(name.getText());
            controllerValidator.validate();
            keyboard.setDescription(description.getText());
            keyboard.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            keyboard.setManufacturer(manufacturer.getText());
            controllerValidator.validate();
            keyboard.setReleaseDate(releaseDate.getValue().toString());
            keyboard.setWeight(Float.valueOf(weight.getText()));
            keyboard.setColor(Color.builder().name(color.getText()).build());
            releaseControllerValidator.validate();
            keyboard.setMaterial(Material.builder().name(material.getText()).build());
            keyboard.setConnectionType(connectionType.getText());
            keyboard.setBacklight(backlight.isSelected());
            controllerValidator.validate();
            keyboard.setMoistureProtection(moistureProtection.isSelected());
            releaseControllerValidator.validate();
            keyboard.setImageUrl("keyboard1.jpeg");
            serverKeyboardService.save(keyboard);
            releaseControllerValidator.validate();
            mainWindowController.loadKeyboardItems(serverKeyboardService.findAll());
        }
    }

    public void setParentController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
