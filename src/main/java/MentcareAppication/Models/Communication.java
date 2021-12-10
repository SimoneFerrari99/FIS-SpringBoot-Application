package MentcareAppication.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name = "COMMUNICATION_ID")
    private long communicationID;

    @ManyToOne(targetEntity = Appointment.class)
    @JoinColumn(name = "APPOINTMENT_ID")
    private Appointment appointment;

    private Date communicationDate;
    private String communicationText;
    private boolean forMedic;
    private boolean forPatient;

    public Communication(){}

    public Communication(Appointment appointment, Date communicationDate, String communicationText, boolean forMedic, boolean forPatient){
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

    public Date getCommunicationDate() {
        return communicationDate;
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
