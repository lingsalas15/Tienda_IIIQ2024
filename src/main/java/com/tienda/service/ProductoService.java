package com.tienda.service;

import com.tienda.domain.Producto;
import java.util.List;

//las interfaces son para definir la firma de los metodos disponibles
public interface ProductoService {

    // Se obtiene un listado de productos en un List
    public List<Producto> getProductos(boolean activos);

    // Se obtiene un Producto, a partir del id de un producto
    public Producto getProducto(Producto producto);

    // Se inserta un nuevo producto si el id del producto esta vacío
    // Se actualiza un producto si el id del producto NO esta vacío
    public void save(Producto producto);

    // Se elimina el producto que tiene el id pasado por parámetro
    public void delete(Producto producto);

    //para disponivilizar/devolver el metodo que creamos en productoDao en la linea 15
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup); //el metodo puede tener el nombre que quiera, pero los parametros deben ser los mismos

    //Lista de productos utilizando consultas con JPQL    
    public List<Producto> metodoJPQL(double precioInf, double precioSup);

    //Lista de productos utilizando consultas con SQL Nativo
    public List<Producto> metodoNativo(double precioInf, double precioSup);
    
    public List<Producto> buscarPorNombre(String nombre); //debo hacer la implementacion en service.impl

    //Lista de productos por Categoria
    //public List<Producto> buscarPorCategoria(Long idCategoria);
    
    List<Producto> findByCategoriaAndActivo(Long idCategoria, boolean activo);
}
