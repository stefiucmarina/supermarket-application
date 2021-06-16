package org.marina.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.marina.exceptions.AccountExists;
import org.marina.services.UserService;

import java.io.IOException;


public class LoginController {

    @FXML
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    public static String user;

    @FXML
    public void handleLoginButtonAction() {

        String username = usernameField.getText();
        String rol = new String();
        String password = passwordField.getText();

        if (username == null || username.isEmpty()) {
            loginMessage.setText("Please type in a username!");
            return;
        }

        if (password == null || password.isEmpty()) {
            loginMessage.setText("Password cannot be empty");
            return;
        }
        try{
            UserService.checkUsernameAndPassword(username,password);
        }
        catch(AccountExists e)
        {
            rol = UserService.getUserRole(username, password);
            if(rol.equals("Administrator")){
                try {
                    Stage stage = (Stage) loginMessage.getScene().getWindow();
                    Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
                    Scene scene = new Scene(viewStudentsRoot, 900, 600);
                    stage.setScene(scene);
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    user = usernameField.getText();
                    Stage stage = (Stage) loginMessage.getScene().getWindow();
                    Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
                    Scene scene = new Scene(viewStudentsRoot, 900, 600);
                    stage.setScene(scene);
                } catch (IOException e1) {
                    e.printStackTrace();
                }
            }
            return;
        }
        loginMessage.setText("Incorrect login!");
    }

    @FXML
    public void handleRegisterButton()
    {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleLoginButtonAction();
    }
}