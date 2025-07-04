package entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "prestiti")
public class Prestito {

    @Id
    @Column(name = "id_prestito")
    private int idPrestito;

    @ManyToOne
    @JoinColumn(name = "elemento_prestato")
    private Catalogo elemento;

    @Column(name = "data_inizio_prestito")
    private LocalDate dataInizioPrestito;

    @Column(name = "data_restituzione_previsto")
    private LocalDate dataRestituzionePrevisto;

    @Column(name = "data_restituzione_effettivo")
    private LocalDate dataRestituzioneEffettivo;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Prestito(int idPrestito, Catalogo elemento, LocalDate dataInizioPrestito, LocalDate dataRestituzioneEffettivo, LocalDate dataRestituzionePrevisto, Utente utente) {
        this.idPrestito = idPrestito;
        this.elemento = elemento;
        this.dataInizioPrestito = dataInizioPrestito;
        this.dataRestituzioneEffettivo = dataRestituzioneEffettivo;
        this.dataRestituzionePrevisto = dataRestituzionePrevisto;
        this.utente = utente;
    }

    public Prestito() {
    }

    public int getIdPrestito() {
        return idPrestito;
    }

    public void setIdPrestito(int idPrestito) {
        this.idPrestito = idPrestito;
    }

    public Catalogo getElemento() {
        return elemento;
    }

    public void setElemento(Catalogo elemento) {
        this.elemento = elemento;
    }

    public LocalDate getDataInizioPrestito() {
        return dataInizioPrestito;
    }

    public void setDataInizioPrestito(LocalDate dataInizioPrestito) {
        this.dataInizioPrestito = dataInizioPrestito;
    }

    public LocalDate getDataRestituzionePrevisto() {
        return dataRestituzionePrevisto;
    }

    public void setDataRestituzionePrevisto(LocalDate dataRestituzionePrevisto) {
        this.dataRestituzionePrevisto = dataRestituzionePrevisto;
    }

    public LocalDate getDataRestituzioneEffettivo() {
        return dataRestituzioneEffettivo;
    }

    public void setDataRestituzioneEffettivo(LocalDate dataRestituzioneEffettivo) {
        this.dataRestituzioneEffettivo = dataRestituzioneEffettivo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "idPrestito=" + idPrestito +
                ", elemento=" + elemento +
                ", dataInizioPrestito=" + dataInizioPrestito +
                ", dataRestituzionePrevisto=" + dataRestituzionePrevisto +
                ", dataRestituzioneEffettivo=" + dataRestituzioneEffettivo +
                ", utente=" + utente +
                '}';
    }
}
