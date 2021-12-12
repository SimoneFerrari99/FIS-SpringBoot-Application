package MentcareAppication.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "PATIENT_ID")
    private long patientID;

    @ManyToOne(targetEntity = Medic.class)
    @JoinColumn(name = "MEDIC_ID")
    private Medic medic;

    private String firstname;
    private String lastname;
    private String cf;
    private String birthDate;
    private String cityOfResidence;
    private String problemCategory;
    private String problemDescription;
    private boolean confirmed;

    private boolean dangerous;

    public Patient(){}

    public Patient(Medic medic, String firstname, String lastname, String cf, String birthDate, String cityOfResidence, String problemCategory, String problemDescription, boolean dangerous) {
        this.medic = medic;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cf = cf;
        this.birthDate = birthDate;
        this.cityOfResidence = cityOfResidence;
        this.problemCategory = problemCategory;
        this.problemDescription = problemDescription;
        this.dangerous = dangerous;
        this.confirmed = medic != null;

    }

    public long getPatientID() {
        return patientID;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
        this.setConfirmed(medic != null);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public LocalDate getBirthDateToLocalDate() {
        return LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getBirthDateToDate(){
        String[] parts = birthDate.split("/");
        return parts[2]+'-'+parts[1]+'-'+parts[0];
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(birthDate);
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getProblemCategory() {
        return problemCategory;
    }

    public void setProblemCategory(String problemCategory) {
        this.problemCategory = problemCategory;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public boolean isDangerous() {
        return dangerous;
    }

    public void setDangerous(boolean dangerous) {
        this.dangerous = dangerous;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getFirstnameLastname(){
        return firstname + ' ' + lastname;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientID=" + patientID +
                ", medic=" + medic +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", cf='" + cf + '\'' +
                ", birthDate=" + birthDate +
                ", cityOfResidence='" + cityOfResidence + '\'' +
                ", problemCategory='" + problemCategory + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", dangerous=" + dangerous +
                '}';
    }
}
