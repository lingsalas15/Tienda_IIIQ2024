
package com.tienda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dsala
 */
@Controller
public class IndexController {
    
    //se usa para mapear un recurso que pide el usuario con un metodo
    @RequestMapping("/") //el mapping no necesariamente se llama igual que el return vista
    public String paginaInicio(Model model) {
        //model.addAttribute("attribute", "value");
        return "index"; //las vistas que buscan los controllers son las de los templates
        //return "layout/plantilla"; //se hace asi en caso de que haya un folder, nunca se coloca el html
    }
    
    @RequestMapping("/contacto") //el mapping no necesariamente se llama igual que el return vista
    public String paginaContacto(Model model) {
        //model.addAttribute("attribute", "value");
        return "info"; //las vistas que buscan los controllers son las de los templates
        //return "layout/plantilla"; //se hace asi en caso de que haya un folder, nunca se coloca el html
    }
    
}
