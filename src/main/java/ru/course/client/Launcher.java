package ru.course.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.course.client.config.ConfigurationSpring;
import ru.course.client.controllers.SignInController;
import ru.course.client.controllers.validators.ControllerValidator;
import ru.course.client.controllers.validators.ReleaseControllerValidator;

public class Launcher extends Application {
    public static AnnotationConfigApplicationContext context;

    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext(ConfigurationSpring.class);
        ControllerValidator.checkVm();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/auth.fxml"));
        SignInController signInController = context.getBean("signInController", SignInController.class);
        ReleaseControllerValidator.logValidate();
        signInController.setStage(primaryStage);
        loader.setController(signInController);
        ControllerValidator.checkVm();
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Авторизация");
        ReleaseControllerValidator.logValidate();
        primaryStage.show();
        ControllerValidator.checkVm();
    }
}
