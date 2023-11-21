import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {
    private BSTree<Integer> emptyTree;
    private BSTree<Integer> myTree;
    private BSTree<Integer> nonEmptyTree;

    @BeforeEach
    public void setUp() {
        emptyTree = new BSTree<>();
        myTree = new BSTree<>();
        nonEmptyTree = new BSTree<>();
        nonEmptyTree.insert(5);
        nonEmptyTree.insert(3);
        nonEmptyTree.insert(7);
        nonEmptyTree.insert(2);
        nonEmptyTree.insert(4);
        nonEmptyTree.insert(6);
        nonEmptyTree.insert(8);
    }

    @Test
    public void BSTree() {
        assertEquals(0, emptyTree.getSize());
        assertNull(emptyTree.getRoot());

        assertEquals(0, myTree.getSize());
        assertNull(emptyTree.getRoot());
    }

    @Test
    public void getRootTest() {
        assertNull(emptyTree.getRoot());
        assertEquals(5, nonEmptyTree.getRoot().getKey());
        assertNull(myTree.getRoot());
        myTree.insert(1);
        assertEquals(1, myTree.getRoot().getKey());
    }

    @Test
    public void getSizeTest() {
        myTree.insert(5);
        myTree.insert(3);
        myTree.insert(7);
        myTree.insert(2);
        myTree.insert(4);
        myTree.insert(6);
        myTree.insert(8);
        assertEquals(7, myTree.getSize());
        assertEquals(0, emptyTree.getSize());
    }
    @Test
    public void insertTest() {
        assertTrue(emptyTree.insert(15));
        assertEquals(1, emptyTree.getSize());

        assertFalse(nonEmptyTree.insert(5));
        assertEquals(7, nonEmptyTree.getSize());

        assertTrue(nonEmptyTree.insert(9));
        assertEquals(8, nonEmptyTree.getSize());

        assertThrows(NullPointerException.class, () -> {
            emptyTree.findKey(null);
        });
    }

    @Test
    public void findKeyTest() {
        assertTrue(nonEmptyTree.findKey(5));
        assertFalse(nonEmptyTree.findKey(10));
        emptyTree.insert(10);
        assertTrue(emptyTree.findKey(10));
        assertFalse(emptyTree.findKey(100));

        assertThrows(NullPointerException.class, () -> {
            emptyTree.findKey(null);
        });
    }

    @Test
    public void insertDataTest() {
        emptyTree.insert(10);
        emptyTree.insertData(10, 20);
        LinkedList<Integer> dataList = emptyTree.findDataList(10);
        assertTrue(dataList.contains(20));

        emptyTree.insertData(10, 30);
        dataList = emptyTree.findDataList(10);
        assertEquals(2, dataList.size());
        assertTrue(dataList.contains(20));
        assertTrue(dataList.contains(30));

        emptyTree.insert(15);
        emptyTree.insertData(15, 5);
        LinkedList<Integer> dataList1 = emptyTree.findDataList(15);
        assertTrue(dataList1.contains(5));
        assertFalse(dataList.contains(5));

        assertThrows(NullPointerException.class, () -> {
            emptyTree.insertData(10, null);
        });
        assertThrows(NullPointerException.class, () -> {
            emptyTree.insertData(null, 13);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            emptyTree.insertData(4, 10);
        });
    }
    @Test
    public void findDataListTest() {
        nonEmptyTree.insertData(3, 30);
        LinkedList<Integer> dataList =  nonEmptyTree.findDataList(3);
        assertEquals(1, dataList.size());

        assertTrue(dataList.contains(30));
        nonEmptyTree.insertData(3, 60);
        assertEquals(2, dataList.size());
        assertTrue(dataList.contains(60));

        myTree.insert(5);
        myTree.insertData(5, 14);
        LinkedList<Integer> dataList1 =  myTree.findDataList(5);
        assertEquals(1, dataList1.size());
        assertTrue(dataList1.contains(14));

        assertThrows(NullPointerException.class, () -> {
            emptyTree.insertData(null, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            emptyTree.insertData(10, 10);
        });
    }

    @Test
    public void findHeightTest() {
        assertEquals(-1, emptyTree.findHeight());
        assertEquals(2, nonEmptyTree.findHeight());
        assertEquals(-1, myTree.findHeight());

        myTree.insert(1);
        myTree.insert(2);
        myTree.insert(3);
        myTree.insert(4);
        myTree.insert(5);
        myTree.insert(6);
        myTree.insert(7);
        myTree.insert(8);
        assertEquals(7, myTree.findHeight());

        emptyTree.insert(15);
        emptyTree.insert(10);
        emptyTree.insert(20);
        emptyTree.insert(8);
        emptyTree.insert(12);
        emptyTree.insert(4);
        emptyTree.insert(7);
        emptyTree.insert(3);
        assertEquals(4, emptyTree.findHeight());
    }
}