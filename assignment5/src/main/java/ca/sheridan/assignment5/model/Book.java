package ca.sheridan.assignment5.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;

    @Column(name = "book_title")
    private String bookTitle = "";

    @Column(name = "book_author")
    private String bookAuthor = "";

    @Column(name = "publication_date")
    private String publicationDate = "";

    @Column(name = "book_language")
    private String bookLanguage = "";

    @Column(name = "book_format")
    private String bookFormat = "";

    @Column(name = "book_used")
    private Boolean bookUsed = false;


    public Book(){
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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

    public Boolean getBookUsed() {
        return bookUsed;
    }

    public void setBookUsed(Boolean bookUsed) {
        this.bookUsed = bookUsed;
    }
}
