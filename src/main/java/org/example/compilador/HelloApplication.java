package org.example.compilador;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private MenuBar mnbCompilador;
    private Menu mnArchivo, mnEditar, mnVista, mnNavegacion, mnCodigo, mnConstruir, mnAjustes;
    private BorderPane bdpPanel;
    private VBox vbPrincipal, vbLateral;
    private HBox hbPrincipal;
    private TextArea txtCodigoFuente, txtSalida;
    private MenuItem mitAbrirArchivo;

    @Override
    public void start(Stage stage) throws IOException {
        crearMenuBar();
        crearEntorno();
        bdpPanel = new BorderPane();
        bdpPanel.setTop(mnbCompilador);
        bdpPanel.setCenter(vbPrincipal);
        Scene scene = new Scene(bdpPanel, 320, 240);
        stage.setTitle("Compilador");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    private void crearEntorno() {
        txtCodigoFuente = new TextArea();
        txtCodigoFuente.setPrefHeight(700);
        txtCodigoFuente.setPromptText("Codigo de fuente");
        txtSalida = new TextArea();
        txtSalida.setPromptText("Salida");
        vbLateral = new VBox();
        vbPrincipal = new VBox(txtCodigoFuente, txtSalida);
        hbPrincipal = new HBox(vbLateral, vbPrincipal);
    }

    private void crearMenuBar (){
        mitAbrirArchivo = new MenuItem("Abrir Archivo");

        mnArchivo = new Menu("Archivo");
        mnArchivo.getItems().add(mitAbrirArchivo);
        mnEditar = new Menu("Editar");
        mnVista = new Menu("Vista");
        mnNavegacion = new Menu("Navegacion");
        mnCodigo = new Menu("Codigo");
        mnConstruir = new Menu("Construir");
        mnAjustes = new Menu("Ajustes");

        mnbCompilador = new MenuBar();
        mnbCompilador.getMenus().addAll(mnArchivo, mnEditar, mnVista, mnNavegacion, mnCodigo, mnConstruir, mnAjustes);
    }

    public static void main(String[] args) {
        launch();
    }
}

