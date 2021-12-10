package MentcareAppication.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "PATIENT_ID")
    private long patientID;

    @ManyToOne(targetEntity = Medic.class)
    @JoinColumn(name = "MEDIC_ID")
    private Medic medic;

    private String firstname;
    private String lastname;
    private String cf;
    private Date birthDate;
    private String cityOfResidence;
    private String problemCategory;
    private String problemDescription;

    private boolean dangerous;

    public Patient(Medic medic, String firstname, String lastname, String cf, Date birthDate, String cityOfResidence, String problemCategory, String problemDescription, boolean dangerous) {
        this.medic = medic;
        this.firstname = firstname;
        this.lastname = lastname;
        this.cf = cf;
        this.birthDate = birthDate;
        this.cityOfResidence = cityOfResidence;
        this.problemCategory = problemCategory;
        this.problemDescription = problemDescription;
        this.dangerous = dangerous;
    }

    public long getPatientID() {
        return patientID;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
}
