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
import ru.course.client.models.product.Monitor;
import ru.course.client.models.product.commons.Color;
import ru.course.client.models.product.commons.Material;
import ru.course.client.services.MonitorService;

import java.math.BigDecimal;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class MonitorAddController {
    private final MonitorService monitorService;
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
    private JFXTextField diagonal;

    @FXML
    private JFXTextField aspectRatio;

    @FXML
    private JFXTextField resolution;

    @FXML
    private JFXTextField matrixType;

    @FXML
    private JFXTextField matrixFrequency;

    @FXML
    private JFXCheckBox builtInSpeakers;
    private JFXListView<Parent> monitors_lv;
    private MainFrameController mainFrameController;

    public void initialize() {
        Stream.of(type, name, description, price, manufacturer, weight, color, material, diagonal, aspectRatio, resolution, matrixType, matrixFrequency)
                .forEach(this::setUpRequiredFieldValidator);
    }

    private void setUpRequiredFieldValidator(JFXTextField jfxTextField) {
        RequiredFieldValidator requiredFieldValidator = new RequiredFieldValidator("Необходимо заполнить поле!");
        jfxTextField.getValidators().add(requiredFieldValidator);
    }

    @FXML
    void add(ActionEvent event) {
        if (Stream.of(type, name, description, price, manufacturer, weight, color, material, diagonal, aspectRatio, resolution, matrixType, matrixFrequency)
                .allMatch(JFXTextField::validate)) {
            Monitor monitor = new Monitor();
            monitor.setType(type.getText());
            monitor.setName(name.getText());
            monitor.setDescription(description.getText());
            monitor.setPrice(BigDecimal.valueOf(Double.parseDouble(price.getText())));
            monitor.setManufacturer(manufacturer.getText());
            monitor.setReleaseDate(releaseDate.getValue().toString());
            monitor.setWeight(Float.valueOf(weight.getText()));
            monitor.setDiagonal(Float.valueOf(diagonal.getText()));
            monitor.setAspectRatio(aspectRatio.getText());
            monitor.setResolution(resolution.getText());
            monitor.setMatrixType(matrixType.getText());
            monitor.setMatrixFrequency(matrixFrequency.getText());
            monitor.setColor(Color.builder().name(color.getText()).build());
            monitor.setMaterial(Material.builder().name(material.getText()).build());
            monitor.setBuiltInSpeakers(builtInSpeakers.isSelected());
            monitor.setImageUrl("monitor1.jpeg");
            monitorService.save(monitor);
            mainFrameController.loadMonitorItems(monitorService.findAll());
        }
    }

    public void setParentController(MainFrameController mainFrameController) {
        this.mainFrameController = mainFrameController;
    }
}
