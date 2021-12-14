package MentcareApplication.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "REQUEST_ID")
    private long requestID;

    @OneToOne(targetEntity = Patient.class)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    private String requestDate;
    private boolean active;

    private Request(){}

    public Request(Patient patient, String requestDate) {
        this.patient = patient;
        this.requestDate = requestDate;
        this.active = true;
    }

    public long getRequestID() {
        return requestID;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public LocalDate getRequestDateToLocalDate() {
        return LocalDate.parse(requestDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
