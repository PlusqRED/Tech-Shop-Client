package ru.course.client.controllers.validators;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.base.ValidatorBase;
import javafx.scene.control.TextInputControl;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.course.client.auth.UserSession;
import ru.course.client.services.AccountService;

import java.util.Optional;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class IncorrectPasswordValidator extends ValidatorBase {
    private AccountService accountService;

    @Setter
    private JFXTextField loginField;

    private UserSession userSession;

    public IncorrectPasswordValidator() {
        super("Неверный пароль!");
    }

    @Override
    protected void eval() {
        TextInputControl textField = (TextInputControl) this.srcControl.get();
        if (textField.getText() != null && isPasswordCorrect(textField.getText())) {
            this.hasErrors.set(false);
            accountService.getAppUserByLogin(loginField.getText())
                    .ifPresent(appUser -> userSession.setAppUser(appUser));
        } else {
            this.hasErrors.set(true);
        }
    }

    private boolean isPasswordCorrect(final String password) {
        return Optional.ofNullable(loginField.getText())
                .map(login -> accountService.isLoginAndPasswordValid(login, password))
                .orElse(false);
    }

    @Autowired
    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
