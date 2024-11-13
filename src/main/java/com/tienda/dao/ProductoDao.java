package com.tienda.dao;

// @author dsala
import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductoDao extends JpaRepository<Producto, Long> { //se trabaja con la entidad, en este caso al objeto de dominio y se usa el primary key de la entidad

    //los va a buscar por precio y los va a ordenar segun la descripcion
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf, double precioSup);

    //Ejemplo de método utilizando Consultas con JPQL
    @Query(value = "SELECT a FROM Producto a where a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.descripcion ASC")
    public List<Producto> metodoJPQL(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup); //como vamos a recibir parametros los definimos los que queremos
    
     //Ejemplo de método utilizando Consultas con SQL nativo
    @Query(nativeQuery=true,
            value="SELECT * FROM producto where producto.precio BETWEEN :precioInf AND :precioSup ORDER BY producto.descripcion ASC")
    public List<Producto> metodoNativo(@Param("precioInf") double precioInf, @Param("precioSup") double precioSup); 

    //en el findBy debe ir el nombre de un atributo 
    //Al ser un atributo de tipo string se debe definir()
    List<Producto> findByDescripcionContainingOrderByPrecio(String descripcion);

}
