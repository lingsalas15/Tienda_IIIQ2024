package com.tienda.controller;

import com.tienda.domain.Producto;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import com.tienda.service.impl.FirebaseStorageServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto") //para simplifica, o sea por ejemplo ya no se debe escribir producto para llegar a los metodos xq responde a todo lo que sea /producto
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private FirebaseStorageServiceImpl firebaseStorageService;

    @GetMapping("/listado") //para obtener la lista mediante la peticion de un URL
    public String inicio(Model model) {
        List<Producto> productos = productoService.getProductos(false);
        var categorias = categoriaService.getCategorias(true); //para que solo traiga la LISTA de las categorias activas
        model.addAttribute("productos", productos);
        model.addAttribute("totalProductos", productos.size());
        model.addAttribute("categorias", categorias); //despues de consultarlas las enviamos al model
        return "/producto/listado";
    }

    /*@GetMapping("/nuevo")
    public String productoNuevo(Producto producto) {
        return "/producto/modifica";
    }*/
    
    @PostMapping("/guardar") //para crear un registro
    public String productoGuardar(Producto producto,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        //ESE IF SOLO SE CUMPLE SI LA IMAGEN ESTA VACIA
        if (!imagenFile.isEmpty()) {
            productoService.save(producto); //HAY QUE SALVARLO PARA QUE GENERE EL ID, Y LUEGO PODER ENVIARLO AL SIGUIENTE METODO
            producto.setRutaImagen(
                    firebaseStorageService.cargaImagen(
                            imagenFile,
                            "producto",
                            producto.getIdProducto())); //retorna el url
        }
        productoService.save(producto); //SE ACTUALIZA
        return "redirect:/producto/listado"; //EL REDIRECT ES COMPLETAMENTE NECESARIO PARA QUE LA DEVUELVA LA LISTA PORQUE SON LOS DATOS DEL MODEL, HAY UNA OPCION ARRIBA EN EL GETMAPPING/LISTADO
    }

    //los getMapping responden a los botones
    @GetMapping("/eliminar/{idProducto}")
    public String productoEliminar(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String productoModificar(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);
        return "/producto/modifica";
    }
}
