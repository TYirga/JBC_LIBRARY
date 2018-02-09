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
    @Autowired
    BookRepository rRepository;

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

    @GetMapping("/addedbooks")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "listaddedbooks";
    }

    @GetMapping("/borrowing")
    public String bookBorrowing(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "borrowing";
    }

    @RequestMapping ("/borrow/{id}")
    public String borrowBook(@PathVariable("id")long id, Model model) {
        model.addAttribute("book", bookRepository.findOne(id));
        return ("showbook");
    }
    @GetMapping("/returning")
    public String returningBook(Model model) {
    model.addAttribute("books", bookRepository.findAll());
    return "returning";
    }

    @RequestMapping ("/return/{id}")
   public String returnBook(@PathVariable("id")long id, Model model) {
       model.addAttribute("book", bookRepository.findOne(id));
       return ("returnform");
    }

}














