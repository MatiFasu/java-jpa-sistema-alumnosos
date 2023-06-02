/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.facultad2.persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.mycompany.facultad2.logica.Alumno;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.facultad2.logica.Calificacion;
import com.mycompany.facultad2.logica.Curso;
import com.mycompany.facultad2.persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matia
 */
public class CursoJpaController implements Serializable {

    public CursoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CursoJpaController() {
        emf = Persistence.createEntityManagerFactory("facultad2PU");
    }

    public void create(Curso curso) {
        if (curso.getAlumnos() == null) {
            curso.setAlumnos(new ArrayList<Alumno>());
        }
        if (curso.getCalificaciones() == null) {
            curso.setCalificaciones(new ArrayList<Calificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Alumno> attachedAlumnos = new ArrayList<Alumno>();
            for (Alumno alumnosAlumnoToAttach : curso.getAlumnos()) {
                alumnosAlumnoToAttach = em.getReference(alumnosAlumnoToAttach.getClass(), alumnosAlumnoToAttach.getId());
                attachedAlumnos.add(alumnosAlumnoToAttach);
            }
            curso.setAlumnos(attachedAlumnos);
            List<Calificacion> attachedCalificaciones = new ArrayList<Calificacion>();
            for (Calificacion calificacionesCalificacionToAttach : curso.getCalificaciones()) {
                calificacionesCalificacionToAttach = em.getReference(calificacionesCalificacionToAttach.getClass(), calificacionesCalificacionToAttach.getId());
                attachedCalificaciones.add(calificacionesCalificacionToAttach);
            }
            curso.setCalificaciones(attachedCalificaciones);
            em.persist(curso);
            for (Alumno alumnosAlumno : curso.getAlumnos()) {
                alumnosAlumno.getCursos().add(curso);
                alumnosAlumno = em.merge(alumnosAlumno);
            }
            for (Calificacion calificacionesCalificacion : curso.getCalificaciones()) {
                Curso oldUnCursoOfCalificacionesCalificacion = calificacionesCalificacion.getUnCurso();
                calificacionesCalificacion.setUnCurso(curso);
                calificacionesCalificacion = em.merge(calificacionesCalificacion);
                if (oldUnCursoOfCalificacionesCalificacion != null) {
                    oldUnCursoOfCalificacionesCalificacion.getCalificaciones().remove(calificacionesCalificacion);
                    oldUnCursoOfCalificacionesCalificacion = em.merge(oldUnCursoOfCalificacionesCalificacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Curso curso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso persistentCurso = em.find(Curso.class, curso.getId());
            List<Alumno> alumnosOld = persistentCurso.getAlumnos();
            List<Alumno> alumnosNew = curso.getAlumnos();
            List<Calificacion> calificacionesOld = persistentCurso.getCalificaciones();
            List<Calificacion> calificacionesNew = curso.getCalificaciones();
            List<Alumno> attachedAlumnosNew = new ArrayList<Alumno>();
            for (Alumno alumnosNewAlumnoToAttach : alumnosNew) {
                alumnosNewAlumnoToAttach = em.getReference(alumnosNewAlumnoToAttach.getClass(), alumnosNewAlumnoToAttach.getId());
                attachedAlumnosNew.add(alumnosNewAlumnoToAttach);
            }
            alumnosNew = attachedAlumnosNew;
            curso.setAlumnos(alumnosNew);
            List<Calificacion> attachedCalificacionesNew = new ArrayList<Calificacion>();
            for (Calificacion calificacionesNewCalificacionToAttach : calificacionesNew) {
                calificacionesNewCalificacionToAttach = em.getReference(calificacionesNewCalificacionToAttach.getClass(), calificacionesNewCalificacionToAttach.getId());
                attachedCalificacionesNew.add(calificacionesNewCalificacionToAttach);
            }
            calificacionesNew = attachedCalificacionesNew;
            curso.setCalificaciones(calificacionesNew);
            curso = em.merge(curso);
            for (Alumno alumnosOldAlumno : alumnosOld) {
                if (!alumnosNew.contains(alumnosOldAlumno)) {
                    alumnosOldAlumno.getCursos().remove(curso);
                    alumnosOldAlumno = em.merge(alumnosOldAlumno);
                }
            }
            for (Alumno alumnosNewAlumno : alumnosNew) {
                if (!alumnosOld.contains(alumnosNewAlumno)) {
                    alumnosNewAlumno.getCursos().add(curso);
                    alumnosNewAlumno = em.merge(alumnosNewAlumno);
                }
            }
            for (Calificacion calificacionesOldCalificacion : calificacionesOld) {
                if (!calificacionesNew.contains(calificacionesOldCalificacion)) {
                    calificacionesOldCalificacion.setUnCurso(null);
                    calificacionesOldCalificacion = em.merge(calificacionesOldCalificacion);
                }
            }
            for (Calificacion calificacionesNewCalificacion : calificacionesNew) {
                if (!calificacionesOld.contains(calificacionesNewCalificacion)) {
                    Curso oldUnCursoOfCalificacionesNewCalificacion = calificacionesNewCalificacion.getUnCurso();
                    calificacionesNewCalificacion.setUnCurso(curso);
                    calificacionesNewCalificacion = em.merge(calificacionesNewCalificacion);
                    if (oldUnCursoOfCalificacionesNewCalificacion != null && !oldUnCursoOfCalificacionesNewCalificacion.equals(curso)) {
                        oldUnCursoOfCalificacionesNewCalificacion.getCalificaciones().remove(calificacionesNewCalificacion);
                        oldUnCursoOfCalificacionesNewCalificacion = em.merge(oldUnCursoOfCalificacionesNewCalificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = curso.getId();
                if (findCurso(id) == null) {
                    throw new NonexistentEntityException("The curso with id " + id + " no longer exists.");
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
            Curso curso;
            try {
                curso = em.getReference(Curso.class, id);
                curso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The curso with id " + id + " no longer exists.", enfe);
            }
            List<Alumno> alumnos = curso.getAlumnos();
            for (Alumno alumnosAlumno : alumnos) {
                alumnosAlumno.getCursos().remove(curso);
                alumnosAlumno = em.merge(alumnosAlumno);
            }
            List<Calificacion> calificaciones = curso.getCalificaciones();
            for (Calificacion calificacionesCalificacion : calificaciones) {
                calificacionesCalificacion.setUnCurso(null);
                calificacionesCalificacion = em.merge(calificacionesCalificacion);
            }
            em.remove(curso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Curso> findCursoEntities() {
        return findCursoEntities(true, -1, -1);
    }

    public List<Curso> findCursoEntities(int maxResults, int firstResult) {
        return findCursoEntities(false, maxResults, firstResult);
    }

    private List<Curso> findCursoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Curso.class));
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

    public Curso findCurso(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }

    public int getCursoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Curso> rt = cq.from(Curso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
