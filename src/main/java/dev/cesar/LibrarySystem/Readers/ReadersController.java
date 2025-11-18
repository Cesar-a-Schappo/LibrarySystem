package dev.cesar.LibrarySystem.Readers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ReadersController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Essa é minha primeira mensagem nesta rota";
    }
}
