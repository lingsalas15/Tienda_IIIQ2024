
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
//import jakarta.persistence.Table;
import lombok.Data;


//las anotcaciones van encima de las clases, siempre
@Data 
@Entity
@Table(name="categoria")
public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id_categoria")
    private Long idCategoria; //interpretar  como id_categoria
    private String descripcion;
    private String rutaImagen;
    private boolean activo;

    public Categoria() {
    }

    //no se hace con el id de categoria porque para eso esta la base de datos
    public Categoria(String descripcion, String rutaImagen, boolean activo) {
        this.descripcion = descripcion;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }
    
    
    
}
