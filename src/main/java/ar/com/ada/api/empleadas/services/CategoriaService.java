package ar.com.ada.api.empleadas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.entities.Categoria;

@Service
public class CategoriaService {
    
    @Autowired
    CategoriaRepository categoriaRepo;

    public boolean crearCcategoria(Categoria categoria) {

        if (existe(categoria.getCategoriaId()))
            return false;

        grabar(categoria);
        return true;
    }

    
    public void grabar(Categoria categoria) {

       categoriaRepo.save(categoria);
    }

    public Categoria buscarPorId(Integer id){
        return categoriaRepo.findById (id);
    }

    public boolean existe (Integer id){
        Categoria categoria = buscarPorId(id);

        return categoria != null;
    }
 //
    
}




