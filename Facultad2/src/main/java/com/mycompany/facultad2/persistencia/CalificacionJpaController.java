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
import com.mycompany.facultad2.logica.Curso;
import com.mycompany.facultad2.logica.Alumno;
import com.mycompany.facultad2.logica.Calificacion;
import com.mycompany.facultad2.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author matia
 */
public class CalificacionJpaController implements Serializable {

    public CalificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CalificacionJpaController() {
        emf = Persistence.createEntityManagerFactory("facultad2PU");
    }

    public void create(Calificacion calificacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Curso unCurso = calificacion.getUnCurso();
            if (unCurso != null) {
                unCurso = em.getReference(unCurso.getClass(), unCurso.getId());
                calificacion.setUnCurso(unCurso);
            }
            Alumno unAlumno = calificacion.getUnAlumno();
            if (unAlumno != null) {
                unAlumno = em.getReference(unAlumno.getClass(), unAlumno.getId());
                calificacion.setUnAlumno(unAlumno);
            }
            em.persist(calificacion);
            if (unCurso != null) {
                unCurso.getCalificaciones().add(calificacion);
                unCurso = em.merge(unCurso);
            }
            if (unAlumno != null) {
                unAlumno.getCalificaciones().add(calificacion);
                unAlumno = em.merge(unAlumno);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Calificacion calificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Calificacion persistentCalificacion = em.find(Calificacion.class, calificacion.getId());
            Curso unCursoOld = persistentCalificacion.getUnCurso();
            Curso unCursoNew = calificacion.getUnCurso();
            Alumno unAlumnoOld = persistentCalificacion.getUnAlumno();
            Alumno unAlumnoNew = calificacion.getUnAlumno();
            if (unCursoNew != null) {
                unCursoNew = em.getReference(unCursoNew.getClass(), unCursoNew.getId());
                calificacion.setUnCurso(unCursoNew);
            }
            if (unAlumnoNew != null) {
                unAlumnoNew = em.getReference(unAlumnoNew.getClass(), unAlumnoNew.getId());
                calificacion.setUnAlumno(unAlumnoNew);
            }
            calificacion = em.merge(calificacion);
            if (unCursoOld != null && !unCursoOld.equals(unCursoNew)) {
                unCursoOld.getCalificaciones().remove(calificacion);
                unCursoOld = em.merge(unCursoOld);
            }
            if (unCursoNew != null && !unCursoNew.equals(unCursoOld)) {
                unCursoNew.getCalificaciones().add(calificacion);
                unCursoNew = em.merge(unCursoNew);
            }
            if (unAlumnoOld != null && !unAlumnoOld.equals(unAlumnoNew)) {
                unAlumnoOld.getCalificaciones().remove(calificacion);
                unAlumnoOld = em.merge(unAlumnoOld);
            }
            if (unAlumnoNew != null && !unAlumnoNew.equals(unAlumnoOld)) {
                unAlumnoNew.getCalificaciones().add(calificacion);
                unAlumnoNew = em.merge(unAlumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = calificacion.getId();
                if (findCalificacion(id) == null) {
                    throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.");
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
            Calificacion calificacion;
            try {
                calificacion = em.getReference(Calificacion.class, id);
                calificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The calificacion with id " + id + " no longer exists.", enfe);
            }
            Curso unCurso = calificacion.getUnCurso();
            if (unCurso != null) {
                unCurso.getCalificaciones().remove(calificacion);
                unCurso = em.merge(unCurso);
            }
            Alumno unAlumno = calificacion.getUnAlumno();
            if (unAlumno != null) {
                unAlumno.getCalificaciones().remove(calificacion);
                unAlumno = em.merge(unAlumno);
            }
            em.remove(calificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Calificacion> findCalificacionEntities() {
        return findCalificacionEntities(true, -1, -1);
    }

    public List<Calificacion> findCalificacionEntities(int maxResults, int firstResult) {
        return findCalificacionEntities(false, maxResults, firstResult);
    }

    private List<Calificacion> findCalificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Calificacion.class));
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

    public Calificacion findCalificacion(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Calificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCalificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Calificacion> rt = cq.from(Calificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
