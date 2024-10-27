package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/categoria") //para simplifica, o sea por ejemplo ya no se debe escribir categoria para llegar a los metodos xq responde a todo lo que sea /categoria
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado") //para obtener la lista mediante la peticion de un URL
    public String inicio(Model model) {
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        model.addAttribute("totalCategorias", categorias.size());
        return "/categoria/listado";
    }

    /*@GetMapping("/nuevo")
    public String categoriaNuevo(Categoria categoria) {
        return "/categoria/modifica";
    }*/
    
    @PostMapping("/guardar") //para crear un registro
    public String categoriaGuardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        //ESE IF SOLO SE CUMPLE SI LA IMAGEN ESTA VACIA
        if (!imagenFile.isEmpty()) {
            categoriaService.save(categoria); //HAY QUE SALVARLO PARA QUE GENERE EL ID, Y LUEGO PODER ENVIARLO AL SIGUIENTE METODO
            categoria.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "categoria",
                            categoria.getIdCategoria())); //retorna el url
        }
        categoriaService.save(categoria); //SE ACTUALIZA
        return "redirect:/categoria/listado"; //EL REDIRECT ES COMPLETAMENTE NECESARIO PARA QUE LA DEVUELVA LA LISTA PORQUE SON LOS DATOS DEL MODEL, HAY UNA OPCION ARRIBA EN EL GETMAPPING/LISTADO
    }

    //los getMapping responden a los botones
    @GetMapping("/eliminar/{idCategoria}")
    public String categoriaEliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    public String categoriaModificar(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return "/categoria/modifica";
    }
}
