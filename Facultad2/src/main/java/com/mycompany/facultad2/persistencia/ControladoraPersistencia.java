
package com.mycompany.facultad2.persistencia;

import com.mycompany.facultad2.logica.Alumno;
import com.mycompany.facultad2.logica.Calificacion;
import com.mycompany.facultad2.logica.Curso;
import com.mycompany.facultad2.logica.Profesor;
import com.mycompany.facultad2.logica.Rol;
import com.mycompany.facultad2.logica.Usuario;
import com.mycompany.facultad2.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ControladoraPersistencia {

    UsuarioJpaController usuJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    AlumnoJpaController alumnJpa = new AlumnoJpaController();
    CursoJpaController cursoJpa = new CursoJpaController();
    ProfesorJpaController profJpa = new ProfesorJpaController();
    CalificacionJpaController califJpa = new CalificacionJpaController();

    public List<Usuario> traerUsuarios() {
        return usuJpa.findUsuarioEntities();
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }

    public void guardarUsuario(Usuario usu) {
        usuJpa.create(usu);
    }

    public void guardarProfesor(Profesor prof) {
        profJpa.create(prof);
    }

    public void guardarAlumno(Alumno alumno) {
        alumnJpa.create(alumno);
    }

    public void borrarUsuario(int id_usuario) {
        try {
            usuJpa.destroy(id_usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int id_usuario) {
        return usuJpa.findUsuario(id_usuario);
    }

    public void editarUsuario(Usuario usu) {
        try {
            usuJpa.edit(usu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Curso> traerCursos() {
        return cursoJpa.findCursoEntities();
    }

    public void guardarCurso(Curso curso) {
        cursoJpa.create(curso);
    }

    public void borrarCurso(int id_curso) {
        try {
            cursoJpa.destroy(id_curso);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Curso traerCurso(int id_curso) {
        return cursoJpa.findCurso(id_curso);
    }

    public void editarCurso(Curso curso) {
        try {
            cursoJpa.edit(curso);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Alumno> traerAlumnos() {
        return alumnJpa.findAlumnoEntities();
    }

    public void editarAlumno(Alumno alumno) {
        try {
            alumnJpa.edit(alumno);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void inscribirAlumno(Alumno alumno, Curso curso) {
        try {
            alumnJpa.edit(alumno);
            cursoJpa.edit(curso);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarCurso(Alumno alumno, Curso curso) {
        try {
            alumnJpa.edit(alumno);
            cursoJpa.edit(curso);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarCalificacion(Calificacion calificacion) {
        try {
            califJpa.create(calificacion);
            califJpa.edit(calificacion);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Calificacion> traerCalificaciones() {
        return califJpa.findCalificacionEntities();
    }

    public void borrarCalificacion(Calificacion calif) {
        try {
            califJpa.destroy(calif.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCalificacion(Calificacion calif) {
        try {
            califJpa.edit(calif);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
