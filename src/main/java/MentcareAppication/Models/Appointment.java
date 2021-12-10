package MentcareAppication.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "APPOINTMENT_ID")
    private long appointmentID;

    @ManyToOne(targetEntity = Medic.class)
    @JoinColumn(name = "MEDIC_ID")
    private Medic medic;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    private Date appointmentDate;
    private String clinic;

    public Appointment(Medic medic, Patient patient, Date appointmentDate, String clinic) {
        this.medic = medic;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.clinic = clinic;
    }

    public long getAppointmentID() {
        return appointmentID;
    }

    public Medic getMedic() {
        return medic;
    }

    public void setMedic(Medic medic) {
        this.medic = medic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}
