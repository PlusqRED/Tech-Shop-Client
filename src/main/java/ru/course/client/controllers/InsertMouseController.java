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
import ru.course.client.models.product.Mouse;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.ServerMouseService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class InsertMouseController {
    private final ServerMouseService serverMouseService;
    private final ReleaseControllerValidator releaseControllerValidator;
    private final ControllerValidator controllerValidator;
    @FXML
    private JFXTextField description;
    @FXML
    private JFXTextField color;
    @FXML
    private JFXTextField material;
    @FXML
    private JFXTextField manufacturer;
    @FXML
    private JFXTextField price;
    @FXML
    private JFXTextField weight;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField type;
    @FXML
    private JFXTextField sensorType;


    @FXML
    private JFXTextField connectionType;
    @FXML
    private JFXDatePicker releaseDate;

    @FXML
    private JFXTextField buttonAmount;
    private MainWindowController mainWindowController;

    public void initialize() {
        controllerValidator.validate();
        Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType, sensorType, buttonAmount)
                .forEach(this::setUpRequiredFieldValidator);
        releaseControllerValidator.validate();
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        controllerValidator.validate();
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить полеControllerValidator.checkVm();");
        releaseControllerValidator.validate();
        jfxTextField.getValidators().add(requiredFieldValidator);
        controllerValidator.validate();
    }

    @FXML
    void add(ActionEvent event) {
        controllerValidator.validate();
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, connectionType, sensorType, buttonAmount)
                .allMatch(JFXTextField::validate)) {
            releaseControllerValidator.validate();
            Mouse mouse = new Mouse();
            mouse.setType(type.getText());
            controllerValidator.validate();
            mouse.setName(name.getText());
            releaseControllerValidator.validate();
            mouse.setDescription(description.getText());
            mouse.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            controllerValidator.validate();
            mouse.setManufacturer(manufacturer.getText());
            mouse.setReleaseDate(releaseDate.getValue().toString());
            mouse.setWeight(Float.valueOf(weight.getText()));
            releaseControllerValidator.validate();
            mouse.setColor(Color.builder().name(color.getText()).build());
            mouse.setMaterial(Material.builder().name(material.getText()).build());
            controllerValidator.validate();
            mouse.setConnectionType(connectionType.getText());
            mouse.setSensorType(sensorType.getText());
            releaseControllerValidator.validate();
            mouse.setButtonAmount(Short.valueOf(buttonAmount.getText()));
            mouse.setImageUrl("mouse1.jpeg");
            controllerValidator.validate();
            serverMouseService.save(mouse);
            mainWindowController.loadMouseItems(serverMouseService.findAll());
            releaseControllerValidator.validate();
        }
    }

    public void setParentController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
