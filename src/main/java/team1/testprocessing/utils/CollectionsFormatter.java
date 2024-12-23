package team1.testprocessing.utils;

import team1.testprocessing.Models.DataModel;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CollectionsFormatter {

    public static List<DataModel> filterByKeyword(List<DataModel> items, String keyword) {
        if (items == null || keyword == null || keyword.isEmpty()) {
            return Collections.emptyList();
        }
        return items.stream()
                .filter(item -> item.getName().contains(keyword) || item.getValue().contains(keyword))
                .collect(Collectors.toList());
    }


    public static List<DataModel> sortByName(List<DataModel> items, boolean ascending) {
        if (items == null) {
            return Collections.emptyList();
        }
        return items.stream()
                .sorted(ascending ? Comparator.comparing(DataModel::getName) : Comparator.comparing(DataModel::getName).reversed())
                .collect(Collectors.toList());
    }

}
