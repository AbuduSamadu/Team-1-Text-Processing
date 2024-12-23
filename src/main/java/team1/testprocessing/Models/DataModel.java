package team1.testprocessing.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DataModel {

   private final  StringProperty id;
   private  final StringProperty name;
   private final  StringProperty value;

    public DataModel(String id, String name, String value) {
        this.id =  new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.value= new SimpleStringProperty(value);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "id='" + id.get() + '\'' +
                ", name='" + name.get() + '\'' +
                ", value='" + value.get() + '\'' +
                '}';
    }
}
