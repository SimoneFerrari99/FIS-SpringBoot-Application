package MentcareAppication.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Communication {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "COMMUNICATION_ID")
    private long communicationID;

    @ManyToOne(targetEntity = Appointment.class)
    @JoinColumn(name = "APPOINTMENT_ID")
    private final Appointment appointment;

    private final Date communicationDate;
    private final String communicationText;
    private final boolean forMedic;
    private final boolean forPatient;

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
}
