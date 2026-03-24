package com.example.demolistview.controllers;

import com.example.demolistview.services.PersonService;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.imageio.plugins.jpeg.JPEGImageReadParam;
import java.io.IOException;
import java.util.List;

public class AppControllers {
    @FXML
    private Label lblMsg;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAge;

    @FXML
    private ObservableList<String> data = FXCollections.observableArrayList();

    private PersonService service = new PersonService();

    @FXML
    public void initialize(){
        listView.getSelectionModel().selectedItemProperty().addListener( (observable, oldValue, newValue) ->{
            loadDataToForm(newValue);
        });
        loadFromFile();

        listView.setItems(data);
    }

    @FXML
    public void onAdd(){
        String name = txtName.getText();
        String email = txtEmail.getText();
        int age = 0;
        try {
            age = Integer.parseInt(txtAge.getText());
            service.addPerson(name,email,age);
            lblMsg.setStyle("-fx-text-fill: green");
            lblMsg.setText("Persona creada con exito.");
            txtEmail.clear();
            txtName.clear();
            txtAge.clear();
        }catch (IOException IOException){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con el archivo.");
        }catch (IllegalArgumentException argumentException){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con los datos.");
        }

    }

    @FXML
    public void onReload(){
        loadFromFile();
    }

    @FXML
    public void onUpdate(){
        String name = txtName.getText();
        String email = txtEmail.getText();
        int age = 0;
        try {
            int index = listView.getSelectionModel().getSelectedIndex();
            age = Integer.parseInt(txtAge.getText());
            service.updateInfo(index, name,email,age);
            loadFromFile();
            lblMsg.setStyle("-fx-text-fill: green");
            lblMsg.setText("Persona creada con exito.");
            txtEmail.clear();
            txtName.clear();
            txtAge.clear();
            loadFromFile();
        }catch (IOException IOException){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con el archivo.");
        }catch (IllegalArgumentException argumentException){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con los datos.");
        }

    }

    private void loadFromFile(){
        try{
            List<String> items = service.loadDataForListView();
            data.setAll(items);
            lblMsg.setStyle("-fx-text-fill: green");
            lblMsg.setText("Datos cargados con exito");
        }catch (IOException e){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error al cargar los archivos.");
        }
    }
    private void loadDataToForm(String data){
        String[] parts = data.split(" - ");
        txtName.setText(parts[0]);
        txtEmail.setText(parts[1]);
        txtAge.setText(parts[2]);
    }

    @FXML
    public void onDelete(){

        int age = 0;
        try {
            int index = listView.getSelectionModel().getSelectedIndex();

            service.deletePerson(index);

            lblMsg.setStyle("-fx-text-fill: green");
            lblMsg.setText("Persona eliminada con exito.");
            txtEmail.clear();
            txtName.clear();
            txtAge.clear();
            try{
                List<String> items = service.loadDataForListView();
                data.setAll(items);
               listView.setItems(data);
            }catch (IOException e){

            }
        }catch (IOException e){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con el archivo.");
        }catch (IllegalArgumentException e){
            lblMsg.setStyle("-fx-text-fill: red");
            lblMsg.setText("Error con los datos.");
        }
    }
}
