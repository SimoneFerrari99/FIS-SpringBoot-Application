package MentcareApplication.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "APPOINTMENT_ID")
    private long appointmentID;

    @ManyToOne(targetEntity = Medic.class)
    @JoinColumn(name = "MEDIC_ID")
    private Medic medic;

    @ManyToOne(targetEntity = Patient.class)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    private String appointmentDate;
    private String clinic;
    private boolean active;

    private Appointment(){}

    public Appointment(Medic medic, Patient patient, String appointmentDate, String clinic) {
        this.medic = medic;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        this.clinic = clinic;
        this.active = true;
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

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public LocalDate getAppointmentDateToLocalDate() {
        return LocalDate.parse(appointmentDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getAppointmentDateToDate(){
        String[] parts = appointmentDate.split("/");
        return parts[2]+'-'+parts[1]+'-'+parts[0];
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(appointmentDate);
    }


    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentID=" + appointmentID +
                ", medic=" + medic +
                ", patient=" + patient +
                ", appointmentDate=" + appointmentDate +
                ", clinic='" + clinic + '\'' +
                '}';
    }
}
