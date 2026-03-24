module org.example.logindashboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.logindashboard to javafx.fxml;
    exports org.example.logindashboard;
}