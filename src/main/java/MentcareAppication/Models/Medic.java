package MentcareAppication.Models;

import javax.persistence.*;

@Entity
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "MEDIC_ID")
    private long medicID;

    private final String firstname;
    private final String lastname;
    private final String specialization;
    private final String specializationDescription;

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
}
