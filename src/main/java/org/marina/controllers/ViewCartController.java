package org.marina.controllers;

import org.marina.exceptions.CartIsEmptyException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import org.marina.model.Order;
import org.marina.model.Product;
import org.marina.services.OrderService;
import org.marina.services.ProductService;

import java.io.IOException;


public class ViewCartController{


    @FXML
    private Text viewCartMessage;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        ObservableList<Product> data = tableView.getItems();
        for (int i=0;i<ClientController.comanda.getContor();i++) {
            data.add(new Product(ClientController.comanda.getOrder().get(i).getName(),
                                 ClientController.comanda.getOrder().get(i).getQuantity(),
                                 ProductService.getPrice(ClientController.comanda.getOrder().get(i).getName(),ClientController.comanda.getOrder().get(i).getQuantity())));
        }
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) viewCartMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSendOrderButton() {
        try {
            ClientController.comanda.setUser(LoginController.user);
            OrderService.placeOrder(ClientController.comanda);
            ClientController.comanda = new Order(ClientController.comanda.getUser());
        } catch (CartIsEmptyException e){
            viewCartMessage.setText("Cart is empty!");
            return;
        }

        try {
            Stage stage = (Stage) viewCartMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

