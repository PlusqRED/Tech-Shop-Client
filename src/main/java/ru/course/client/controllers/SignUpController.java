package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.course.client.auth.UserSession;
import ru.course.client.controllers.validators.LoginAlreadyExistsValidator;
import ru.course.client.models.users.AppUser;
import ru.course.client.models.users.Role;
import ru.course.client.services.AccountService;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final LoginAlreadyExistsValidator loginAlreadyExistsValidator;
    private final AccountService accountService;
    private final UserSession userSession;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @Value("Необходимо заполнить поле!")
    private String REQUIRED_FIELD;
    private AuthController authController;
    private Stage signUpStage;

    public void initialize() {
        RequiredFieldValidator requiredLoginValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        login.getValidators().addAll(requiredLoginValidator, loginAlreadyExistsValidator);
        RequiredFieldValidator requiredPasswordValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        password.getValidators().addAll(requiredPasswordValidator);
    }

    @FXML
    @SneakyThrows
    public void signUp() {
        if (login.validate() && password.validate()) {
            signUpStage.close();
            AppUser newCustomer = AppUser.builder()
                    .login(login.getText())
                    .password(password.getText())
                    .role(Role.builder().id(3).build())
                    .build();
            accountService.create(newCustomer);
            userSession.setAppUser(newCustomer);
            authController.launchMainFrame();
        }
    }

    public void setAuthController(AuthController authController) {
        this.authController = authController;
    }

    public void setStage(Stage signUpStage) {
        this.signUpStage = signUpStage;
    }
}
