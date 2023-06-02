/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facultad2.persistencia;

import com.mycompany.facultad2.logica.Alumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.facultad2.logica.Curso;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.facultad2.logica.Calificacion;
import com.mycompany.facultad2.persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matia
 */
public class AlumnoJpaController implements Serializable {

    public AlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public AlumnoJpaController() {
        emf = Persistence.createEntityManagerFactory("facultad2PU");
    }

    public void create(Alumno alumno) {
        if (alumno.getCursos() == null) {
            alumno.setCursos(new ArrayList<Curso>());
        }
        if (alumno.getCalificaciones() == null) {
            alumno.setCalificaciones(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Curso> attachedCursos = new ArrayList<Curso>();
            for (Curso cursosCursoToAttach : alumno.getCursos()) {
                cursosCursoToAttach = em.getReference(cursosCursoToAttach.getClass(), cursosCursoToAttach.getId());
                attachedCursos.add(cursosCursoToAttach);
            }
            alumno.setCursos(attachedCursos);
            List<Calificacion> attachedCalificaciones = new ArrayList<Calificacion>();
            for (Calificacion calificacionesCalificacionToAttach : alumno.getCalificaciones()) {
                calificacionesCalificacionToAttach = em.getReference(calificacionesCalificacionToAttach.getClass(), calificacionesCalificacionToAttach.getId());
                attachedCalificaciones.add(calificacionesCalificacionToAttach);
            }
            alumno.setCalificaciones(attachedCalificaciones);
            em.persist(alumno);
            for (Curso cursosCurso : alumno.getCursos()) {
                cursosCurso.getAlumnos().add(alumno);
                cursosCurso = em.merge(cursosCurso);
            }
            for (Calificacion calificacionesCalificacion : alumno.getCalificaciones()) {
                Alumno oldUnAlumnoOfCalificacionesCalificacion = calificacionesCalificacion.getUnAlumno();
                calificacionesCalificacion.setUnAlumno(alumno);
                calificacionesCalificacion = em.merge(calificacionesCalificacion);
                if (oldUnAlumnoOfCalificacionesCalificacion != null) {
                    oldUnAlumnoOfCalificacionesCalificacion.getCalificaciones().remove(calificacionesCalificacion);
                    oldUnAlumnoOfCalificacionesCalificacion = em.merge(oldUnAlumnoOfCalificacionesCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Alumno alumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno persistentAlumno = em.find(Alumno.class, alumno.getId());
            List<Curso> cursosOld = persistentAlumno.getCursos();
            List<Curso> cursosNew = alumno.getCursos();
            List<Calificacion> calificacionesOld = persistentAlumno.getCalificaciones();
            List<Calificacion> calificacionesNew = alumno.getCalificaciones();
            List<Curso> attachedCursosNew = new ArrayList<Curso>();
            for (Curso cursosNewCursoToAttach : cursosNew) {
                cursosNewCursoToAttach = em.getReference(cursosNewCursoToAttach.getClass(), cursosNewCursoToAttach.getId());
                attachedCursosNew.add(cursosNewCursoToAttach);
            }
            cursosNew = attachedCursosNew;
            alumno.setCursos(cursosNew);
            List<Calificacion> attachedCalificacionesNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionesNewCalificacionToAttach : calificacionesNew) {
                calificacionesNewCalificacionToAttach = em.getReference(calificacionesNewCalificacionToAttach.getClass(), calificacionesNewCalificacionToAttach.getId());
                attachedCalificacionesNew.add(calificacionesNewCalificacionToAttach);
            }
            calificacionesNew = attachedCalificacionesNew;
            alumno.setCalificaciones(calificacionesNew);
            alumno = em.merge(alumno);
            for (Curso cursosOldCurso : cursosOld) {
                if (!cursosNew.contains(cursosOldCurso)) {
                    cursosOldCurso.getAlumnos().remove(alumno);
                    cursosOldCurso = em.merge(cursosOldCurso);
                }
            }
            for (Curso cursosNewCurso : cursosNew) {
                if (!cursosOld.contains(cursosNewCurso)) {
                    cursosNewCurso.getAlumnos().add(alumno);
                    cursosNewCurso = em.merge(cursosNewCurso);
                }
            }
            for (Calificacion calificacionesOldCalificacion : calificacionesOld) {
                if (!calificacionesNew.contains(calificacionesOldCalificacion)) {
                    calificacionesOldCalificacion.setUnAlumno(null);
                    calificacionesOldCalificacion = em.merge(calificacionesOldCalificacion);
                }
            }
            for (Calificacion calificacionesNewCalificacion : calificacionesNew) {
                if (!calificacionesOld.contains(calificacionesNewCalificacion)) {
                    Alumno oldUnAlumnoOfCalificacionesNewCalificacion = calificacionesNewCalificacion.getUnAlumno();
                    calificacionesNewCalificacion.setUnAlumno(alumno);
                    calificacionesNewCalificacion = em.merge(calificacionesNewCalificacion);
                    if (oldUnAlumnoOfCalificacionesNewCalificacion != null && !oldUnAlumnoOfCalificacionesNewCalificacion.equals(alumno)) {
                        oldUnAlumnoOfCalificacionesNewCalificacion.getCalificaciones().remove(calificacionesNewCalificacion);
                        oldUnAlumnoOfCalificacionesNewCalificacion = em.merge(oldUnAlumnoOfCalificacionesNewCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = alumno.getId();
                if (findAlumno(id) == null) {
                    throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Alumno alumno;
            try {
                alumno = em.getReference(Alumno.class, id);
                alumno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The alumno with id " + id + " no longer exists.", enfe);
            }
            List<Curso> cursos = alumno.getCursos();
            for (Curso cursosCurso : cursos) {
                cursosCurso.getAlumnos().remove(alumno);
                cursosCurso = em.merge(cursosCurso);
            }
            List<Calificacion> calificaciones = alumno.getCalificaciones();
            for (Calificacion calificacionesCalificacion : calificaciones) {
                calificacionesCalificacion.setUnAlumno(null);
                calificacionesCalificacion = em.merge(calificacionesCalificacion);
            }
            em.remove(alumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Alumno> findAlumnoEntities() {
        return findAlumnoEntities(true, -1, -1);
    }

    public List<Alumno> findAlumnoEntities(int maxResults, int firstResult) {
        return findAlumnoEntities(false, maxResults, firstResult);
    }

    private List<Alumno> findAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Alumno.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Alumno findAlumno(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Alumno> rt = cq.from(Alumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
