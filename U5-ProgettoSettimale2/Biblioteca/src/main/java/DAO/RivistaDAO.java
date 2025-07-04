package DAO;

import entities.Rivista;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RivistaDAO {

    private EntityManager entityManager;

    public RivistaDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Rivista rivista) {

        EntityTransaction et = entityManager.getTransaction();
        et.begin();

        entityManager.persist(rivista);

        et.commit();

        System.out.println("la rivista "+rivista+" è stato correttamente salvato");

    }

    public Rivista findById(int id){

        Rivista trovato=entityManager.find(Rivista.class,id);
        if(trovato==null)
            throw new NotFoundException(id);

        return trovato;

    }

    public  void deleteById(int id){
        Rivista daEliminare=this.findById(id);
        EntityTransaction et=entityManager.getTransaction();
        et.begin();
        entityManager.remove(daEliminare);
        et.commit();

        System.out.println("rivista "+daEliminare+" è stata eliminata");

    }
}
