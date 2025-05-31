package net.othmane.hd.appg_consult_patients.dao.consultation;


import net.othmane.hd.appg_consult_patients.config.DatabaseConfig;
import net.othmane.hd.appg_consult_patients.dao.patient.PatientDao;
import net.othmane.hd.appg_consult_patients.models.Consultation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IConsultationDao{
    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO CONSULTATIONS(dateConsultation,description,id_patient) VALUES(?,?,?)");
        pstm.setDate(1, consultation.getDate());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3,consultation.getPatient().getId());
        pstm.executeUpdate();
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("UPDATE CONSULTATIONS SET dateConsultation=?,description=?,id_patient=? WHERE id_consultation=?");
        pstm.setDate(1, consultation.getDate());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3,consultation.getPatient().getId());
        pstm.setLong(4, consultation.getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("DELETE FROM CONSULTATIONS WHERE id_consultation = ?");
        pstm.setLong(1,consultation.getId());
        pstm.executeUpdate();

    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM CONSULTATIONS WHERE id_consultation = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        Consultation consultation = new Consultation();
        if(rs.next()){
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDescription(rs.getString("description"));
            consultation.setDate(rs.getDate("date_consultation"));
            consultation.setPatient(new PatientDao().findById(rs.getLong("id_patient")));
        }
        return consultation;
    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM CONSULTATIONS");
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();
        while(rs.next()){
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDescription(rs.getString("description"));
            consultation.setDate(rs.getDate("dateConsultation"));
            consultation.setPatient(new PatientDao().findById(rs.getLong("id_patient")));
            consultations.add(consultation);
        }
        return consultations;
    }

    @Override
    public List<Consultation> searchByQuery(String query) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT c.* FROM CONSULTATIONS C INNER JOIN PATIENTS P ON  C.id_patient = P.id_patient WHERE C.DESCRIPTION like ? OR P.NOM like ? OR P.PRENOM like ? ");
        pstm.setString(1,"%"+query+"%");
        pstm.setString(2,"%"+query+"%");
        pstm.setString(3,"%"+query+"%");
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();
        while(rs.next()){
            Consultation c = new Consultation();
            c.setId(rs.getLong("id_consultation"));
            c.setDate(rs.getDate("dateConsultation"));
            c.setDescription(rs.getString("description"));
            c.setPatient(new PatientDao().findById(rs.getLong("id_patient")));
            consultations.add(c);
        }
        return consultations;
    }
}