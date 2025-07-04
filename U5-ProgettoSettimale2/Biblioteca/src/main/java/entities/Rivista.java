package entities;


import enums.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends Catalogo {


    @Enumerated(EnumType.STRING)
    private Periodicita periodo;


    public Rivista(int isbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodo) {
        super(isbn, titolo, annoPubblicazione, numeroPagine);
        this.periodo = periodo;
    }

    public Rivista() {
    }

    public Periodicita getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodicita periodo) {
        this.periodo = periodo;
    }


    @Override
    public String toString() {
        return super.toString() + " Rivista{" +
                "periodo=" + periodo +
                '}';
    }

}
