package top.aiteky.onebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String root(){return "index";}

    @GetMapping("/books")
    public String books(){return "books";}

}
