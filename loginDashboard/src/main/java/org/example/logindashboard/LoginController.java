package org.example.logindashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;

    private LoginService loginService = new LoginService();

    public void userLogin(ActionEvent event){
        String userEmail = email.getText(); // convertir input en texto
        String userPassword = password.getText(); // Convertir input en texto

        String error = loginService.validarEmail(userEmail, userPassword);

        if (error != null){
            wrongLogin.setText(error);
        }else {
            goToDashboard(event);

        }
    }

    private void goToDashboard(ActionEvent event){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard-view.fxml")); //Precarga la pantalla

            Parent root = loader.load(); //Crea el dashboard en el nodo(base de la pantalla) raiz(base de la escena)

            DashBoardController dashboardController = loader.getController(); //Crea el controller de dashboard

            dashboardController.setUser(email.getText()); // cambia el username de dashboard

            Stage stage = (Stage)((Node)event.getSource()) //Asigna el evento del boton
                    .getScene() // de la escena
                    .getWindow(); // de la ventana(Stage)

            stage.setScene(new Scene(root)); // Cambia la escena a una nueva escena con la raiz que definimos antes
            stage.show(); // muestra la escena

        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
