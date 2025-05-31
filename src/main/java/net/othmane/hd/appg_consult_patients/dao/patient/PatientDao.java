package net.othmane.hd.appg_consult_patients.dao.patient;


import net.othmane.hd.appg_consult_patients.config.DatabaseConfig;
import net.othmane.hd.appg_consult_patients.models.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements IPatientDao{
    @Override
    public void create(Patient patient) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm= con.prepareStatement("insert into patients(nom,prenom,tel) values (?,?,?)");
        pstm.setString(1, patient.getNom());
        pstm.setString(2, patient.getPrenom());
        pstm.setString(3, patient.getTel());
        pstm.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm= con.prepareStatement("Update patients set nom=?,prenom=?,tel=? where id_patient = ?");
        pstm.setString(1, patient.getNom());
        pstm.setString(2, patient.getPrenom());
        pstm.setString(3, patient.getTel());
        pstm.setLong(4, patient.getId());
        pstm.executeUpdate();
    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm= con.prepareStatement("Delete from patients where id_patient = ?");
        pstm.setLong(1, patient.getId());
        pstm.executeUpdate();
    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm= con.prepareStatement("SELECT * FROM Patients where id_patient = ?");
        pstm.setLong(1,id);
        ResultSet rs =  pstm.executeQuery();
        Patient p = new Patient();
        if (rs.next()){
            p.setId(rs.getLong("id_patient"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTel(rs.getString("tel"));
        }
        return p;
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM PATIENTS");
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<Patient>();
        while(rs.next()){
            Patient p = new Patient();
            p.setId(rs.getLong("id_patient"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTel(rs.getString("tel"));
            patients.add(p);
        }
        return patients;
    }

    @Override
    public List<Patient> searchByQuery(String query) throws SQLException {
        Connection con = DatabaseConfig.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("SELECT * FROM PATIENTS WHERE NOM like ? OR PRENOM like ? ");
        pstm.setString(1,"%"+query+"%");
        pstm.setString(2,"%"+query+"%");
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while(rs.next()){
            Patient p = new Patient();
            p.setId(rs.getLong("id_patient"));
            p.setNom(rs.getString("nom"));
            p.setPrenom(rs.getString("prenom"));
            p.setTel(rs.getString("tel"));
            patients.add(p);
        }
        return patients;
    }
}