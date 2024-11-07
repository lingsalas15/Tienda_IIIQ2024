
package com.tienda.dao;

// @author dsala

import com.tienda.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao extends JpaRepository<Producto, Long> { //se trabaja con la entidad, en este caso al objeto de dominio y se usa el primary key de la entidad
    
}
