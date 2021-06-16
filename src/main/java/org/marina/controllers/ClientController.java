package org.marina.controllers;

import org.marina.exceptions.ItemIsNotInTheCart;
import org.marina.exceptions.NotEnoughQuantity;
import org.marina.exceptions.ProductDoesNotExist;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.marina.model.Order;
import org.marina.model.Product;

import java.io.IOException;

public class ClientController {

    public static Order comanda=new Order();

    @FXML
    private Text clientMessage;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;

    @FXML
    public void handleViewProducts() {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("viewProductsClient.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddToCart() {
        if(productNameField.getText() == null || productNameField.getText().isEmpty()){
            clientMessage.setText("Please type in a name!");
            return;
        }
        if(quantityField.getText() == null || quantityField.getText().isEmpty()){
            clientMessage.setText("Please type in the quantity!");
            return;
        }
        try
        {
            Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException ex) {
            clientMessage.setText("Quantity must be an Integer!");
            return;
        }
        try {
            comanda.addProduct(new Product(productNameField.getText(), Integer.parseInt(quantityField.getText())));
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (ProductDoesNotExist e){
            clientMessage.setText(e.getMessage());
        } catch (NotEnoughQuantity e) {
            clientMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemoveProduct() {
        if(productNameField.getText() == null || productNameField.getText().isEmpty()){
            clientMessage.setText("Please type in a name!");
            return;
        }
        try {
            try
            {
                Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                clientMessage.setText("Quantity must be an Integer!");
                return;
            }
            comanda.removeProduct(new Product(productNameField.getText(), 1));
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (ItemIsNotInTheCart e) {
            clientMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleViewCart() {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDisconnect()
    {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleAddToCart();
    }
}
