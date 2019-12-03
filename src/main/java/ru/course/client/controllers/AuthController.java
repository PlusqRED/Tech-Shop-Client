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
import ru.course.client.controllers.validators.IncorrectPasswordValidator;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final MainFrameController mainFrameController;
    private final SignUpController signUpController;
    private final IncorrectPasswordValidator incorrectPasswordValidator;
    @FXML
    private JFXTextField login_tf;
    @FXML
    private JFXPasswordField password_tf;
    @Setter
    private Stage stage;

    @Value("Необходимо заполнить поле!")
    private String REQUIRED_FIELD;

    public void initialize() {
        RequiredFieldValidator requiredLoginValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        login_tf.getValidators().add(requiredLoginValidator);
        RequiredFieldValidator requiredPasswordValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        incorrectPasswordValidator.setLoginField(login_tf);
        password_tf.getValidators().addAll(requiredPasswordValidator, incorrectPasswordValidator);
    }

    @FXML
    @SneakyThrows
    void signIn() {
        if (login_tf.validate() && password_tf.validate()) {
            launchMainFrame();
        }
    }

    protected void launchMainFrame() throws java.io.IOException {
        Stage mainFrameStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/main_frame.fxml"));
        mainFrameController.setStage(mainFrameStage);
        loader.setController(mainFrameController);
        mainFrameStage.setScene(new Scene(loader.load()));
        mainFrameStage.setTitle("Учет продукции");
        mainFrameStage.setOnCloseRequest(e -> stage.show());
        mainFrameStage.show();
    }

    @FXML
    @SneakyThrows
    void signUp() {
        stage.close();
        Stage signUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/sign_up.fxml"));
        signUpController.setAuthController(this);
        signUpController.setStage(signUpStage);
        loader.setController(signUpController);
        signUpStage.setScene(new Scene(loader.load()));
        signUpStage.setTitle("Регистрация");
        signUpStage.setOnCloseRequest(e -> stage.show());
        signUpStage.show();
    }
}
