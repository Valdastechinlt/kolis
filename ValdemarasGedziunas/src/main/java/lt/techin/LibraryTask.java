package lt.techin;

import lt.techin.library.Author;
import lt.techin.library.Book;
import lt.techin.library.BookCatalog;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

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
        return books.containsKey(isbn);
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
    public Book findNewestBookByPublisher(String bookPublisher) {
        List<Book> publisherBooks = books.values().stream().filter(b -> b.getPublisher().equals(bookPublisher)).toList();
        Book myBook = publisherBooks.get(0);
        Book newestBook = new Book();
        for (Book book:publisherBooks) {
            if (book.getPublicationYear() > myBook.getPublicationYear()){
                newestBook = book;
            }
        }
        return newestBook;
    }

    @Override
    public List<Book> getSortedBooks() {
        return books.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Book>> groupBooksByPublisher() {
        return books.values().stream().collect(groupingBy(Book::getPublisher));
    }

    @Override
    public List<Book> filterBooks(Predicate<Book> predicate) {
        return books.values().stream().filter(predicate).collect(Collectors.toList());
    }

    @Override
    public BigDecimal calculateTotalPrice() {
        return books.values()
                .stream()
                .map(Book::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
}
