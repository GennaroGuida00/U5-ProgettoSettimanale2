package entities;

import enums.Genere;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table (name = "libri")
public class Libro extends Catalogo{
    private String autore;
    private Genere genere;

    public Libro(int isbn, String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere=" + genere +
                '}';
    }
}
