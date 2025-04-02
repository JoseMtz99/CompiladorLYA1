module org.example.compilador {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.compilador to javafx.fxml;
    exports org.example.compilador;
}