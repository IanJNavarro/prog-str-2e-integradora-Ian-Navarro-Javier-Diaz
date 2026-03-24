package org.example.logindashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{ // Crea la pantalla para la aplicacion.
        //Crea el Stage en base a la direccion asignada
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/logindashboard/login-view.fxml"));
        //Instancia la escena
        Scene scene = new Scene(loader.load());

        stage.setTitle("LoginApp");  // Nombre al principio de ventana
        stage.setScene(scene);  // Asigna la scene de la stage
        stage.show();  // Muestra la stage
    }

    public static void main(String[] args){
        launch();
    }
}
