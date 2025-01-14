package team1.testprocessing.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtility {

    public static void showInfoAlert(String title, String header, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, header, message);
    }

    public static void showWarningAlert(String title, String header, String message) {
        showAlert(Alert.AlertType.WARNING, title, header, message);
    }

    public static void showErrorAlert(String title, String header, String message) {
        showAlert(Alert.AlertType.ERROR, title, header, message);
    }

    private static void showAlert(Alert.AlertType alertType, String title, String header, String message) {
        Alert alert = new Alert(alertType, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(header);

        alert.setResizable(true);
        alert.showAndWait();
    }

}
