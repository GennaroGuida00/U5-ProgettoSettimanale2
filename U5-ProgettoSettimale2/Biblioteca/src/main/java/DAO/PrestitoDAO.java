package DAO;

import entities.Libro;
import entities.Prestito;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PrestitoDAO {

    private EntityManager entityManager;

    public PrestitoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Prestito prestito) {

        EntityTransaction et = entityManager.getTransaction();
        et.begin();

        entityManager.persist(prestito);

        et.commit();

        System.out.println("il prestito "+prestito+" è stato correttamente salvato");

    }

    public Prestito findById(int id){

        Prestito trovato=entityManager.find(Prestito.class,id);
        if(trovato==null)
            throw new NotFoundException(id);

        return trovato;

    }

    public  void deleteById(int id){
        Prestito daEliminare=this.findById(id);
        EntityTransaction et=entityManager.getTransaction();
        et.begin();
        entityManager.remove(daEliminare);
        et.commit();

        System.out.println("il prestito "+daEliminare+" è stato eliminato");

    }
}
