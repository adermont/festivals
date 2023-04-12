package fr.simplon.festivals.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Classe de mapping des URLs générales.
 */
@Controller
public class WebController
{
    @GetMapping(path="/")
    public String index(){
        return "redirect:/festivals";
    }
}
