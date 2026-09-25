package network.webtech.labmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PaginaController {
    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("message", "Bem-vindo ao Lab Spring MVC!");
        return "home";
    }

    @GetMapping("/message/{msg}")
    public String mensagem(@PathVariable String msg, Model model) {
        model.addAttribute("message", msg);
        return "home";
    }
}
