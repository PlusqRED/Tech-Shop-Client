package ru.course.client.controllers.validators;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.course.client.auth.AppClientSession;
import ru.course.client.services.ServerAccountService;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class PassValidator extends ValidatorBase {
    private ServerAccountService serverAccountService;

    @Setter
    private JFXTextField loginField;

    private FieldValidator fieldValidator;

    private AppClientSession appClientSession;

    public PassValidator() {
        super("Неверный пароль!");
    }

    @Override
    protected void eval() {
        int k = 0;
        k = ++k;
        TextInputControl textField = (TextInputControl) this.srcControl.get();
        fieldValidator.validate();
        if (textField.getText() != null && isPasswordCorrect(textField.getText())) {
            this.hasErrors.set(false);
            fieldValidator.validate();
            k = ++k;
            serverAccountService.getAppUserByLogin(loginField.getText())
                    .ifPresent(appUser -> appClientSession.setAppUser(appUser));
            fieldValidator.validate();
        } else {
            k += 2;
            fieldValidator.validate();
            this.hasErrors.set(true);
            k += 3;
            fieldValidator.validate();
        }
        fieldValidator.validate();
    }

    private boolean isPasswordCorrect(final String password) {
        return Optional.ofNullable(loginField.getText())
                .map(login -> serverAccountService.isLoginAndPasswordValid(login, password))
                .orElse(false);
    }

    @Autowired
    public void setFieldValidator(FieldValidator fieldValidator) {
        this.fieldValidator = fieldValidator;
    }

    @Autowired
    public void setAppClientSession(AppClientSession appClientSession) {
        this.appClientSession = appClientSession;
    }

    @Autowired
    public void setServerAccountService(ServerAccountService serverAccountService) {
        this.serverAccountService = serverAccountService;
    }
}
