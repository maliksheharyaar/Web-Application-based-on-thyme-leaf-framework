package ca.sheridan.assignment5.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class BookForm implements Serializable {
    private int id = 0;

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z ]*")
    private String BookTitle = "";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z ]*")
    private String bookAuthor = "";

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "[A-Za-z0-90-90-90-9 ]*")
    private String publicationDate = "";

    @NotBlank
    @Pattern(regexp = "(English|French|German|Latin)?")
    private String bookLanguage = "";

    @NotBlank
    @Pattern(regexp = "(Paperback|Hardcover)?")
    private String bookFormat = "";

    private String bookUsed = "";

    public BookForm() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return BookTitle;
    }

    public void setBookTitle(String bookTitle) {
        BookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public String getBookFormat() {
        return bookFormat;
    }

    public void setBookFormat(String bookFormat) {
        this.bookFormat = bookFormat;
    }

    public String getBookUsed() {
        return bookUsed;
    }

    public void setBookUsed(String bookUsed) {
        this.bookUsed = bookUsed;
    }
}
