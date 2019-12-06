package ru.course.client.controllers;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.PassValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;

@Controller
@RequiredArgsConstructor
public class SignInController {

    private final MainWindowController mainWindowController;
    private final ReleaseControllerValidator releaseControllerValidator;
    private final SignUpController signUpController;
    private final ControllerValidator controllerValidator;
    private final PassValidator passValidator;
    @FXML
    private JFXTextField login_tf;
    @FXML
    private JFXPasswordField password_tf;
    @Setter
    private Stage stage;

    @Value("Необходимо заполнить поле!")
    private String REQUIRED_FIELD;

    public void initialize() {
        controllerValidator.validate();
        releaseControllerValidator.validate();
        RequiredFieldValidator requiredLoginValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        controllerValidator.validate();
        releaseControllerValidator.validate();
        login_tf.getValidators().add(requiredLoginValidator);
        controllerValidator.validate();
        RequiredFieldValidator requiredPasswordValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        controllerValidator.validate();
        passValidator.setLoginField(login_tf);
        controllerValidator.validate();
        releaseControllerValidator.validate();
        password_tf.getValidators().addAll(requiredPasswordValidator, passValidator);
    }

    @FXML
    @SneakyThrows
    void signIn() {
        if (login_tf.validate() && password_tf.validate()) {
            controllerValidator.validate();
            launchMainFrame();
            releaseControllerValidator.validate();
            controllerValidator.validate();
        }
    }

    protected void launchMainFrame() throws java.io.IOException {
        Stage mainFrameStage = new Stage();
        controllerValidator.validate();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/main_frame.fxml"));
        controllerValidator.validate();
        mainWindowController.setStage(mainFrameStage);
        controllerValidator.validate();
        releaseControllerValidator.validate();
        loader.setController(mainWindowController);
        mainFrameStage.setScene(new Scene(loader.load()));
        controllerValidator.validate();
        releaseControllerValidator.validate();
        mainFrameStage.setTitle("Учет продукции");
        mainFrameStage.setOnCloseRequest(e -> stage.show());
        controllerValidator.validate();
        releaseControllerValidator.validate();
        mainFrameStage.show();
    }

    @FXML
    @SneakyThrows
    void signUp() {
        stage.close();
        controllerValidator.validate();
        Stage signUpStage = new Stage();
        controllerValidator.validate();
        releaseControllerValidator.validate();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/sign_up.fxml"));
        controllerValidator.validate();
        signUpController.setSignInController(this);
        releaseControllerValidator.validate();
        signUpController.setStage(signUpStage);
        loader.setController(signUpController);
        signUpStage.setScene(new Scene(loader.load()));
        signUpStage.setTitle("Регистрация");
        signUpStage.setOnCloseRequest(e -> stage.show());
        releaseControllerValidator.validate();
        signUpStage.show();
    }
}
