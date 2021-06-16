package org.marina.controllers;

import org.marina.exceptions.ProductDoesNotExist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.marina.services.ProductService;

import java.io.IOException;

public class RemoveProductController{

    @FXML
    private Text removeProductMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField quantityField;

    @FXML
    public void handleRemoveProductButton() {
        if(nameField.getText() == null || nameField.getText().isEmpty()){
            removeProductMessage.setText("Please type in the Name!");
            return;
        }
        if(codeField.getText() == null || codeField.getText().isEmpty()){
            removeProductMessage.setText("Please type in the Code!");
            return;
        }
        try {
            ProductService.removeProduct(nameField.getText(), codeField.getText());
            removeProductMessage.setText("Product removed successfully!");
            Stage stage = (Stage) removeProductMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("removeProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(ProductDoesNotExist e) {
            removeProductMessage.setText("This product doesn't exist");
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) removeProductMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleRemoveProductButton();
    }
}
