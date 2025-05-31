# 🏥 Application de Gestion des Patients & Consultations

Une application JavaFX moderne pour gérer efficacement les **patients** et leurs **consultations médicales**, avec une interface fluide, organisée et intuitive.

---

## 📋 Description

Cette application desktop permet :
- La gestion des **patients** (ajout, modification, suppression, recherche)
- La gestion des **consultations** (ajout, modification, suppression, recherche)
- Une architecture MVC/DAO bien structurée pour un projet clair et évolutif
- Une interface JavaFX stylisée avec BootstrapFX

---

## ✨ Fonctionnalités principales

👤 **Gestion des Patients**
- ➕ Ajouter
- 📝 Modifier
- 🗑️ Supprimer
- 🔍 Rechercher
- ⚠️ Gestion des exceptions

🩺 **Gestion des Consultations**
- ➕ Ajouter
- 📝 Modifier
- 🗑️ Supprimer
- 🔍 Rechercher
- ⚠️ Gestion des exceptions

---
## 📸 Captures d'écran
Voici quelques aperçus de l'application :

### ➕👤🏥 Écran d'ajout un patient
![Écran d'accueil](https://github.com/OTH-BD/AppConsult_Patients/blob/main/capture/Ajouter_Patient.gif?raw=true)

### ✏️👤🏥 Écran de modifier un patient
![Écran d'accueil](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Modifier_Patient.gif?raw=true)

### 🗑️👤🏥 Écran de supprimer un patient
![Écran d'accueil](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Supprimer_Patient.gif?raw=true)

### 🔍👤🏥 Écran de Recherche un patient
![Écran d'accueil](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Rechercher_Patient.gif?raw=true)

### ⚠️👤🏥 Écran de gestion des exceptions d'un patient
![Écran d'accueil](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Gestion_Exception_Patient.gif?raw=true)

### ➕🩺 Ajouter une consultation
![Liste des patients](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Ajouter_Consultation.gif?raw=true)

### ✏️🩺 Modifier une consultation
![Détail consultation](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Modifier_Consultation.gif?raw=true)

### 🗑️🩺 Supprimer une consultation
![Détail consultation](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Supprimer_Consultation.gif?raw=true)

### 🔍🩺 Rechercher une consultation
![Détail consultation](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Rechercher_Consultation.gif?raw=true)

### ⚠️🩺 Écran de gestion des exceptions d'une consultation
![Détail consultation](https://github.com/ssifdine/AppG_Consult_Patients/blob/master/capture/Gestion_Exceprion_Consultation.gif?raw=true)  modifier structure d'affichage

## 🛠️ Technologies Utilisées

- **Java 23**
- **JavaFX** pour l’interface graphique
- **MySQL** pour la base de données
- **FXML** pour la définition d’interfaces
- **BootstrapFX** pour le style
- **DAO & MVC** pour l’organisation du code

---

## 📁 Structure du Projet (Résumé)

- `models/` : Modèles Patient et Consultation
- `dao/` : Interfaces & implémentations DAO
- `services/` : Interfaces & services métier
- `controllers/` : Contrôleurs FXML
- `resources/` : Fichiers FXML + CSS
- `config/` : Configuration base de données
- `Main.java` : Point d’entrée de l’application

---

## 🚀 Lancer le Projet

1. **Cloner le dépôt**

   ```bash
   git clone https://github.com/ssifdine/AppG_Consult_Patients.git
