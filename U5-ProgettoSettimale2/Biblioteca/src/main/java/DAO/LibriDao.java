package DAO;

import entities.Libro;
import entities.Rivista;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LibriDao {

    private EntityManager entityManager;

    public LibriDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Libro libro) {

        EntityTransaction et = entityManager.getTransaction();
        et.begin();

        entityManager.persist(libro);

        et.commit();

        System.out.println("il libro "+libro+" è stato correttamente salvato");

    }

    public Libro findById(int id){

        Libro trovato=entityManager.find(Libro.class,id);
        if(trovato==null)
            throw new NotFoundException(id);

        return trovato;

    }

    public  void deleteById(int id){
        Libro daEliminare=this.findById(id);
        EntityTransaction et=entityManager.getTransaction();
        et.begin();
        entityManager.remove(daEliminare);
        et.commit();

        System.out.println("libro "+daEliminare+" è stato eliminato");

    }

    public List<Libro> findByYear(int anno){
        TypedQuery<Libro> query= entityManager.createQuery("SELECT l from Libro l where l.annoPubblicazione= :anno", Libro.class);
        query.setParameter("anno",anno);
        return query.getResultList();
    }
    public List<Libro> findByAutor(String autore){
        String autoreDacercare=autore+"%";
        TypedQuery<Libro> query= entityManager.createQuery("SELECT l from Libro l where l.autore LIKE :autoreDacercare", Libro.class);
        query.setParameter("autoreDacercare",autoreDacercare);
        return query.getResultList();
    }

    public List<Libro> findByTitol(String titolo){
        String titoloDacercare="%"+titolo+"%";
        TypedQuery<Libro> query= entityManager.createQuery("SELECT l from Libro l where l.titolo LIKE :titoloDacercare", Libro.class);
        query.setParameter("titoloDacercare",titoloDacercare);
        return query.getResultList();
    }

}
