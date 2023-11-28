package lt.techin;
import lt.techin.library.Book;
import lt.techin.library.BookCatalog;
import lt.techin.library.BookNotFoundException;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class LibraryTask implements BookCatalog {

    Map<String,Book> books = new HashMap<>();
    @Override
    public void addBook(Book book) {
        if (book == null){
            throw new IllegalArgumentException();
        }
        if (book.getTitle() == null || book.getIsbn() == null ||
                book.getIsbn().isEmpty() || book.getTitle().isEmpty()){
            throw new IllegalArgumentException();
        }
        if (!books.containsKey(book.getIsbn())){
            books.put(book.getIsbn(),book);
        }
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        if(books.values().stream().noneMatch(book -> book.getIsbn().equals(isbn))){
            throw new BookNotFoundException("");
        }
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
        if(books.values().stream().noneMatch(book -> book.getPublisher().equals(bookPublisher))){
            throw new BookNotFoundException("");
        }
        List<Book> publisherBooks = books.values().stream().filter(b -> b.getPublisher().equals(bookPublisher)).toList();
        return publisherBooks.stream().sorted(bookComparatorByNewest()).toList().getLast();
    }

    public static Comparator<Book> bookComparatorByNewest() {
        return Comparator.comparing(Book::getPublicationYear);
    }
    public static Comparator<Book> bookComparator() {
        return Comparator.comparing(Book::getPublicationYear)
                .thenComparing(Book::getTitle).thenComparing(Book::getPageCount);
    }
    @Override
    public List<Book> getSortedBooks() {
        List<Book> sortedBooks = new ArrayList<Book>(books.values());
        return sortedBooks.stream().sorted(bookComparator()).collect(Collectors.toList());
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
