package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    BookRepository bookRepository;


    @RequestMapping("/")
    public String bookList() {
        return "list";
    }
    @GetMapping("/add")
    public String libraryForm(Model model) {
        model.addAttribute("book", new Book());
        return "libraryform";
    }

    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("books") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "libraryform";
        }
        bookRepository.save(book);
        return "listaddedbooks";

    }

    @GetMapping ("/addedbooks")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "listaddedbooks";
    }
    //@GetMapping ("/borrowing")
    //public String bookBorrowing(Model model) {
      //  model.addAttribute("books", bookRepository.findAll());
        //return "borrowing";
    //}
    @GetMapping("/borrowing")
    public String getBook()
    {
        return "borrowingform";
    }

    @PostMapping("/borrowing")
    public String showSearchResults(HttpServletRequest request, Model model)
    {

        String borrowS = request.getParameter("borrowing");
        model.addAttribute("borrowing",borrowS);
        model.addAttribute("books",bookRepository.findAllByAuthorIgnoreCase(borrowS));
        return "listaddedbooks";
        //return "list";
    }

}