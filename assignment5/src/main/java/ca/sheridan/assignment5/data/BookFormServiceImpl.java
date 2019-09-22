package ca.sheridan.assignment5.data;

import ca.sheridan.assignment5.model.Book;
import ca.sheridan.assignment5.model.BookForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookFormServiceImpl implements BookFormService {

    private BookRepository bookRepository;

    @Autowired
    BookFormServiceImpl(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    private static void copyFormToBook(BookForm form, Book book){
        book.setId(form.getId());
        book.setBookTitle(form.getBookTitle());
        book.setBookAuthor(form.getBookAuthor());
        book.setPublicationDate(form.getPublicationDate());
        book.setBookLanguage(form.getBookLanguage());
        book.setBookFormat(form.getBookFormat());
        book.setBookUsed(form.getBookUsed().equals("yes"));
    }
    private static void copyBookToForm(Book book, BookForm form){
        form.setId(book.getId());
        form.setBookTitle(book.getBookTitle());
        form.setBookAuthor(book.getBookAuthor());
        form.setPublicationDate(book.getPublicationDate());
        form.setBookLanguage(book.getBookLanguage());
        form.setBookFormat(book.getBookFormat());
        form.setBookUsed(book.getBookUsed()?"yes":"no");
    }
    @Override
    public void insertBookForm(BookForm form) {
        Book book = new Book();
        copyFormToBook(form, book);
        book = bookRepository.save(book);
        bookRepository.flush();
        form.setId(book.getId());
    }
    @Override
    public List<BookForm> getAllBookForms() {
        List<BookForm> formList = new ArrayList<>();
        List<Book> bookList = bookRepository.findAll();
        for(Book book: bookList){
            BookForm form = new BookForm();
            copyBookToForm(book, form);
            formList.add(form);
        }
        return formList;
    }

    @Override
    public void deleteAllBookForms() {
        bookRepository.deleteAll();
    }

    @Override
    public void deleteBookForm(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookForm getBookForm(int id) {
        Optional<Book> result = bookRepository.findById(id);
        if(result.isPresent()){
            BookForm form = new BookForm();
            Book book = result.get();
            copyBookToForm(book, form);
            return form;
        }
        return null;
    }
    @Override
    public void updateBookForm(BookForm form) {
        Optional<Book> result = bookRepository.findById(form.getId());
        if(result.isPresent()){
            Book student = result.get();
            copyFormToBook(form, student);
            bookRepository.save(student);
            bookRepository.flush();
        }
    }

}
