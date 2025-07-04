package entities;


import enums.Periodicità;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends Catalogo {

    private Periodicità periodo;

    public Rivista(int isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicità periodo) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodo = periodo;
    }

    public Rivista() {
    }

}
