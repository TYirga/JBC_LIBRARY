package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {
    @Autowired
    BookRepository bookRepository;

    @RequestMapping("/")
    public String bookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
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
        return "redirect:/";

    }

    @RequestMapping("/delete/{id}")
    public String deleteBorrowedBook(@PathVariable("id") long id) {
        bookRepository.delete(id);
        return "redirect:/";
    }

}