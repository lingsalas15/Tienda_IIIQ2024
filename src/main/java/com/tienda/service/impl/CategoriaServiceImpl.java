package com.tienda.service.impl;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dsala
 */
@Service /*Esto es esencial para que funcione*/
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired /*Esto es para importar lo que necesitamos*/
    private CategoriaDao categoriaDao; /*es privado porque nadie fuera de ella deberia usarlo*/

    @Override
    public List<Categoria> getCategorias(boolean activos) {
        List<Categoria> lista = categoriaDao.findAll();

        if (activos) {
            //remueve de la lista los elemtnos donde el atributo activo es false
            lista.removeIf(e -> !e.isActivo()); //remueven los que cumplen con esta condicion
        }

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return categoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Categoria categoria) {
        categoriaDao.save(categoria);
    }

    @Override
    @Transactional
    public void delete(Categoria categoria) {
        categoriaDao.delete(categoria);
    }

}
