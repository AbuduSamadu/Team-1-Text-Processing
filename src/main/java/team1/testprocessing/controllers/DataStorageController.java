package team1.testprocessing.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import team1.testprocessing.Exceptions.FailedToAddItemException;
import team1.testprocessing.Exceptions.FailedToDeleteException;
import team1.testprocessing.Exceptions.FailedToUpdateItemException;
import team1.testprocessing.Models.DataModel;
import team1.testprocessing.service.DataStorageService;
import team1.testprocessing.service.FileImportService;
import team1.testprocessing.utils.AlertUtility;
import team1.testprocessing.utils.CollectionsFormatter;

import java.util.List;

public class DataStorageController {


    private final ObservableList<DataModel> dataList = FXCollections.observableArrayList();



    private Label titleLabel;
    @FXML
    private Button updateItemButton;
    @FXML
    private Button deleteItemButton;
    @FXML
    public TextField itemIdField;

    @FXML
    public TextField itemNameField;

      @FXML
    public TextField itemValueField;

    @FXML
    private Button addItemButton;

    @FXML
    private TableView<DataModel> dataTable;

    @FXML
    private TableColumn<DataModel,String> idColumn;

    @FXML
    private TableColumn<DataModel, String> nameColumn;
    @FXML
    private TableColumn<DataModel, String> valueColumn;
    private final FileImportService fileImportService= new FileImportService();
    private  final  ObservableList<DataModel> observableList= FXCollections.observableArrayList();

    public DataStorageController(){

    }

    public DataStorageController(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(data -> data.getValue().idProperty());
        nameColumn.setCellValueFactory(data -> data.getValue().nameProperty());
        valueColumn.setCellValueFactory(data -> data.getValue().valueProperty());
        dataTable.setItems(dataList);
        loadData();

    }

    private void loadData() {
        List <DataModel> items = DataStorageService.getInstance().getDataItems();
        dataList.setAll(items);
    }

    @FXML
    public void handleItemAdd() {
        String id = itemIdField.getText();
        String name = itemNameField.getText();
        String value =itemValueField.getText();

        if (id.isEmpty() || name.isEmpty() || value.isEmpty()) {
            AlertUtility.showErrorAlert("update failed",
                    "all fields required",
                    "fill all fields");
            return;
        }
        DataModel newItem = new DataModel(id, name, value);
        try {
            DataStorageService.getInstance().addNewItem(newItem);
            clearFields();
            dataList.add(newItem);
            AlertUtility.showInfoAlert("accepted",
                    "operation successful",
                    "item added");


        } catch (FailedToAddItemException e) {
            AlertUtility.showErrorAlert("Error",
                    "item by the id:" + id + "exists",
                    "try a different Id");
        }

    }


    @FXML
    public void handleItemUpdate() {
        String id = itemIdField.getText();
        String name = itemNameField.getText();
        String value = itemValueField.getText();

        if (id.isEmpty() || name.isEmpty() || value.isEmpty()) {
            AlertUtility.showErrorAlert("update failed",
                    "all fields required",
                    "fill all fields");
            return;
        }
        DataModel updateItem = new DataModel(id, name, value);
        try {
            DataStorageService.getInstance().updateItem(updateItem);
            loadData();
            clearFields();
            AlertUtility.showInfoAlert("accepted",
                    "operation successful",
                    "item updated");
        } catch (FailedToUpdateItemException e) {
            AlertUtility.showErrorAlert("Error",
                    "item by the id:" + id + "exists",
                    "try a different Id");
        }


    }


    @FXML
    public void handleDeleteItem() {
        DataModel selectedItem = dataTable.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertUtility.showErrorAlert("Error",
                    "no item selected",
                    "select an item to delete");
            return;
        }

        try {
            DataStorageService.getInstance().deleteItem(selectedItem);
            dataList.remove(selectedItem);
            clearFields();
            AlertUtility.showInfoAlert("Success",
                    "item deleted",
                    "operation complete");
        } catch (FailedToDeleteException e) {
            AlertUtility.showErrorAlert("Error",
                    "operation failed",
                    "item not found");
        }
    }

    @FXML
    public void handleRowSelection(MouseEvent event) {
        DataModel selectedItem = dataTable.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            itemIdField.setText(selectedItem.getId());
            itemNameField.setText(selectedItem.getName());
            itemValueField.setText(selectedItem.getValue());
        }

    }


    public void handleAddItemByFileImport() {
        try {
            var dataItems = fileImportService.extractItemsFromFile(addItemButton);
            dataItems.forEach(data->DataStorageService.getInstance().addNewItem(data));
            AlertUtility.showInfoAlert("accepted",
                    "operation successful",
                    "items successfully added from file");
                     loadData();

        } catch (Exception e) {
            AlertUtility.showErrorAlert("File Error",
                    "error occurred  while reading the file",
                    "try again");
        }

    }


    @FXML
    public void handleFilterByKeyword() {
        String keyword = itemNameField.getText();
        List<DataModel> filteredItems = CollectionsFormatter.filterByKeyword(dataList, keyword);
        dataTable.setItems(FXCollections.observableArrayList(filteredItems));
    }

    @FXML
    public void handleSortByName() {
        boolean ascending = true; // or false for descending
        List<DataModel> sortedItems = CollectionsFormatter.sortByName(dataList, ascending);
        dataTable.setItems(FXCollections.observableArrayList(sortedItems));
    }

    @FXML
    private void clearFields() {
        itemIdField.clear();
        itemNameField.clear();
        itemValueField.clear();
    }


}
