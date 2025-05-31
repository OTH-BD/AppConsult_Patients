package net.othmane.hd.appg_consult_patients;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
            Parent root = fxmlLoader.load();

            // Create and configure the scene
            Scene scene = new Scene(root, 600, 460);
            scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

            // Configure and show the stage
            stage.setTitle("Consultation Management Application");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            // Handle FXML loading errors
            showErrorAlert("FXML Loading Error",
                    "Could not load the main view file.",
                    "Details: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Handle other unexpected errors
            showErrorAlert("Application Error",
                    "An unexpected error occurred while starting the application.",
                    "Details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Shows an error alert dialog with the specified information
     * @param title The alert title
     * @param header The alert header text
     * @param content The alert content text
     */
    private void showErrorAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();

        // Optionally exit the application after the user closes the error dialog
        Platform.exit();
    }

    /**
     * Application entry point
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        try {
            launch(args);
        } catch (Exception e) {
            System.err.println("Fatal error during application launch:");
            e.printStackTrace();
        }
    }
}