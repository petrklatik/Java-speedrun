package org.example;

import java.util.*;

class Library {
    private HashMap<Integer, Book> library = new HashMap<>();

    public void addBook(String title, String author, int isbn, boolean availability) {
        Book book = new Book(title, author, isbn, availability);
        library.put(book.getIsbn(), book);
    }

    public List<Book> searchBook(String keyword) {
        List<Book> results = new ArrayList<>();
        for (var book : library.values()) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> displayAll() {
        return new ArrayList<>(library.values());
    }

    public void updateAvailability(int isbn) {
        Book book = library.get(isbn);
        if (book != null) {
            book.setAvailability(!book.getAvailability());
        } else {
            System.out.println("Book with ISBN " + isbn + " not found.");
        }
    }
}

class Book {
    private String title;
    private String author;
    private int isbn;
    private boolean availability;

    public Book(String title, String author, int isbn, boolean availability) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.availability = availability;
    }

    public int getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
