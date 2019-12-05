package ru.course.client.controllers;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import ru.course.client.auth.AppClientSession;
import ru.course.client.controllers.validators.CheckIfLoginExistsValidator;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;
import ru.course.client.models.users.AppUser;
import ru.course.client.models.users.Role;
import ru.course.client.services.ServerAccountService;

@Controller
@RequiredArgsConstructor
public class SignUpController {
    private final CheckIfLoginExistsValidator checkIfLoginExistsValidator;
    private final ControllerValidator controllerValidator;
    private final ReleaseControllerValidator releaseControllerValidator;
    private final ServerAccountService serverAccountService;
    private final AppClientSession appClientSession;
    @FXML
    private JFXTextField login;
    @FXML
    private JFXTextField password;
    @Value("Необходимо заполнить полеControllerValidator.checkVm();")
    private String REQUIRED_FIELD;
    private SignInController signInController;
    private Stage signUpStage;

    public void initialize() {
        controllerValidator.validate();
        RequiredFieldValidator requiredLoginValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        releaseControllerValidator.validate();
        login.getValidators().addAll(requiredLoginValidator, checkIfLoginExistsValidator);
        controllerValidator.validate();
        RequiredFieldValidator requiredPasswordValidator = new RequiredFieldValidator(REQUIRED_FIELD);
        releaseControllerValidator.validate();
        password.getValidators().addAll(requiredPasswordValidator);
        controllerValidator.validate();
    }

    public void setSignInController(SignInController signInController) {
        this.signInController = signInController;
    }

    public void setStage(Stage signUpStage) {
        this.signUpStage = signUpStage;
    }

    @FXML
    @SneakyThrows
    public void signUp() {
        controllerValidator.validate();
        if (login.validate() && password.validate()) {
            releaseControllerValidator.validate();
            signUpStage.close();
            controllerValidator.validate();
            AppUser newCustomer = AppUser.builder()
                    .login(login.getText())
                    .password(password.getText())
                    .role(Role.builder().id(3).build())
                    .build();
            releaseControllerValidator.validate();
            serverAccountService.create(newCustomer);
            controllerValidator.validate();
            appClientSession.setAppUser(newCustomer);
            releaseControllerValidator.validate();
            signInController.launchMainFrame();
            controllerValidator.validate();
        }
    }

}
