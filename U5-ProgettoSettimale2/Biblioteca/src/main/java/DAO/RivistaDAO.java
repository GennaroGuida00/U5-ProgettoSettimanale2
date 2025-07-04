package DAO;

import entities.Libro;
import entities.Rivista;
import exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public List<Rivista> findByYear(int anno) {
        TypedQuery<Rivista> query = entityManager.createQuery(
                "SELECT l FROM Rivista l WHERE l.annoPubblicazione = :anno", Rivista.class
        );
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public List<Rivista> findByAutor(String autore){
        String autoreDacercare=autore+"%";
        TypedQuery<Rivista> query= entityManager.createQuery("SELECT l from Libro l where l.autore LIKE :autoreDacercare", Rivista.class);
        query.setParameter("autoreDacercare",autoreDacercare);
        return query.getResultList();
    }

    public List<Rivista> findByTitol(String titolo){
        String titoloDacercare = "%" + titolo + "%";
        TypedQuery<Rivista> query = entityManager.createQuery(
                "SELECT l FROM Rivista l WHERE l.titolo LIKE :titoloDacercare", Rivista.class
        );
        query.setParameter("titoloDacercare", titoloDacercare);
        return query.getResultList();
    }

}
