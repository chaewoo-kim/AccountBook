package com.chaewookim.AccoutBook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/main"})
    String main() {
        return "main.html";
    }

}
