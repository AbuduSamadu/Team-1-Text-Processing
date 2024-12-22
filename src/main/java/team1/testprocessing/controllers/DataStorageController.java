package team1.testprocessing.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import team1.testprocessing.Models.DataModel;
import team1.testprocessing.service.DataStorageService;
import team1.testprocessing.utils.AlertUtility;

import java.util.List;

public class DataStorageController {


    private ToggleButton toggleButton;
    private Label titleLabel;
    private Button updateButton;
    private  Button deleteButton;

    @FXML
    private TextField IdField;

    @FXML
    private TextField nameField;

    @FXML
    private  TextField valueField;

    @FXML
    private Button addItemButton;

    @FXML
    private TableView<DataModel> dataTable;

    private TableColumn<DataModel,String> IdColumn;

    private TableColumn<DataModel,String> nameColumn;

    private TableColumn<DataModel,String> valueColumn;

    private   DataStorageService storageService;
   private final  ObservableList<DataModel> observableList = FXCollections.observableArrayList();


    @FXML

    public  void init(){
  IdColumn.setCellValueFactory( data->data.getValue().id());
  nameColumn.setCellValueFactory(data->data.getValue().name());
  valueColumn.setCellValueFactory(data->data.getValue().value());
  dataTable.setItems(observableList);
  loadData();

    }

    private  void loadData(){
        List<DataModel> items= storageService.getInstance().getDataItems();
        observableList.setAll(items);
    }

    @FXML
    public  void handleAddItem(){
       String id= IdField.getText();
       String name= nameField.getText();
       String value=valueField.getText();

       if( id.isEmpty()||name.isEmpty()||value.isEmpty()){
           AlertUtility.showErrorAlert("update failed","all fields required", "fill all fields");
           return;
       }
       //DataModel newItem= new DataModel(id,name,value)



    }
}
