package org.marina.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.marina.exceptions.ProductAlreadyExistsException;
import org.marina.services.ProductService;

import java.io.IOException;

public class AddProductController{

    @FXML
    private Text addProductMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField priceField;

    @FXML
    public void handleAddProductButton() {
        if(nameField.getText() == null || nameField.getText().isEmpty()){
            addProductMessage.setText("Please type in the Name!");
            return;
        }
        if(categoryField.getText() == null || categoryField.getText().isEmpty()){
            addProductMessage.setText("Please type in the Category!");
            return;
        }
        if(codeField.getText() == null || codeField.getText().isEmpty()){
            addProductMessage.setText("Please type in the Code!");
            return;
        }
        if(quantityField.getText() == null || quantityField.getText().isEmpty()){
            addProductMessage.setText("Please type in the Quantity!");
            return;
        }
        try
        {
            Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException ex) {
            addProductMessage.setText("Quantity must be an Integer!");
            return;
        }
        if(priceField.getText() == null || priceField.getText().isEmpty()){
            addProductMessage.setText("Please type in the Price!");
            return;
        }
        try
        {
            Integer.parseInt(priceField.getText());
        } catch (NumberFormatException ex) {
            addProductMessage.setText("Price must be an Integer!");
            return;
        }

        try {
            ProductService.addProduct(nameField.getText(), categoryField.getText(), codeField.getText(),
                    Integer.parseInt(quantityField.getText()), Integer.parseInt(priceField.getText()));
            addProductMessage.setText("Product added successfully!");
            Stage stage = (Stage) addProductMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("addProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
        catch (ProductAlreadyExistsException e) {
            addProductMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) addProductMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleAddProductButton();
    }
}
