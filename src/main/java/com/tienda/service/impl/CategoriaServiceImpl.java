
package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dsala
 */

@Service
public class CategoriaServiceImpl implements CategoriaService{
    
    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<Categoria> getCategorias(boolean activos) {
       List<Categoria> lista = categoriaDao.findAll();
       
       if (activos) {
           //remueve de la lista los elemtnos donde el atributo activo es false
           lista.removeIf(e -> !e.isActivo()); //remueven los que cumplen con esta condicion
       }
       
       return lista;
    }
    
}
