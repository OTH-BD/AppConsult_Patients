package net.othmane.hd.appg_consult_patients.services.patient;

import net.othmane.hd.appg_consult_patients.dao.patient.IPatientDao;
import net.othmane.hd.appg_consult_patients.models.Patient;

import java.sql.SQLException;
import java.util.List;

public class PatientService implements IPatientService{

    private IPatientDao patientDao;

    public PatientService(IPatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void addPatient(Patient patient)  {
        try {
            patientDao.create(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePatient(Patient patient)  {
        try {
            patientDao.update(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deletePatient(Patient patient)  {
        try {
            patientDao.delete(patient);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Patient> getPatients()  {
        List<Patient> patients = null;
        try {
            patients = patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = null;
        try {
            patient = patientDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patient;
    }

    @Override
    public List<Patient> searchPatientsByQuery(String query) {
        try {
            return patientDao.searchByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
