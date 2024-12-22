package team1.testprocessing.service;
import team1.testprocessing.Models.DataModel;
import team1.testprocessing.utils.AlertUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStorageService {

    private   DataStorageService dataStorageService;
    private  List<DataModel> dataItems;

    public  Object instance;

    private DataStorageService() {
        this.dataItems = new ArrayList<>();


    }

    public  DataStorageService getInstance(){
        return dataStorageService= (dataStorageService==null)? new DataStorageService():dataStorageService;

    }


    public void addNewItem(DataModel model) {
        Optional<DataModel> optionalItem = findById(String.valueOf(model.id()));

        optionalItem.ifPresentOrElse(id -> {
                    AlertUtility.showErrorAlert("failed to add data ",
                            "item with id" + id + " already exists",
                            "please provide a different item ID");
                },
                () -> dataItems.add(model));
    }

    public  void updateItem(DataModel model){

         Optional<DataModel> existingItem=findById(String.valueOf(model));
        existingItem.ifPresentOrElse(item->{
            item.name().set(model.name().toString());
            item.name().set(model.value().toString());
        }, ()->{
            AlertUtility.showErrorAlert("failed to update data",
                    "item not found",
                    "enter a valid item " );
        });
    }

    public void deleteItem( DataModel model){

        Optional<DataModel> optionalItem = findById(String.valueOf(model.id()));

        optionalItem.ifPresentOrElse(id -> {
                    AlertUtility.showErrorAlert("failed to add data ",
                            "item with id" + id + " not found",
                            " provide a valid item");
                },
                () -> dataItems.remove(model));
    }

    public  List<DataModel> getDataItems(){
         return  new ArrayList<>(dataItems);
    }


    public Optional<DataModel> findById(String Id) {
        return dataItems.stream().filter(data -> data.id().toString().equals(Id)).findFirst();
    }
}
