package team1.testprocessing.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import team1.testprocessing.Models.DataModel;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DataStorageServiceTest {

    private DataStorageService dataStorageService;
    private DataModel dataModel1;
    private DataModel dataModel2;

    @BeforeEach
    void setUp() {
        dataStorageService = DataStorageService.getInstance();
        dataModel1 = new DataModel("1", "Item 1", "value");
        dataModel2 = new DataModel("2", "Item 2", "value");
    }

    @BeforeEach
    public  void  resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instance= DataStorageService.class.getDeclaredField("singleInstance");
        instance.setAccessible(true);
        instance.set(null,null);

    }

    @Test
    void testAddNewItem_Success() {
        dataStorageService.addNewItem(dataModel1);

        List<DataModel> dataItems = dataStorageService.getDataItems();
        assertEquals(1, dataItems.size());
        assertTrue(dataItems.contains(dataModel1));
    }

    @Test
    void testUpdateItem_Success() {
        dataStorageService.addNewItem(dataModel1);

        DataModel updatedModel = new DataModel("1", "Updated Item", "value");
        dataStorageService.updateItem(updatedModel);

        Optional<DataModel> updatedItem = dataStorageService.findById("1");
        assertTrue(updatedItem.isPresent());
        assertEquals("Updated Item", updatedItem.get().getName());
    }

    @Test
    void testDeleteItem_Success() {
        dataStorageService.addNewItem(dataModel1);
        dataStorageService.deleteItem(dataModel1);

        List<DataModel> dataItems = dataStorageService.getDataItems();
        assertEquals(0, dataItems.size());
    }

    @Test
    void testFindById_Found() {
        dataStorageService.addNewItem(dataModel1);

        Optional<DataModel> foundItem = dataStorageService.findById("1");
        assertTrue(foundItem.isPresent());
        assertEquals("Item 1", foundItem.get().getName());
    }

    @Test
    void testFindById_NotFound() {
        Optional<DataModel> foundItem = dataStorageService.findById("999");
        assertFalse(foundItem.isPresent());
    }
}
