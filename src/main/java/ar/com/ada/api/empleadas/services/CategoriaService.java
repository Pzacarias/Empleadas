  
package ar.com.ada.api.empleadas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.repos.CategoriaRepository;
@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    public boolean crearCategoria(Categoria categoria){
        
        if (existe(categoria.getCategoriaId()))
            return false;

        grabar(categoria);
        return true;
    }

    public List<Categoria> traerCategorias(){
        return repo.findAll();
    }
      
    public void grabar(Categoria categoria) {

       repo.save(categoria);
    }

    public Categoria buscarPorId(Integer id){
        return repo.findByCategoriaId(id);
    }

    public boolean existe(Integer id){
        Categoria categoria = buscarPorId(id);

        return categoria != null;
    }

    public Categoria buscarCategoria(Integer categoriaId){

        Optional<Categoria> resultado =  repo.findById(categoriaId);
        Categoria categoria = null;

        if (resultado.isPresent())
            categoria = resultado.get();

        return categoria;

    }
    
}




