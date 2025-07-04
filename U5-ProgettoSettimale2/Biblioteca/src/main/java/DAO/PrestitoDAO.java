package DAO;

import entities.Libro;
import entities.Prestito;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

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

    public List<Prestito> onLoan() {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista >= :dataOdierna",
                Prestito.class
        );
        query.setParameter("dataOdierna", LocalDate.now());
        return query.getResultList();
    }

    public List<Prestito> onLoanById(int id) {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista >= :dataOdierna AND p.idPrestito = :id",
                Prestito.class
        );
        query.setParameter("dataOdierna", LocalDate.now());
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Prestito> notLoan() {
        TypedQuery<Prestito> query = entityManager.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzioneEffettiva < :dataOdierna",
                Prestito.class
        );
        query.setParameter("dataOdierna", LocalDate.now());
        return query.getResultList();
    }

}
