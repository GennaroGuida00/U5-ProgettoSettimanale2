package DAO;

import entities.Libro;
import entities.Rivista;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

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
}
