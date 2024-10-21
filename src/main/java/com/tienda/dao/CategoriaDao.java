
package com.tienda.dao;

// @author dsala

import com.tienda.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao extends JpaRepository<Categoria, Long> { //se trabaja con la entidad, en este caso al objeto de dominio y se usa el primary key de la entidad
    
}
