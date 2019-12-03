package ru.course.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.models.product.Mouse;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.MouseService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class MouseAddController {
    private final MouseService mouseService;
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
    private JFXTextField sensorType;

    @FXML
    private JFXTextField buttonAmount;
    private MainFrameController mainFrameController;

    public void initialize() {
        Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType, sensorType, buttonAmount)
                .forEach(this::setUpRequiredFieldValidator);
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить поле!");
        jfxTextField.getValidators().add(requiredFieldValidator);
    }

    @FXML
    void add(ActionEvent event) {
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType, sensorType, buttonAmount)
                .allMatch(JFXTextField::validate)) {
            Mouse mouse = new Mouse();
            mouse.setType(type.getText());
            mouse.setName(name.getText());
            mouse.setDescription(description.getText());
            mouse.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            mouse.setManufacturer(manufacturer.getText());
            mouse.setReleaseDate(releaseDate.getValue().toString());
            mouse.setWeight(Float.valueOf(weight.getText()));
            mouse.setColor(Color.builder().name(color.getText()).build());
            mouse.setMaterial(Material.builder().name(material.getText()).build());
            mouse.setConnectionType(connectionType.getText());
            mouse.setSensorType(sensorType.getText());
            mouse.setButtonAmount(Short.valueOf(buttonAmount.getText()));
            mouse.setImageUrl("mouse1.jpeg");
            mouseService.save(mouse);
            mainFrameController.loadMouseItems(mouseService.findAll());
        }
    }

    public void setParentController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
}
