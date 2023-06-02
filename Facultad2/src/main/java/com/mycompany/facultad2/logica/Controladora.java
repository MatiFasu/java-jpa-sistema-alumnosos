
package com.mycompany.facultad2.logica;

import com.mycompany.facultad2.persistencia.ControladoraPersistencia;
import java.util.List;


public class Controladora {

    ControladoraPersistencia controlPersis;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }

    public Usuario validarUsuario(String usuario, String pass) {
        
        Usuario usu = null;
        
        List<Usuario> usuarios = controlPersis.traerUsuarios();
        for(Usuario u: usuarios) {
            if( u.getUsuario().equals(usuario)) {
                if( u.getPass().equals(pass)) {
                    usu = u;
                    return usu;
                } else {
                    usu = null;
                    return usu;
                }
            } else {
                usu = null;
            }
        }

        return usu;
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    public void guardarUsuario(String usuario, String pass, String rol) {
        Rol unRol = null;
        List<Rol> roles = controlPersis.traerRoles();
        for(Rol r: roles) {
            if( r.getNombreRol().equals(rol)) {
                unRol = r;
            }
        }
        
        Usuario usu = new Usuario();
        usu.setUsuario(usuario);
        usu.setPass(pass);
        usu.setUnRol(unRol);
        
        controlPersis.guardarUsuario(usu);
    }

    public void guardarProfesor(String usuario) {
        Profesor prof = new Profesor();
        prof.setNombreProf(usuario);
        controlPersis.guardarProfesor(prof);
    }

    public void guardarAlumno(String usuario) {
        List<Curso> cursos = null;
        List<Calificacion> calificaciones = null;
        
        Alumno alumno = new Alumno();
        alumno.setNombre(usuario);
        alumno.setCursos(cursos);
        alumno.setCalificaciones(calificaciones);
        controlPersis.guardarAlumno(alumno);
    }

    public void borrarUsuario(int id_usuario) {
        controlPersis.borrarUsuario(id_usuario);
    }

    public void editarUsuario(int id_usuario, String usuario, String pass, String rol) {
        Rol unRol = null;
        List<Rol> roles = controlPersis.traerRoles();
        for(Rol r: roles) {
            if( r.getNombreRol().equals(rol) ) {
                unRol = r;
            }
        }
        
        Usuario usu = controlPersis.traerUsuario(id_usuario);
        usu.setUsuario(usuario);
        usu.setPass(pass);
        usu.setUnRol(unRol);
        
        controlPersis.editarUsuario(usu);
    }

    public List<Curso> traerCursos() {
        return controlPersis.traerCursos();
    }

    public void guardarCurso(String nombre, String precio, String cupo) {
        
        List<Alumno> alumnos = null;
        List<Calificacion> calificaciones = null;
        
        Curso curso = new Curso();
        curso.setNombre(nombre);
        curso.setPrecio(Float.parseFloat(precio));
        curso.setCupo(Integer.parseInt(cupo));
        curso.setAnotados(0);
        curso.setAlumnos(alumnos);
        curso.setCalificaciones(calificaciones);
        
        controlPersis.guardarCurso(curso); 
    }

    public void borrarCurso(int id_curso) {
        controlPersis.borrarCurso(id_curso);
    }

    public void editarCurso(int id_curso, String nombre, String precio, String cupo, String anotados) {
        
        Curso curso = controlPersis.traerCurso(id_curso);
        curso.setNombre(nombre);
        curso.setPrecio(Float.parseFloat(precio));
        curso.setCupo(Integer.parseInt(cupo));
        curso.setAnotados(Integer.parseInt(anotados));
        
        controlPersis.editarCurso(curso);
    }

    public boolean inscribirAlumno(int id_curso, Usuario usuario) {
        
        boolean inscribirse = false;
        
        Curso curso = controlPersis.traerCurso(id_curso);
        Alumno alumno = null;
        List<Alumno> alumnos = controlPersis.traerAlumnos();
        for(Alumno a: alumnos) {
            if(a.getNombre().equals(usuario.getUsuario())) {
                alumno = a;
            }
        }
        
        if( alumno != null && curso != null) {
            if( !estaInscrito(curso, alumno) ) {
                if( curso.getCupo() > 0 ) {
                    List<Alumno> alumnos2 = curso.getAlumnos();
                    alumnos2.add(alumno);
                    List<Curso> cursos = alumno.getCursos();
                    cursos.add(curso);

                    alumno.setCursos(cursos);
                    curso.setAlumnos(alumnos2);
                    curso.setCupo(curso.getCupo()-1);
                    curso.setAnotados(curso.getAnotados()+1);
                    
                    Calificacion calificacion = new Calificacion();
                    calificacion.setNota(0);
                    calificacion.setUnAlumno(alumno);
                    calificacion.setUnCurso(curso);
                    
                    controlPersis.inscribirAlumno(alumno,curso);
                    controlPersis.guardarCalificacion(calificacion);
                    inscribirse = true;
                    return inscribirse;
                } else {
                    System.out.println("No hay cupos");
                    inscribirse = false;
                }
            } else {
                System.out.println("Ya esta inscrito");
                inscribirse = false;
            }
        } else {
            System.out.println("Alumno y/o curso vacios");
        }

        return inscribirse;
    }

    private boolean estaInscrito(Curso curso, Alumno alumno) {
        
        boolean estaInscrito = false;
        
        for(int i=0; i<curso.getAlumnos().size(); i++) {
            if( curso.getAlumnos().get(i).getId() == alumno.getId() ) {
                estaInscrito = true;
                return estaInscrito;
            }
        }
        
        return estaInscrito;
    }

    public List<Alumno> traerAlumnos() {
        return controlPersis.traerAlumnos();
    }

    public boolean dejarCurso(int id_curso, Usuario usuario) {
        
        boolean dejarCurso = false;
        
        Curso curso = controlPersis.traerCurso(id_curso);
        Alumno alumno = null;
        List<Alumno> alumnos = controlPersis.traerAlumnos();
        for(Alumno a: alumnos) {
            if( a.getNombre().equals(usuario.getUsuario()) ) {
                alumno = a;
            }
        }
        
        Calificacion calif = null;
        List<Calificacion> calificaciones = controlPersis.traerCalificaciones();
        for(Calificacion cal: calificaciones) {
            if( cal.getUnCurso().getId() == curso.getId() && cal.getUnAlumno().getId() == alumno.getId() ) {
                calif = cal;
            }
        }
        
        if( alumno != null && curso != null ) {
            if( estaInscrito(curso, alumno) ) {
                List<Curso> cursos = alumno.getCursos();
                List<Alumno> alumnos2 = curso.getAlumnos();

                eliminarCursoAlumno(cursos,alumnos2,curso,alumno);
                
                alumno.setCursos(cursos);
                curso.setAlumnos(alumnos2);
                curso.setAnotados(curso.getAnotados()-1);
                curso.setCupo(curso.getCupo()+1);
                
                controlPersis.dejarCurso(alumno, curso);
                controlPersis.borrarCalificacion(calif);
                dejarCurso = true;
                return dejarCurso;
            }
        }

        return dejarCurso;
    }

    private void eliminarCursoAlumno(List<Curso> cursos, List<Alumno> alumnos2, Curso curso, Alumno alumno) {
        
        for(int i=0; i<cursos.size(); i++) {
            if( cursos.get(i).getId() == curso.getId() ) {
                cursos.remove(i);
            }
        }
        
        for(int i=0; i<alumnos2.size(); i++) {
            if( alumnos2.get(i).getId() == alumno.getId() ) {
                alumnos2.remove(i);
            }
        }
    }

    public List<Calificacion> traerCalificaciones() {
        return controlPersis.traerCalificaciones();
    }

    public void editarCalificacion(int id_curso, int id_alumno, String nota) {
        
        Calificacion calif = null;
        List<Calificacion> calificaciones = controlPersis.traerCalificaciones();
        for(Calificacion cal: calificaciones) {
            if( cal.getUnCurso().getId() == id_curso && cal.getUnAlumno().getId() == id_alumno ) {
                calif = cal;
            }
        }
        
        calif.setNota(Integer.parseInt(nota));
        
        controlPersis.editarCalificacion(calif);
    }
    
    
    
    
}
