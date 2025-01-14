package team1.testprocessing.utils;

import org.junit.jupiter.api.Test;
import team1.testprocessing.Models.DataModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsFormatterTest {

    @Test
    void testFilterByKeyword_validKeyword() {
        DataModel item1 = new DataModel("apple", "fruit", "value");
        DataModel item2 = new DataModel("orange", "fruit", "value");
        DataModel item3 = new DataModel("carrot", "vegetable", "value");

        List<DataModel> items = Arrays.asList(item1, item2, item3);
        List<DataModel> result = CollectionsFormatter.filterByKeyword(items, "uit");

        assertEquals(2, result.size());
        assertTrue(result.contains(item1));
        assertTrue(result.contains(item2));
    }

    @Test
    void testFilterByKeyword_emptyKeyword() {
        DataModel item1 = new DataModel("apple", "fruit", "value");
        DataModel item2 = new DataModel("orange", "fruit", "value");

        List<DataModel> items = Arrays.asList(item1, item2);
        List<DataModel> result = CollectionsFormatter.filterByKeyword(items, "");

        assertEquals(0, result.size());
    }

    @Test
    void testFilterByKeyword_nullKeyword() {
        DataModel item1 = new DataModel("apple", "fruit", "value");
        DataModel item2 = new DataModel("orange", "fruit", "value");

        List<DataModel> items = Arrays.asList(item1, item2);
        List<DataModel> result = CollectionsFormatter.filterByKeyword(items, null);

        assertEquals(0, result.size());
    }

    @Test
    void testFilterByKeyword_noMatches() {
        DataModel item1 = new DataModel("apple", "fruit", "value");
        DataModel item2 = new DataModel("orange", "fruit", "value");

        List<DataModel> items = Arrays.asList(item1, item2);
        List<DataModel> result = CollectionsFormatter.filterByKeyword(items, "vegetable");

        assertEquals(0, result.size());
    }

    @Test
    void testSortByName_ascending() {
        DataModel item1 = new DataModel("1", "apple", "value");
        DataModel item2 = new DataModel("2", "orange", "value");
        DataModel item3 = new DataModel("3", "banana", "value");

        List<DataModel> items = Arrays.asList(item1, item2, item3);
        List<DataModel> result = CollectionsFormatter.sortByName(items, true);

        assertEquals(item1, result.get(0));
        assertEquals(item3, result.get(1));
        assertEquals(item2, result.get(2));
    }

    @Test
    void testSortByName_descending() {
        DataModel item1 = new DataModel("1", "apple", "value");
        DataModel item2 = new DataModel("2", "orange", "value");
        DataModel item3 = new DataModel("3", "banana", "value");

        List<DataModel> items = Arrays.asList(item1, item2, item3);
        List<DataModel> result = CollectionsFormatter.sortByName(items, false);

        assertEquals(item2, result.get(0));
        assertEquals(item3, result.get(1));
        assertEquals(item1, result.get(2));
    }

    @Test
    void testSortByName_emptyList() {
        List<DataModel> items = Collections.emptyList();
        List<DataModel> result = CollectionsFormatter.sortByName(items, true);
        assertTrue(result.isEmpty());
    }

    @Test
    void testSortByName_nullList() {
        List<DataModel> result = CollectionsFormatter.sortByName(null, true);
        assertTrue(result.isEmpty());
    }

}
