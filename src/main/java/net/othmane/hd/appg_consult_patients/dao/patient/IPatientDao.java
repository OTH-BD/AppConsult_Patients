package net.othmane.hd.appg_consult_patients.dao.patient;

import net.othmane.hd.appg_consult_patients.dao.Dao;
import net.othmane.hd.appg_consult_patients.models.Patient;

import java.sql.SQLException;
import java.util.List;

public interface IPatientDao extends Dao<Patient,Long> {
    List<Patient> searchByQuery(String query) throws SQLException;
}
