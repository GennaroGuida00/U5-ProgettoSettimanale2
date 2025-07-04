package org.example;

import DAO.LibriDao;
import DAO.PrestitoDAO;
import DAO.RivistaDAO;
import DAO.UtenteDAO;
import entities.Libro;
import entities.Rivista;
import enums.Genere;
import enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("u5progettosettimanalepu");
        EntityManager em = emf.createEntityManager();
        Scanner in = new Scanner(System.in);

        RivistaDAO rd = new RivistaDAO(em);
        PrestitoDAO pd = new PrestitoDAO(em);
        LibriDao ld = new LibriDao(em);
        UtenteDAO ud = new UtenteDAO(em);

        int scelta;
        int sceltaCatalogo;
        int id, anno, pagine;
        String titolo, autore;
        Genere genere;
        Periodicita periodo;

        System.out.println("Benvenuto all'interno della biblioteca, scegli che operazione eseguire (premi 0 per uscire):");

        do {

            System.out.println("1) Aggiunta di un elemento del catalogo");
            System.out.println("2) Rimozione di un elemento del catalogo dato un codice ISBN");
            System.out.println("3) Ricerca per anno di pubblicazione");
            System.out.println("4) Ricerca per titolo o parte di esso");
            System.out.println("5) Ricerca degli elementi attualmente in prestito");
            System.out.println("6) Ricerca di un prestito per ID");
            System.out.println("7) Ricerca di tutti i prestiti scaduti e non ancora restituiti");
            System.out.print("Scelta: ");
            scelta = in.nextInt();
            in.nextLine();

            switch (scelta) {
                case 1:

                    System.out.println("Quale catalogo vuoi selezionare?");
                    System.out.println("1) Rivista");
                    System.out.println("1) Libro");
                    sceltaCatalogo = in.nextInt();
                    in.nextLine();

                    if (sceltaCatalogo == 1) {

                        System.out.print("Inserisci codice ISBN: ");
                        id = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci anno pubblicazione: ");
                        anno = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci numero pagine: ");
                        pagine = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci titolo: ");
                        titolo = in.nextLine();
                        System.out.print("Inserisci periodo (SETTIMANALE, MENSILE, SEMESTRALE): ");
                        periodo = Periodicita.valueOf(in.nextLine().toUpperCase());

                        rd.save(new Rivista(id, titolo, anno, pagine, periodo));
                    } else {

                        System.out.print("Inserisci codice ISBN: ");
                        id = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci anno pubblicazione: ");
                        anno = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci numero pagine: ");
                        pagine = in.nextInt();
                        in.nextLine();
                        System.out.print("Inserisci titolo: ");
                        titolo = in.nextLine();
                        System.out.print("Inserisci autore: ");
                        autore = in.nextLine();
                        System.out.print("Inserisci genere (FANTASY, ADVENTURE, ROMANCE, HORROR): ");
                        genere = Genere.valueOf(in.nextLine().toUpperCase());

                        ld.save(new Libro(id, titolo, anno, pagine, autore, genere));
                    }

                    sceltaCatalogo = 0;
                    break;

                case 2:

                    System.out.println("Quale elemento vuoi eliminare?");
                    System.out.println("1) Rivista");
                    System.out.println("1) Libro");
                    sceltaCatalogo = in.nextInt();
                    in.nextLine();

                    System.out.print("Inserisci codice ISBN: ");
                    id = in.nextInt();
                    in.nextLine();

                    if (sceltaCatalogo == 1) {
                        rd.deleteById(id);
                    } else {
                        ld.deleteById(id);
                    }

                    sceltaCatalogo = 0;
                    break;

                case 3:

                    System.out.println("Ricerca per anno. Seleziona tipo:");
                    System.out.println("1) Rivista");
                    System.out.println("1) Libro");
                    sceltaCatalogo = in.nextInt();
                    in.nextLine();

                    System.out.print("Inserisci anno: ");
                    anno = in.nextInt();
                    in.nextLine();

                    if (sceltaCatalogo == 1) {
                        rd.findByYear(anno).forEach(System.out::println);
                    } else {
                        ld.findByYear(anno).forEach(System.out::println);
                    }

                    sceltaCatalogo = 0;
                    break;

                case 4:

                    System.out.println("Ricerca per titolo. Seleziona tipo:");
                    System.out.println("1) Rivista");
                    System.out.println("1) Libro");
                    sceltaCatalogo = in.nextInt();
                    in.nextLine();

                    System.out.print("Inserisci titolo o parte di esso: ");
                    titolo = in.nextLine();

                    if (sceltaCatalogo == 1) {
                        rd.findByTitol(titolo).forEach(System.out::println);
                    } else {
                        ld.findByTitol(titolo).forEach(System.out::println);
                    }

                    sceltaCatalogo = 0;
                    break;

                case 5:

                    pd.onLoan().forEach(System.out::println);
                    break;

                case 6:

                    System.out.print("Inserisci ID prestito: ");
                    id = in.nextInt();
                    in.nextLine();

                    pd.onLoanById(id).forEach(System.out::println);
                    break;

                case 7:

                    pd.notLoan().forEach(System.out::println);
                    break;

                case 0:
                    System.out.println("Arrivederci");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }

        } while (scelta != 0);

        em.close();
        emf.close();
    }
}
