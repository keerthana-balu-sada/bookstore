// src/test/java/com/example/bookstore/model/BookTest.java

package com.example.bookstore.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookTest {

    @Test
    public void testGettersAndSetters() {
        Book book = new Book();
        
        // Set the values
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");

        // Test getters
        assertEquals(1L, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
    }
}
