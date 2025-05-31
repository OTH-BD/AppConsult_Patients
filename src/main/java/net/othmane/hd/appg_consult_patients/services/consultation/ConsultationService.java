package net.othmane.hd.appg_consult_patients.services.consultation;

import net.othmane.hd.appg_consult_patients.dao.consultation.IConsultationDao;
import net.othmane.hd.appg_consult_patients.models.Consultation;

import java.sql.SQLException;
import java.util.List;

public class ConsultationService implements IConsultationService {

    private IConsultationDao consultationDao;

    public ConsultationService(IConsultationDao consultationDao) {
        this.consultationDao = consultationDao;
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try {
            consultationDao.create(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> getConsultations() {
        List<Consultation> consultations = null;
        try {
            consultations = consultationDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }

    @Override
    public Consultation getConsultationById(Long id) {
        Consultation consultation = null;
        try {
            consultation = consultationDao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultation;
    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try {
            consultationDao.update(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try {
            consultationDao.delete(consultation);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Consultation> searchConsultationsByQuery(String query) {
        List<Consultation> consultations = null;
        try {
            consultations = consultationDao.searchByQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consultations;
    }
}
