package team1.testprocessing.service;

import team1.testprocessing.Models.DataModel;
import team1.testprocessing.utils.AlertUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataStorageService {
    private  static  DataStorageService singleInstance= null;
    private DataStorageService dataStorageService;
    private List<DataModel> dataItems;

    private DataStorageService() {
      this.dataItems=new ArrayList<>();
    }


    public  static  synchronized  DataStorageService getInstance(){

        if (singleInstance==null){
            singleInstance= new DataStorageService();
        }
        return singleInstance;
    }

    public void addNewItem(DataModel model) {
        Optional<DataModel> optionalItem = findById(model.getId());

        optionalItem.ifPresentOrElse(id -> {
                    AlertUtility.showErrorAlert("failed to add data ",
                            "item with id" + id + " already exists",
                            "please provide a different item ID");
                },
                () -> dataItems.add(model));
    }

    public void updateItem(DataModel model) {

        Optional<DataModel> existingItem = findById(model.getId());
        existingItem.ifPresentOrElse(item -> {
            item.setName(model.getName());
            item.setName(model.getName());
        }, () -> {
            AlertUtility.showErrorAlert("failed to update data",
                    "item not found",
                    "enter a valid item ");
        });
    }

    public void deleteItem(DataModel model) {

        Optional<DataModel> optionalItem = findById(model.getId());

        optionalItem.ifPresentOrElse( item-> dataItems.remove(item),
                ()-> {
                    AlertUtility.showErrorAlert("failed to add data ",
                            "item with  not found",
                            " provide a valid item Id");
                }
                );
    }

    public List<DataModel> getDataItems() {
        return new ArrayList<>(dataItems);
    }


    public Optional<DataModel> findById(String Id) {
        return dataItems.stream().filter(data -> data.getId().equals(Id)).findFirst();
    }
}
