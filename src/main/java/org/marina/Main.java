package org.marina;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.marina.services.*;
import org.marina.services.OrderService;
import org.marina.services.ProductService;
import org.marina.services.UserService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initDirectory();
        UserService.initDatabase();
        ProductService.initDatabase();
        OrderService.initDatabase();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Supermarket Application");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
