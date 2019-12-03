package ru.course.client.controllers.validators;

import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.course.client.models.users.AppUser;
import ru.course.client.services.AccountService;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginAlreadyExistsValidator extends ValidatorBase {
    private AccountService accountService;

    public LoginAlreadyExistsValidator() {
        super("Пользователь с таким логином уже существует!");
    }

    @Override
    protected void eval() {
        TextInputControl loginField = (TextInputControl) this.srcControl.get();
        if (loginField.getText() != null) {
            Optional<AppUser> appUserByLogin = accountService.getAppUserByLogin(loginField.getText());
            if (!appUserByLogin.isPresent()) {
                this.hasErrors.set(false);
            } else {
                this.hasErrors.set(true);
            }
        }

    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
