package net.othmane.hd.appg_consult_patients.services.patient;

import net.othmane.hd.appg_consult_patients.models.Patient;

import java.util.List;

public interface IPatientService {
    void addPatient(Patient patient);
    void updatePatient(Patient patient);
    void deletePatient(Patient patient);
    List<Patient> getPatients();
    Patient getPatientById(Long id);
    List<Patient> searchPatientsByQuery(String query);
}
