package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "MEDIC_ID")
    private long medicID;

    private String firstname;
    private String lastname;
    private String specialization;
    private String specializationDescription;

    public Medic(){ }

    public Medic(String firstname, String lastname, String specialization, String specializationDescription) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialization = specialization;
        this.specializationDescription = specializationDescription;
    }

    public long getMedicID() {
        return medicID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "medicID=" + medicID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", specialization='" + specialization + '\'' +
                ", specializationDescription='" + specializationDescription + '\'' +
                '}';
    }
}
