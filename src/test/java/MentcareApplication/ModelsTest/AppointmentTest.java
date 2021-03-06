package MentcareApplication.ModelsTest;

import MentcareApplication.Models.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class AppointmentTest {

    private Appointment appointment;
    private Medic medic;
    private Patient patient;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;

    @Before
    public void setUp(){
        medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        patient = new Patient(medic, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
        now = LocalDate.now();
        appointment = new Appointment(medic, patient, dtf.format(now), "Venezia");
    }

    @Test
    public void getAppointmentIdTest() {
        assertEquals(0, this.appointment.getAppointmentID());
    }

    @Test
    public void getMedicTest() {
        assertEquals(medic, this.appointment.getMedic());
    }

    @Test
    public void setMedicTest() {
        Medic medic1 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
        assertEquals(medic, this.appointment.getMedic());
        appointment.setMedic(medic1);
        assertEquals(medic1, this.appointment.getMedic());
    }

    @Test
    public void getPatientTest() {
        assertEquals(patient, this.appointment.getPatient());
    }

    @Test
    public void setPatientTest() {
        Patient patient1 = new Patient(medic, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
        assertEquals(patient, this.appointment.getPatient());
        appointment.setPatient(patient1);
        assertEquals(patient1, this.appointment.getPatient());

    }

    @Test
    public void getAppointmentDateTest() {
        assertEquals(dtf.format(now), this.appointment.getAppointmentDate());
    }

    @Test
    public void getAppointmentDateToLocalDateTest() {
        assertEquals(now, this.appointment.getAppointmentDateToLocalDate());
    }

    @Test
    public void getAppointmentDateToDateTest(){
        String[] parts = appointment.getAppointmentDate().split("/");
        String date = parts[2]+'-'+parts[1]+'-'+parts[0];

        assertEquals(date, this.appointment.getAppointmentDateToDate());
    }

    @Test
    public void setAppointmentDateTest() {
        assertEquals(dtf.format(now), this.appointment.getAppointmentDate());
        appointment.setAppointmentDate(dtf.format(now.plusDays(1)));
        assertEquals(dtf.format(now.plusDays(1)), this.appointment.getAppointmentDate());
    }

    @Test
    public void setAppointmentDateStringTest() {
        assertEquals(dtf.format(now), this.appointment.getAppointmentDate());
        appointment.setAppointmentDate(dtf.format(now.plusDays(1)));
        assertEquals(dtf.format(now.plusDays(1)), this.appointment.getAppointmentDate());
    }

    @Test
    public void setAppointmentDateLocalDateTest() {
        assertEquals(now, this.appointment.getAppointmentDateToLocalDate());
        appointment.setAppointmentDate(now.plusDays(1));
        assertEquals(now.plusDays(1), this.appointment.getAppointmentDateToLocalDate());
    }

    @Test
    public void getClinicTest() {
        assertEquals("Venezia", this.appointment.getClinic());
    }

    @Test
    public void setClinicTest() {
        assertEquals("Venezia", this.appointment.getClinic());
        appointment.setClinic("Padova");
        assertEquals("Padova", this.appointment.getClinic());
        appointment.setClinic("");
        assertEquals("", this.appointment.getClinic());
    }

    @Test
    public void isActiveTest() {
        assertTrue(this.appointment.isActive());
    }

    @Test
    public void setActiveTest() {
        assertTrue(this.appointment.isActive());
        appointment.setActive(false);
        assertFalse(this.appointment.isActive());
    }


    @Test
    public void toStringTest() {
        assertEquals("Appointment{" +
                "appointmentID=" + 0 +
                ", medic=" + medic +
                ", patient=" + patient +
                ", appointmentDate=" + dtf.format(now) +
                ", clinic='" + "Venezia" + '\'' +
                '}', this.appointment.toString());
    }

}
