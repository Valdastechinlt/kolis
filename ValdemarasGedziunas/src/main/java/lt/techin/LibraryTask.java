package lt.techin;

import lt.techin.library.Author;
import lt.techin.library.Book;
import lt.techin.library.BookCatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LibraryTask implements BookCatalog {

    Map<String,Book> books = new HashMap<>();
    @Override
    public void addBook(Book book) {
        if (!books.containsKey(book.getIsbn())){
            books.put(book.getIsbn(),book);
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    @Override
    public List<Book> searchBooksByAuthor(String authorName) {
       return  books.values().stream().filter(b-> b.getAuthors().stream().anyMatch(a->a.getName().equals(authorName))).toList();

    }


    @Override
    public int getTotalNumberOfBooks() {
        return books.size();
    }

    @Override
    public boolean isBookInCatalog(String isbn) {
        if (books.containsKey(isbn)){
            return true;
        }
        return false;
    }

    @Override
    public boolean isBookAvailable(String bookTitle) {
        for (String key: books.keySet()) {
            if (books.get(key).isAvailable()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Book findNewestBookByPublisher(String bookTitle) {
        return null;
    }

    @Override
    public List<Book> getSortedBooks() {
        return null;
    }

    @Override
    public Map<String, List<Book>> groupBooksByPublisher() {
        return null;
    }

    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {
        return null;
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return null;
    }
}
