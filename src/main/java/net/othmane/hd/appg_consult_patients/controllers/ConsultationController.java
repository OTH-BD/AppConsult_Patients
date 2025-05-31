package net.othmane.hd.appg_consult_patients.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import net.othmane.hd.appg_consult_patients.dao.consultation.ConsultationDao;
import net.othmane.hd.appg_consult_patients.dao.patient.PatientDao;
import net.othmane.hd.appg_consult_patients.models.Consultation;
import net.othmane.hd.appg_consult_patients.models.Patient;
import net.othmane.hd.appg_consult_patients.services.consultation.ConsultationService;
import net.othmane.hd.appg_consult_patients.services.consultation.IConsultationService;
import net.othmane.hd.appg_consult_patients.services.patient.IPatientService;
import net.othmane.hd.appg_consult_patients.services.patient.PatientService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {

    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation, Long> columnId;
    @FXML private TableColumn<Consultation, Date> columnDateConsultation;
    @FXML private TableColumn<Consultation, String> columnDescription;
    @FXML private TableColumn<Consultation, Patient> columnPatient;
    @FXML private DatePicker datePickerConsultation;
    @FXML private ComboBox<Patient> comboBoxPatient;
    @FXML private TextArea textareaDescription;
    @FXML private TextField textFieldSearch;
    @FXML private Label statusLabel;
    private IConsultationService consultationService;
    private IPatientService patientService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    private Consultation selectedConsultation;

    // Add a refresh timer to periodically check for new patients
    private Timeline patientRefreshTimer;

    private void clearFields(){
        datePickerConsultation.setValue(null);
        textareaDescription.clear();
        comboBoxPatient.setValue(null);
    }

    private void loadConsultations() {
        consultations.setAll(consultationService.getConsultations());
    }

    public void loadPatients() {
        patients.setAll(patientService.getPatients());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        consultationService = new ConsultationService(new ConsultationDao());
        patientService = new PatientService(new PatientDao());

        // Initial load of patients
        loadPatients();
        comboBoxPatient.setItems(patients);

        // Set up columns
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));

        // Load consultations
        loadConsultations();
        tableConsultation.setItems(consultations);

        // Configure search functionality
        textFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            consultations.setAll(consultationService.searchConsultationsByQuery(newValue));
        });

        // Configure selection listener for the consultation table
        tableConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null){
                textareaDescription.setText(newValue.getDescription());
                datePickerConsultation.setValue(newValue.getDate().toLocalDate());
                comboBoxPatient.setValue(newValue.getPatient());
                selectedConsultation = newValue;
            }
        });

        // Set up a timer to refresh the patient list every 2 seconds
        patientRefreshTimer = new Timeline(new KeyFrame(
                Duration.seconds(2),
                event -> refreshPatientList()
        ));
        patientRefreshTimer.setCycleCount(Timeline.INDEFINITE);
        patientRefreshTimer.play();
    }

    // Method to refresh the patient list while preserving selection
    private void refreshPatientList() {
        Patient selectedPatient = comboBoxPatient.getValue();
        patients.setAll(patientService.getPatients());

        // If a patient was selected before, try to reselect it
        if (selectedPatient != null) {
            for (Patient patient : patients) {
                if (patient.getId() == selectedPatient.getId()) {
                    comboBoxPatient.setValue(patient);
                    break;
                }
            }
        }
    }

    // Don't forget to stop the timer when the controller is no longer needed
    public void cleanup() {
        if (patientRefreshTimer != null) {
            patientRefreshTimer.stop();
        }
    }

    public void ajouterConsultation() {
        if (textareaDescription.getText().isEmpty() || datePickerConsultation.getValue() == null || comboBoxPatient.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ATTENTION ⛔📛💥");
            alert.setHeaderText("Veuillez remplir tous les champs de la consultation !");
            alert.showAndWait();
            return;
        }

        Consultation consultation = new Consultation();
        consultation.setDate(Date.valueOf(datePickerConsultation.getValue()));
        consultation.setPatient(comboBoxPatient.getSelectionModel().getSelectedItem());
        consultation.setDescription(textareaDescription.getText());

        try {
            consultationService.addConsultation(consultation);
            statusLabel.setText("La consultation a été ajoutée.");

            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(3),
                    event -> statusLabel.setText("")
            ));
            timeline.setCycleCount(1);
            timeline.play();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur ❌");
            alert.setHeaderText("Une erreur s'est produite lors de l'ajout de la consultation.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        loadConsultations();
        clearFields();
    }

    public void modifierConsultation(){
        if(selectedConsultation == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION ⛔📛💥");
            alert.setHeaderText("Veuillez selectionner une consultation avant la modification");
            alert.showAndWait();
            return;
        }
        else {
            selectedConsultation.setDate(Date.valueOf(datePickerConsultation.getValue()));
            selectedConsultation.setPatient(comboBoxPatient.getSelectionModel().getSelectedItem());
            selectedConsultation.setDescription(textareaDescription.getText());
            consultationService.updateConsultation(selectedConsultation);
            statusLabel.setText("La consultation a été modifiée.");
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event->{
                statusLabel.setText("");
            }));
            timeline.setCycleCount(1);
            timeline.play();
            loadConsultations();
            clearFields();
            selectedConsultation = null;
        }
    }

    public void supprimerConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        if(consultation == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATTENTION ⛔📛💥");
            alert.setHeaderText("Veuillez selectionner une consultation avant la suppression");
            alert.showAndWait();
            return;
        }
        consultationService.deleteConsultation(consultation);
        statusLabel.setText("La consultation a été supprimée.");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event->{
            statusLabel.setText("");
        }));
        timeline.setCycleCount(1);
        timeline.play();
        loadConsultations();
        clearFields();
    }
}