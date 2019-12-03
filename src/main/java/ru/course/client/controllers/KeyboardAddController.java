package ru.course.client.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.models.product.Keyboard;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.KeyboardService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class KeyboardAddController {
    private final KeyboardService keyboardService;
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
    private MainFrameController mainFrameController;

    public void initialize() {
        Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType)
                .forEach(this::setUpRequiredFieldValidator);
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить поле!");
        jfxTextField.getValidators().add(requiredFieldValidator);
    }

    @FXML
    void add(ActionEvent event) {
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType)
                .allMatch(JFXTextField::validate)) {
            Keyboard keyboard = new Keyboard();
            keyboard.setType(type.getText());
            keyboard.setName(name.getText());
            keyboard.setDescription(description.getText());
            keyboard.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            keyboard.setManufacturer(manufacturer.getText());
            keyboard.setReleaseDate(releaseDate.getValue().toString());
            keyboard.setWeight(Float.valueOf(weight.getText()));
            keyboard.setColor(Color.builder().name(color.getText()).build());
            keyboard.setMaterial(Material.builder().name(material.getText()).build());
            keyboard.setConnectionType(connectionType.getText());
            keyboard.setBacklight(backlight.isSelected());
            keyboard.setMoistureProtection(moistureProtection.isSelected());
            keyboard.setImageUrl("keyboard1.jpeg");
            keyboardService.save(keyboard);
            mainFrameController.loadKeyboardItems(keyboardService.findAll());
        }
    }

    public void setParentController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
}
