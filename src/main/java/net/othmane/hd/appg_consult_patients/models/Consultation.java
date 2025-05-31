package net.othmane.hd.appg_consult_patients.models;

import java.sql.Date;

public class Consultation {

    private long id;
    private Date date;
    private String description;
    private Patient patient;

    public Consultation() {
    }

    public Consultation(String description, long id, Patient patient) {
        this.description = description;
        this.id = id;
        this.patient = patient;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return "Consultation{" +
                "date=" + date +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", patient=" + patient +
                '}';
    }
}