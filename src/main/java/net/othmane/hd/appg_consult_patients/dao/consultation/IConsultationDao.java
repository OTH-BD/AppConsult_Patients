package net.othmane.hd.appg_consult_patients.dao.consultation;

import net.othmane.hd.appg_consult_patients.dao.Dao;
import net.othmane.hd.appg_consult_patients.models.Consultation;

import java.sql.SQLException;
import java.util.List;

public interface IConsultationDao extends Dao<Consultation, Long> {
    List<Consultation> searchByQuery(String query) throws SQLException;
}
