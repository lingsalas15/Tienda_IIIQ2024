
package com.tienda.service;

import com.tienda.domain.Categoria;
import java.util.List;

/**
 *
 * @author dsala
 */

//las interfaces son para definir la firma de los metodos disponibles
public interface CategoriaService {
    
    public List<Categoria> getCategorias(boolean activos);
}
