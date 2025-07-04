package DAO;

import entities.Utente;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {

    private EntityManager entityManager;

    public UtenteDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Utente utente) {

        EntityTransaction et = entityManager.getTransaction();
        et.begin();

        entityManager.persist(utente);

        et.commit();

        System.out.println("l'utente "+utente+" è stato correttamente salvato");

    }

    public Utente findById(int id){

        Utente trovato=entityManager.find(Utente.class,id);
        if(trovato==null)
            throw new NotFoundException(id);

        return trovato;

    }

    public  void deleteById(int id){
        Utente daEliminare=this.findById(id);
        EntityTransaction et=entityManager.getTransaction();
        et.begin();
        entityManager.remove(daEliminare);
        et.commit();

        System.out.println("l'utente "+daEliminare+" è stato eliminato");

    }
}
