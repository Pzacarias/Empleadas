package ar.com.ada.api.empleadas.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table (name = "empleada")
public class Empleada {
    
    @Id
    @Column  (name = "empleada_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empleadaId;

    private String nombre;

    private Integer edad;

   
    @ManyToOne 
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
    private Categoria categoria;

    private BigDecimal sueldo;

    private int estado;

    @Column (name = "fecha_alta")
    private Date fechaAlta;

    @Column (name ="fecha_baja")
    private Date fechaBaja;

    public Integer getEmpleadaId() {
        return empleadaId;
    }

    public void setEmpleadaId(Integer empleadaId) {
        this.empleadaId = empleadaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }


    public enum EstadoEmpleadaEnum{

        ACTIVO(1),
        INACTIVO(2);


        private final int value;

        private EstadoEmpleadaEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EstadoEmpleadaEnum parse(int id) {
            EstadoEmpleadaEnum status = null; // Default
            for (EstadoEmpleadaEnum item : EstadoEmpleadaEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }


    }

}
