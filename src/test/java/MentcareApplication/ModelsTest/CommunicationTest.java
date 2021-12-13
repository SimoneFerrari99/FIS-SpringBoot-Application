package MentcareApplication.ModelsTest;

import MentcareApplication.Models.Appointment;
import MentcareApplication.Models.Communication;
import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class CommunicationTest {

    private Communication communication1;
    private Communication communication2;
    private Communication communication3;
    private Appointment appointment;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;

    @Before
    public void setUp(){
        Medic medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        Patient patient = new Patient(medic, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);

        now = LocalDate.now();
        appointment = new Appointment(medic, patient, dtf.format(now), "Venezia");

        communication1 = new Communication(appointment, dtf.format(now), "Le ricordo l'appuntamento di oggi.", true, false);
        communication2 = new Communication(appointment, dtf.format(now), "Le ricordo l'appuntamento di oggi.", false, true);
        communication3 = new Communication(appointment, dtf.format(now), "Le ricordo l'appuntamento di oggi.", true, true);
    }

    @Test
    public void getCommunicationIDTest() {
        assertEquals(0, this.communication1.getCommunicationID());
    }

    @Test
    public void getAppointmentTest() {
        assertEquals(appointment, this.communication1.getAppointment());
    }

    @Test
    public void getCommunicationDateTest() {
        assertEquals(dtf.format(now), this.communication1.getCommunicationDate());
    }

    @Test
    public void getCommunicationDateToLocalDateTest() {
        assertEquals(now, this.communication1.getCommunicationDateToLocalDate());
    }

    @Test
    public void getCommunicationTextTest() {
        assertEquals("Le ricordo l'appuntamento di oggi.", this.communication1.getCommunicationText());
    }

    @Test
    public void isForMedicTest() {
        assertTrue(this.communication1.isForMedic());
        assertFalse(this.communication2.isForMedic());
        assertTrue(this.communication3.isForMedic());
    }

    @Test
    public void isForPatientTest() {
        assertFalse(this.communication1.isForPatient());
        assertTrue(this.communication2.isForPatient());
        assertTrue(this.communication3.isForPatient());
    }

    @Test
    public void toStringTest() {
        assertEquals("Communication{" +
                "communicationID=" + 0 +
                ", appointment=" + appointment +
                ", communicationDate=" + dtf.format(now) +
                ", communicationText='" + "Le ricordo l'appuntamento di oggi." + '\'' +
                ", forMedic=" + true +
                ", forPatient=" + false +
                '}', this.communication1.toString());
    }

}
