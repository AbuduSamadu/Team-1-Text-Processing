package team1.testprocessing.Models;

import javafx.beans.property.StringProperty;


public record DataModel(
         StringProperty id,
         StringProperty name,
         StringProperty value
) {
}
