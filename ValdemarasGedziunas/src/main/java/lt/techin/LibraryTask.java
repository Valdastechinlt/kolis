package lt.techin;

import lt.techin.library.Book;
import lt.techin.library.BookCatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class LibraryTask implements BookCatalog {

    @Override
    public void addBook(Book book) {

    }

    @Override
    public Book getBookByIsbn(String s) {
        return null;
    }

    @Override
    public List<Book> searchBooksByAuthor(String s) {
        return null;
    }

    @Override
    public int getTotalNumberOfBooks() {
        return 0;
    }

    @Override
    public boolean isBookInCatalog(String s) {
        return false;
    }

    @Override
    public boolean isBookAvailable(String s) {
        return false;
    }

    @Override
    public Book findNewestBookByPublisher(String s) {
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
