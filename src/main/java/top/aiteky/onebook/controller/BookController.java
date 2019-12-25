package top.aiteky.onebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.aiteky.onebook.model.Result;
import top.aiteky.onebook.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/oneBook")
    public Result oneBook(){
        return bookService.oneBook();
    }

    @GetMapping("/books")
    public Result books(Long start, Long num){
        return bookService.findAllBooksLimit(start, num);
    }
    @GetMapping("/search")
    public Result search(String keyword){
        return bookService.search(keyword.replaceAll(" ", "%"));
    }

}
