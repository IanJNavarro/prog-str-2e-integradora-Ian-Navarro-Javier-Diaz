package org.example.logindashboard;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashBoardController {
    @FXML
    private Label userId;

    public void setUser(String username){
        userId.setText(username);
    }
}
