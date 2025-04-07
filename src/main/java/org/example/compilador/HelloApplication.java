package org.example.compilador;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.stage.FileChooser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;



import java.io.IOException;

public class HelloApplication extends Application {
    private MenuBar mnbCompilador;
    private Menu mnArchivo, mnEditar, mnVista, mnNavegacion, mnCodigo, mnConstruir, mnAjustes;
    private BorderPane bdpPanel;
    private VBox vbPrincipal, vbLateral;
    private HBox hbPrincipal;
    private TextArea txtCodigoFuente, txtSalida;
    private MenuItem mitAbrirArchivo;
    private MenuItem mitGuardarArchivo;


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

        mitAbrirArchivo.setOnAction(e -> abrirArchivo());
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
    
    private void abrirArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir archivo de código fuente");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Archivos de texto", "*.aaa"),
            new FileChooser.ExtensionFilter("Todos los archivos", "*.*")
        );
    
        File archivoSeleccionado = fileChooser.showOpenDialog(null);
        if (archivoSeleccionado != null) {
            try (BufferedReader lector = new BufferedReader(new FileReader(archivoSeleccionado))) {
                StringBuilder contenido = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    contenido.append(linea).append("\n");
                }
                txtCodigoFuente.setText(contenido.toString());
            } catch (IOException e) {
                txtSalida.setText("Error al leer el archivo: " + e.getMessage());
            }
        }
    }

    private void guardarArchivo() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo de código fuente");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos de código AAA", "*.aaa")
        );
        fileChooser.setInitialFileName("codigo.aaa");
    
        File archivo = fileChooser.showSaveDialog(null);
        if (archivo != null) {
            try (PrintWriter escritor = new PrintWriter(new FileWriter(archivo))) {
                escritor.write(txtCodigoFuente.getText());
                txtSalida.setText("Archivo guardado correctamente en: " + archivo.getAbsolutePath());
            } catch (IOException e) {
                txtSalida.setText("Error al guardar el archivo: " + e.getMessage());
            }
        }
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

        mitGuardarArchivo = new MenuItem("Guardar Archivo");
        mitGuardarArchivo.setOnAction(e -> guardarArchivo());
        mnArchivo.getItems().addAll(mitAbrirArchivo, mitGuardarArchivo);

        mnbCompilador = new MenuBar();
        mnbCompilador.getMenus().addAll(mnArchivo, mnEditar, mnVista, mnNavegacion, mnCodigo, mnConstruir, mnAjustes);
    }

    public static void main(String[] args) {
        launch();
    }
}

