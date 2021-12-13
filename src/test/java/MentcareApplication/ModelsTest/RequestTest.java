package MentcareApplication.ModelsTest;

import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import MentcareApplication.Models.Request;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class RequestTest {

    private Request request;
    private Patient patient;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;


    @Before
    public void setUp(){
        patient = new Patient(null, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
        request = new Request(patient, dtf.format(now));
    }

    @Test
    public void getRequestIDTest() {
        assertEquals(0, this.request.getRequestID());
    }

    @Test
    public void getPatientTest() {
        assertEquals(patient, this.request.getPatient());
    }

    @Test
    public void getRequestDateTest() {
        assertEquals(dtf.format(now), this.request.getRequestDate());
    }

    @Test
    public void getRequestDateToLocalDateTest() {
        assertEquals(now, this.request.getRequestDateToLocalDate());
    }

    @Test
    public void isActiveTest() {
        assertTrue(this.request.isActive());
    }

    @Test
    public void setActiveTest() {
        assertTrue(this.request.isActive());
        request.setActive(false);
        assertFalse(this.request.isActive());
    }


}
