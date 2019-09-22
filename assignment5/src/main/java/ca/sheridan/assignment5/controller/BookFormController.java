package ca.sheridan.assignment5.controller;

import ca.sheridan.assignment5.data.BookFormService;
import ca.sheridan.assignment5.model.BookForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookFormController {

    private final Logger logger = LoggerFactory.getLogger(BookFormController.class);

    private static final String[] formats = {
            "English", "French",
            "German", "Latin"};

    private BookFormService bookFormService;

    @Autowired
    public BookFormController(BookFormService bookFormService){
        this.bookFormService = bookFormService;
    }

    @RequestMapping(value={"/","/Index.do"})
    public String index(){
        logger.trace("index() is called");
        return "Index";
    }
    @RequestMapping("/AddBook.do")
    public ModelAndView addBook(){
        logger.trace("addStudent() is called");
        ModelAndView modelAndView =
                new ModelAndView("AddBook",
                        "form", new BookForm());
        modelAndView.addObject("formats", formats);
        return modelAndView;
    }
    @RequestMapping("/InsertBook.do")
    public String insertBook(
            @Validated @ModelAttribute("form") BookForm form,
            BindingResult bindingResult,
            Model model) {
        logger.trace("insertBook() is called");


        logger.debug("received bookTitle=" + form.getBookTitle());
        logger.debug("received bookAuthor=" + form.getBookAuthor());
        logger.debug("received publicationDate=" + form.getPublicationDate());
        logger.debug("received bookLanguage=" + form.getBookLanguage());
        logger.debug("received bookFormat=" + form.getBookFormat());
        logger.debug("received bookUsed=" + form.getBookUsed());


        form.setBookUsed((form.getBookUsed() == null) ? "no" : "yes");
        // checking for the input validation errors
        //bookFormService.insertBookForm(form);
        //return "redirect:ConfirmEntry.do?id=" + form.getId();

        if (!bindingResult.hasErrors()) {
            logger.trace("the user inputs are correct");
            bookFormService.insertBookForm(form);
            return "redirect:ConfirmEntry.do?id=" + form.getId();
        } else {
            logger.trace("input validation errors");
            model.addAttribute("form", form);
            model.addAttribute("formats", formats);
            return "AddBook";
        }
    }
    @RequestMapping("/ConfirmEntry.do")
    public String confirmInsert(@RequestParam(name = "id") String strId, Model model){
        logger.trace("confirmInsert() is called");
        if (strId == null || strId.isEmpty()) {
            logger.trace("no id in the request");
            return "DataNotFound";
        } else {
            try {
                int id = Integer.parseInt(strId);
                logger.trace("looking for the data in the database");
                //logger.debug("received bookTitle=" + form.getBookTitle());
                BookForm form = bookFormService.getBookForm(id);
                logger.debug("received bookID=" + form.getId());
                logger.debug("bookTitle=" + form.getBookTitle());
                logger.debug("bookAuthor=" + form.getBookAuthor());
                logger.debug("publicationDate=" + form.getPublicationDate());
                logger.debug("bookLanguage=" + form.getBookLanguage());
                logger.debug("bookFormat=" + form.getBookFormat());
                logger.debug("bookUsed=" + form.getBookUsed());
                if (form == null) {
                    logger.trace("no data for this id=" + id);
                    return "DataNotFound";
                } else {
                    logger.trace("showing the data");
                    model.addAttribute("book", form);
                    return "ConfirmEntry";
                }
            } catch (NumberFormatException e) {
                logger.trace("the id in not an integer");
                return "DataNotFound";
            }
        }

    }
    @RequestMapping("/BookList.do")
    public ModelAndView bookList() {
        logger.trace("bookList() is called");
        List<BookForm> list = bookFormService.getAllBookForms();
        return new ModelAndView("BookList",
                "book", list);
    }
    @RequestMapping("/DeleteAll.do")
    public String deleteAll() {
        logger.trace("deleteAll() is called");
        bookFormService.deleteAllBookForms();
        return "redirect:BookList.do";
    }
}
