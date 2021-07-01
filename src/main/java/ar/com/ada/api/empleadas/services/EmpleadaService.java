package ar.com.ada.api.empleadas.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleadas.entities.Categoria;
import ar.com.ada.api.empleadas.entities.Empleada;
import ar.com.ada.api.empleadas.entities.Empleada.EstadoEmpleadaEnum;
import ar.com.ada.api.empleadas.models.request.InfoEmpleadaNueva;
import ar.com.ada.api.empleadas.models.request.SueldoNuevoEmpleada;
import ar.com.ada.api.empleadas.repos.EmpleadaRepository;

@Service
public class EmpleadaService {

    @Autowired
    EmpleadaRepository repo;

    @Autowired
    CategoriaService categoriaService;

    public void crearEmpleada(Empleada empleada) {
        repo.save(empleada);
    }

    public List<Empleada> traerEmpleadas() {
        return repo.findAll();
    }

    public Empleada buscarEmpleada(Integer id) {
        Optional<Empleada> resultado = repo.findById(id);

        if (resultado.isPresent())
            return resultado.get();

        return null;
    }

    //DELETE LOGICO,que se mantiene en la db pero con estatus.
	public void bajaEmpleadaPorId(Integer id) {
        Empleada empleada = this.buscarEmpleada(id);
        
        empleada.setEstado(EstadoEmpleadaEnum.BAJA);
        empleada.setFechaBaja(new Date());

        repo.save(empleada);

	}

	public List<Empleada> traerEmpleadaPorCategoria(Integer catId) {
		
        Categoria categoria = categoriaService.buscarCategoria(catId);
        
        return categoria.getEmpleadas();

	}

    public void guardar(Empleada empleada) {
        repo.save(empleada);
    }

    public void modificarSueldo(Integer id, SueldoNuevoEmpleada sueldoNuevoInfo) {
        Empleada empleada = this.buscarEmpleada(id);
        empleada.setSueldo(sueldoNuevoInfo.sueldoNuevo);
        guardar(empleada);
    }

    public Empleada crearEmpleada(InfoEmpleadaNueva empleadaInfo) {
        Empleada empleada = new Empleada(empleadaInfo.nombre, empleadaInfo.edad, empleadaInfo.sueldo, new Date ());
               
        Categoria categoria = categoriaService.buscarCategoria(empleadaInfo.categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstado(EstadoEmpleadaEnum.ACTIVO);
        
       crearEmpleada(empleada);
    return empleada;
    }

}
