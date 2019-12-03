package ru.course.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.course.client.config.SpringConfig;
import ru.course.client.controllers.AuthController;

public class Launcher extends Application {
    public static AnnotationConfigApplicationContext context;

    public void start(Stage primaryStage) throws Exception {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("views/auth.fxml"));
        AuthController authController = context.getBean("authController", AuthController.class);
        authController.setStage(primaryStage);
        loader.setController(authController);
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Авторизация");
        primaryStage.show();
    }
}
