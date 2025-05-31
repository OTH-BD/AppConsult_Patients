package net.othmane.hd.appg_consult_patients.models;

import java.util.List;

//Entité ou class persistente
public class Patient {
    private long id ;
    private String nom ;
    private String prenom ;
    private String tel ;
    private List<Consultation> consultations ;

    public Patient() {
    }


    public Patient(List<Consultation> consultations, String nom, String prenom, String tel) {
        this.consultations = consultations;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public String toString() {
        return nom + " " +prenom;
    }
}