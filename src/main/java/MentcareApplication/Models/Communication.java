package MentcareApplication.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "COMMUNICATION_ID")
    private long communicationID;

    @ManyToOne(targetEntity = Appointment.class)
    @JoinColumn(name = "APPOINTMENT_ID")
    private Appointment appointment;

    private String communicationDate;
    private String communicationText;
    private boolean forMedic;
    private boolean forPatient;

    private Communication(){}

    public Communication(Appointment appointment, String communicationDate, String communicationText, boolean forMedic, boolean forPatient){
        this.appointment = appointment;
        this.communicationDate = communicationDate;
        this.communicationText = communicationText;
        this.forMedic = forMedic;
        this.forPatient = forPatient;
    }

    public long getCommunicationID() {
        return communicationID;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getCommunicationDate() {
        return communicationDate;
    }

    public LocalDate getCommunicationDateToLocalDate() {
        return LocalDate.parse(communicationDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getCommunicationText() {
        return communicationText;
    }

    public boolean isForMedic() {
        return forMedic;
    }

    public boolean isForPatient() {
        return forPatient;
    }

    @Override
    public String toString() {
        return "Communication{" +
                "communicationID=" + communicationID +
                ", appointment=" + appointment +
                ", communicationDate=" + communicationDate +
                ", communicationText='" + communicationText + '\'' +
                ", forMedic=" + forMedic +
                ", forPatient=" + forPatient +
                '}';
    }
}
