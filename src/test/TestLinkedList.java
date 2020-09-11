package test;

import main.LinkedList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLinkedList {

    @Test
    public void testLinkedList() {
        LinkedList llist = new LinkedList();
        assertEquals(0, llist.size());
        llist.addNode(2);
        assertEquals(1, llist.size());
        llist.addNodeAtStart(1);
        assertTrue(llist.contains(1));
        assertEquals(0, llist.indexOf(1));
        llist.addNodeAt(3, 2);
        assertEquals(2, llist.indexOf(3));
        assertEquals("1 -> 2 -> 3", llist.print());
        assertEquals(2, llist.removeNodeAt(1));
        llist.removeNode();
        assertEquals(1, llist.size());
        llist.removeNodeAtStart();
        assertTrue(!llist.contains(1));
        assertEquals(0, llist.size());
    }
}
