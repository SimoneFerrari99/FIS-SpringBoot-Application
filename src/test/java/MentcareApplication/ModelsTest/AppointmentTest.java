package MentcareApplication.ModelsTest;

import MentcareApplication.Models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class AppointmentTest {

    private Appointment appointment;

    @Before
    public void setUp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Medic medic1 = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        Patient patient1 = new Patient(medic1, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
        LocalDate now = LocalDate.now();
        appointment = new Appointment(medic1, patient1, dtf.format(now), "Venezia");
    }

    @Test
    public void getAppointmentIdTest() {
        assertEquals(0, this.appointment.getAppointmentID());
    }




}
