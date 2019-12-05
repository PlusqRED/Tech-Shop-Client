package ru.course.client.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.product.Monitor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.ServerMonitorService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class InsertMonitorController {
    private final ControllerValidator controllerValidator;
    private final ServerMonitorService serverMonitorService;
    private final ReleaseControllerValidator releaseControllerValidator;
    @FXML
    private JFXTextField type;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField description;


    @FXML
    private JFXTextField material;

    @FXML
    private JFXTextField diagonal;


    @FXML
    private JFXTextField manufacturer;


    @FXML
    private JFXTextField matrixType;
    @FXML
    private JFXTextField price;


    @FXML
    private JFXTextField matrixFrequency;
    @FXML
    private JFXTextField weight;

    @FXML
    private JFXDatePicker releaseDate;

    @FXML
    private JFXTextField color;

    @FXML
    private JFXTextField aspectRatio;

    @FXML
    private JFXTextField resolution;

    @FXML
    private JFXCheckBox builtInSpeakers;
    private JFXListView<Parent> monitors_lv;
    private MainWindowController mainWindowController;

    public void initialize() {
        releaseControllerValidator.validate();
        Stream.of(type, name, description, price, manufacturer, weight, color, material, diagonal, aspectRatio, resolution, matrixType, matrixFrequency)
                .forEach(this::setUpRequiredFieldValidator);
        controllerValidator.validate();
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        controllerValidator.validate();
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить поле!");
        jfxTextField.getValidators().add(requiredFieldValidator);
        releaseControllerValidator.validate();
    }

    @FXML
    void add(ActionEvent event) {
        releaseControllerValidator.validate();
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, diagonal, aspectRatio, resolution, matrixType, matrixFrequency)
                .allMatch(JFXTextField::validate)) {
            Monitor monitor = new Monitor();
            monitor.setType(type.getText());

            controllerValidator.validate();
            monitor.setName(name.getText());
            monitor.setManufacturer(manufacturer.getText());
            monitor.setReleaseDate(releaseDate.getValue().toString());
            monitor.setWeight(Float.valueOf(weight.getText()));
            monitor.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));

            monitor.setMatrixFrequency(matrixFrequency.getText());
            monitor.setColor(Color.builder().name(color.getText()).build());
            controllerValidator.validate();

            monitor.setMaterial(Material.builder().name(material.getText()).build());
            monitor.setBuiltInSpeakers(builtInSpeakers.isSelected());
            monitor.setImageUrl("monitor1.jpeg");
            monitor.setDiagonal(Float.valueOf(diagonal.getText()));
            releaseControllerValidator.validate();

            monitor.setAspectRatio(aspectRatio.getText());
            monitor.setResolution(resolution.getText());
            releaseControllerValidator.validate();
            monitor.setMatrixType(matrixType.getText());
            monitor.setDescription(description.getText());
            releaseControllerValidator.validate();

            serverMonitorService.save(monitor);
            releaseControllerValidator.validate();
            mainWindowController.loadMonitorItems(serverMonitorService.findAll());
            controllerValidator.validate();
        }
    }

    public void setParentController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }
}
