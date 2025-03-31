module org.example.compilador {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.compilador to javafx.fxml;
    exports org.example.compilador;
}