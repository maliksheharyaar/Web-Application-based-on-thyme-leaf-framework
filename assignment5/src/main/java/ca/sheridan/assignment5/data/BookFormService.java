package ca.sheridan.assignment5.data;

import ca.sheridan.assignment5.model.BookForm;

import java.util.List;

public interface BookFormService {

    void insertBookForm(BookForm form);

    List<BookForm> getAllBookForms();

    void deleteAllBookForms();

    void deleteBookForm(int id);

    BookForm getBookForm(int id);
    void updateBookForm(BookForm form);
}
