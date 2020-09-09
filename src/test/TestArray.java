package test;

import main.Array;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestArray {
    Array myArray;
    @BeforeEach
    public void prerequisites() {
        myArray = new Array(10);

        for (int i = 0; i < 10; i++) {
            myArray.set(i, i+1);
        }
    }

    @Test
    public void testArray() {

        assertEquals(10, myArray.size());
        assertEquals(3, myArray.get(2));
        assertTrue(!myArray.contains(11));
        assertEquals(2, myArray.indexOf((Integer) 3));
        myArray.removeAt(3);
        assertEquals(9, myArray.size());
        assertEquals(5, myArray.get(3));
        assertTrue(!myArray.remove((Integer) 4));
    }
}
