package net.othmane.hd.appg_consult_patients.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/clinique_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private static DatabaseConfig instance;

    private DatabaseConfig() {
        createTables(); // Exécute la création des tables au démarrage
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            instance = new DatabaseConfig();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void createTables() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            // Création de la table Patient
            String createPatientTable = "CREATE TABLE IF NOT EXISTS PATIENTS ("
                    + "id_patient INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nom VARCHAR(255) NOT NULL, "
                    + "prenom VARCHAR(255) NOT NULL, "
                    + "tel VARCHAR(20) NOT NULL"
                    + ")";
            statement.executeUpdate(createPatientTable);

            // Création de la table Consultation
            String createConsultationTable = "CREATE TABLE IF NOT EXISTS CONSULTATIONS ("
                    + "id_consultation INT AUTO_INCREMENT PRIMARY KEY, "
                    + "dateConsultation DATE NOT NULL, "
                    + "description TEXT NOT NULL, "
                    + "id_patient INT NOT NULL, "
                    + "FOREIGN KEY (id_patient) REFERENCES PATIENTS(id_patient) ON DELETE CASCADE"
                    + ")";
            statement.executeUpdate(createConsultationTable);

            System.out.println("✅ Tables créées avec succès.");
        } catch (SQLException e) {
            System.err.println("❌ Erreur lors de la création des tables: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
