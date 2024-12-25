package team1.testprocessing.service;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import team1.testprocessing.Models.DataModel;
import team1.testprocessing.utils.LoggerUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileImportService {
    public List<DataModel> extractItemsFromFile(Button button) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Text File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"), new FileChooser.ExtensionFilter("All files", "*.*"));

        List<DataModel> items = new ArrayList<>();
        Stage stage = (Stage) button.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            try {
                String content = new String(Files.readAllBytes(Paths.get(selectedFile.toURI())));

                String[] lines = content.split("\n");
                for (String line : lines) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        DataModel item = new DataModel(parts[0], parts[1], parts[2]);
                        items.add(item);
                    }
                }
            } catch (IOException e) {
                LoggerUtility.logError(e);
            }
        }

        return items;
    }

}
