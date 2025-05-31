package com.windowsxp.crucigrama.crucigramagui_xp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class CrucigramaApp extends Application {

    @FXML private TextArea inputPalabras;
    @FXML private TextField inputTamanio;
    @FXML private GridPane grid;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/windowsxp/crucigrama/crucigramagui_xp/crucigrama_layout.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Generador de Crucigrama");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    @FXML
    private void generarCrucigrama() {
        String[] palabras = inputPalabras.getText().split(",");

        if (palabras.length < 4) {
            mostrarAlerta("Error", "Debes ingresar al menos 4 palabras.");
            return;
        }

        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].trim().toLowerCase();

            // Validar 10 caract
            if (palabras[i].length() > 10) {
                mostrarAlerta("Error", "La palabra '" + palabras[i] + "' es demasiado larga (max. 10 letras).");
                return;
            }
            if (palabras[i].length() < 3) {
                mostrarAlerta("Error", "La palabra '" + palabras[i] + "' es demasiado corta (min. 3 letras).");
                return;
            }
        }

        int tamanio = calcularTamañoAdecuado(palabras);

        for (String palabra : palabras) {
            if (palabra.length() > tamanio) {
                mostrarAlerta("Error", "La palabra '" + palabra + "' es demasiado larga para el tablero.");
                return;
            }
        }

        Crucigrama crucigrama = new Crucigrama(palabras, tamanio);
        mostrarMatriz(crucigrama.matriz);
    }

    private void mostrarMatriz(char[][] matriz) {
        grid.getChildren().clear();

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                char c = matriz[i][j];
                Label label = new Label(c == ' ' ? " " : String.valueOf(c));
                label.setMinSize(25, 25);
                label.setMaxSize(25, 25);

                if (c != ' ') {
                    label.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-font-weight: bold;");
                } else {
                    label.setStyle("-fx-background-color: transparent;");
                }
                grid.add(label, j, i);
            }
        }
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private int calcularTamañoAdecuado(String[] palabras) {
        int tamaño = 0;
        for (String palabra : palabras) {
            tamaño += palabra.length();
        }
        tamaño = tamaño / 2;
        return Math.max(tamaño, 10);
    }
}
