package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf= Persistence.createEntityManagerFactory("u5progettosettimanalepu");
        EntityManager em= emf.createEntityManager();

        emf.close();
        em.close();
    }
}
