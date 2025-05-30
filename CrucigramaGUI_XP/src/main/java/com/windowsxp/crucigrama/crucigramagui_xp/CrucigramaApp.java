package com.windowsxp.crucigrama.crucigramagui_xp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CrucigramaApp extends Application {

    private TextArea inputPalabras;
    private TextField inputTamanio;
    private GridPane grid;

    public static void main(String[] args) {
        launch(args); // Ejecuta la aplicación JavaFX
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Generador de Crucigrama");

        // Área de ingreso de palabras
        inputPalabras = new TextArea();
        inputPalabras.setPromptText("Ingresa palabras separadas por coma (mín. 4)");

        // Campo de tamaño del crucigrama
        //inputTamanio = new TextField();
        //inputTamanio.setPromptText("Tamaño mínimo 10 (opcional)");
        inputTamanio = new TextField();
        inputTamanio.setVisible(false);

        // Botón generar
        Button generarBtn = new Button("Generar Crucigrama");
        generarBtn.setOnAction(e -> generarCrucigrama());

        // Cuadro para mostrar la matriz
        grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        VBox layout = new VBox(10, inputPalabras, inputTamanio, generarBtn, new ScrollPane(grid));
        layout.setPadding(new Insets(15));

        primaryStage.setScene(new Scene(layout, 600, 600));
        primaryStage.show();
    }

    private void generarCrucigrama() {
        String[] palabras = inputPalabras.getText().split(",");
        if (palabras.length < 4) {
            mostrarAlerta("Error", "Debes ingresar al menos 4 palabras.");
            return;
        }

        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].trim().toLowerCase(); // limpieza básica
        }

        int tamanio = calcularTamañoAdecuado(palabras);

        // Validación de largo de palabras
        for (String palabra : palabras) {
            if (palabra.length() > tamanio) {
                mostrarAlerta("Error", "La palabra '" + palabra + "' es demasiado larga para el tablero de tamaño " + tamanio + ".");
                return;
            }
        }

        Crucigrama crucigrama = new Crucigrama(palabras, tamanio);
        mostrarMatriz(crucigrama.matriz);
    }

    private void mostrarMatriz(char[][] matriz) {
        grid.getChildren().clear(); // Limpia antes de mostrar

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                char c = matriz[i][j];
                Label label = new Label((matriz[i][j] == ' ' ? " " : String.valueOf(matriz[i][j])));
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
            tamaño += palabra.length(); // suma todas las longitudes
        }
        tamaño = tamaño / 2; // tamaño base estimado
        return Math.max(tamaño, 10); // mínimo 10
    }
}
