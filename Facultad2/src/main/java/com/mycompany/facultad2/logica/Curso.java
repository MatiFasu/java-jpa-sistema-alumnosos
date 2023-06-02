
package com.mycompany.facultad2.logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Curso implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;
    private float precio;
    private int cupo;
    private int anotados;
    @ManyToMany(mappedBy = "cursos")
    private List<Alumno> alumnos;
    @OneToMany (mappedBy = "unCurso")
    private List<Calificacion> calificaciones;

    public Curso() {
    }

    public Curso(int id, String nombre, float precio, int cupo, int anotados, List<Alumno> alumnos, List<Calificacion> calificaciones) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cupo = cupo;
        this.anotados = anotados;
        this.alumnos = alumnos;
        this.calificaciones = calificaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getAnotados() {
        return anotados;
    }

    public void setAnotados(int anotados) {
        this.anotados = anotados;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    
    
}
