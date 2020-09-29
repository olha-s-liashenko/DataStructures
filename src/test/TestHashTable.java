package test;

import main.HashTable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestHashTable {

    private class Contact {
        String name = "";
        String tel = "";

        public Contact(String name, String tel) {
            this.name = name;
            this.tel = tel;
        }
    }

    @Test
    public void testHashTable() {
        String[] names = {"Adel", "Bob", "David", "Eva", "Helen", "Kate", "Luke", "Mary", "Nathan", "Peter", "Sarah", "Timothy", "Yannis"};
        String[] tels = {"0771234532", "0453424323", "0785372348", "0785473847", "0674937584", "0674958767", "0657493847", "0875647364", "0896756432", "0476584756", "0908768574", "0785647343", "0675849876"};
        Contact[] contacts = new Contact[names.length];
        for (int i = 0; i < contacts.length; i++) {
            contacts[i] = new Contact(names[i], tels[i]);
        }


        HashTable<String, String> contactsTable = new HashTable<>();
        for (int i = 0;  i < contacts.length; i++) {
            contactsTable.put(contacts[i].name, contacts[i].tel);
        }

        assertEquals(13, contactsTable.size());
        assertEquals("0908768574", contactsTable.get("Sarah"));

        Object prevEntry = null;
        System.out.println(contactsTable.toString());

        for (Object entry : contactsTable) {
            System.out.println(entry.toString());
            assertTrue(entry != null);
        }
    }
}
