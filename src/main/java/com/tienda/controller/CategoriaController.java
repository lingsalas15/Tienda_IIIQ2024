
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dsala
 */
@Controller
@RequestMapping("/categoria") //para simplifica, o sea por ejemplo ya no se debe escribir categoria para llegar a los metodos xq responde a todo lo que sea /categoria
public class CategoriaController {
    
    @Autowired
    CategoriaService categoriaService;
    
    @RequestMapping("/listado")
    public String inicio(Model model) {
        List<Categoria> lista = categoriaService.getCategorias(false);
        model.addAttribute("categorias", lista);
        model.addAttribute("totalCategorias", lista.size());
        
        return "/categoria/listado"; //muy diferente es el mapping a la categoria, se pueden llamar diferente
    }
    
}
