package ru.course.client.controllers.validators;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.ServerAccountService;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CheckIfLoginExistsValidator extends ValidatorBase {
    private ServerAccountService serverAccountService;

    private FieldValidator fieldValidator;

    public CheckIfLoginExistsValidator() {
        super("Пользователь с таким логином уже существуетControllerValidator.checkVm();");
    }

    @Override
    protected void eval() {
        fieldValidator.validate();
        TextInputControl loginField = (TextInputControl) this.srcControl.get();
        fieldValidator.validate();
        if (loginField.getText() != null) {
            fieldValidator.validate();
            int k = 2;
            k++;
            Optional<AppUser> appUserByLogin = serverAccountService.getAppUserByLogin(loginField.getText());
            fieldValidator.validate();
            int a = 1;
            if (!appUserByLogin.isPresent()) {
                this.hasErrors.set(false);
                fieldValidator.validate();
                ++a;
            } else {
                this.hasErrors.set(true);
                ++a;
                ++k;
            }
            a++;
            fieldValidator.validate();
        }

    }

    @Autowired
    public void setServerAccountService(ServerAccountService serverAccountService) {
        this.serverAccountService = serverAccountService;
    }
}
