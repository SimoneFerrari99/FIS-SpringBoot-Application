package MentcareApplication.ModelsTest;

import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class PatientTest {

    private Patient patient;
    private Patient patientNotConfirmed;
    private Medic medic;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;


    @Before
    public void setUp(){
        medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        patient = new Patient(medic, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);

    }

    @Test
    public void getPatientIDTest() {
        assertEquals(0, this.patient.getPatientID());
    }

    @Test
    public void getMedicTest() {
        assertEquals(medic, this.patient.getMedic());
    }

    @Test
    public void setMedicToConfirmedPatientTest() {
        Medic medic1 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
        assertEquals(medic, this.patient.getMedic());
        assertTrue(this.patient.isConfirmed());
        patient.setMedic(medic1);
        assertEquals(medic1, this.patient.getMedic());
        assertTrue(this.patient.isConfirmed());
    }

    @Test
    public void setMedicToNotConfirmedPatientTest() {
        patientNotConfirmed = new Patient(null, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
        assertNull(patientNotConfirmed.getMedic());
        assertFalse(patientNotConfirmed.isConfirmed());
        patientNotConfirmed.setMedic(medic);
        assertEquals(medic, patientNotConfirmed.getMedic());
        assertTrue(patientNotConfirmed.isConfirmed());
    }


    @Test
    public void getFirstnameTest() {
        assertEquals("Johnny", this.patient.getFirstname());
    }

    @Test
    public void setFirstnameTest() {
        assertEquals("Johnny", this.patient.getFirstname());
        patient.setFirstname("Test");
        assertEquals("Test", this.patient.getFirstname());
        patient.setFirstname("");
        assertEquals("", this.patient.getFirstname());
    }

    @Test
    public void getLastnameTest() {
        assertEquals("Depp", this.patient.getLastname());
    }

    @Test
    public void setLastnameTest() {
        assertEquals("Depp", this.patient.getLastname());
        patient.setLastname("Test");
        assertEquals("Test", this.patient.getLastname());
        patient.setLastname("");
        assertEquals("", this.patient.getLastname());
    }

    @Test
    public void getCfTest() {
        assertEquals("JD63YP", this.patient.getCf());
    }

    @Test
    public void setCfTest() {
        assertEquals("JD63YP", this.patient.getCf());
        patient.setCf("Test");
        assertEquals("Test", this.patient.getCf());
        patient.setCf("");
        assertEquals("", this.patient.getCf());
    }

    @Test
    public void getBirthDateTest() {
        assertEquals("09/06/1963", this.patient.getBirthDate());
    }

    @Test
    public void getBirthDateToLocalDateTest() {
        assertEquals(LocalDate.parse("09/06/1963", dtf), this.patient.getBirthDateToLocalDate());
    }

    @Test
    public void getBirthDateToDateTest() {
        assertEquals("1963-06-09", this.patient.getBirthDateToDate());
    }

    @Test
    public void setBirthDateStringTest() {
        assertEquals("09/06/1963", this.patient.getBirthDate());
        patient.setBirthDate("01/01/2000");
        assertEquals("01/01/2000", this.patient.getBirthDate());
        patient.setBirthDate("");
        assertEquals("", this.patient.getBirthDate());
    }

    @Test
    public void setBirthDateLocalDateTest() {
        assertEquals("09/06/1963", this.patient.getBirthDate());
        patient.setBirthDate(LocalDate.parse("01/01/2000", dtf));
        assertEquals("01/01/2000", this.patient.getBirthDate());
    }

    @Test
    public void getCityOfResidenceTest() {
        assertEquals("Venezia", this.patient.getCityOfResidence());
    }

    @Test
    public void setCityOfResidenceTest() {
        assertEquals("Venezia", this.patient.getCityOfResidence());
        patient.setCityOfResidence("Padova");
        assertEquals("Padova", this.patient.getCityOfResidence());
        patient.setCityOfResidence("");
        assertEquals("", this.patient.getCityOfResidence());
    }

    @Test
    public void getProblemCategoryTest() {
        assertEquals("Disturbo ossessivo compulsivo (DOC)", this.patient.getProblemCategory());
    }

    @Test
    public void setProblemCategoryTest() {
        assertEquals("Disturbo ossessivo compulsivo (DOC)", this.patient.getProblemCategory());
        patient.setProblemCategory("Test");
        assertEquals("Test", this.patient.getProblemCategory());
        patient.setProblemCategory("");
        assertEquals("", this.patient.getProblemCategory());
    }

    @Test
    public void getProblemDescriptionTest() {
        assertEquals("Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", this.patient.getProblemDescription());
    }

    @Test
    public void setProblemDescriptionTest() {
        assertEquals("Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", this.patient.getProblemDescription());
        patient.setProblemDescription("Test");
        assertEquals("Test", this.patient.getProblemDescription());
        patient.setProblemDescription("");
        assertEquals("", this.patient.getProblemDescription());
    }

    @Test
    public void isDangerousTest() {
        assertFalse(this.patient.isDangerous());
    }

    @Test
    public void setDangerousTest() {
        assertFalse(this.patient.isDangerous());
        patient.setDangerous(true);
        assertTrue(this.patient.isDangerous());
    }

    @Test
    public void isConfirmedTest() {
        patientNotConfirmed = new Patient(null, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);

        assertTrue(this.patient.isConfirmed());
        assertFalse(this.patientNotConfirmed.isConfirmed());

    }

    @Test
    public void setConfirmedTest() {
        patientNotConfirmed = new Patient(null, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);

        assertTrue(this.patient.isConfirmed());
        assertFalse(this.patientNotConfirmed.isConfirmed());

        patient.setConfirmed(false);
        patientNotConfirmed.setConfirmed(true);

        assertFalse(this.patient.isConfirmed());
        assertTrue(this.patientNotConfirmed.isConfirmed());
    }

    @Test
    public void getFirstnameLastnameTest() {
        assertEquals("Johnny Depp", this.patient.getFirstnameLastname());
    }

    @Test
    public void toStringTest() {
        assertEquals("Patient{" +
                "patientID=" + 0 +
                ", medic=" + medic +
                ", firstname='" + "Johnny" + '\'' +
                ", lastname='" + "Depp" + '\'' +
                ", cf='" + "JD63YP" + '\'' +
                ", birthDate=" + "09/06/1963" +
                ", cityOfResidence='" + "Venezia" + '\'' +
                ", problemCategory='" + "Disturbo ossessivo compulsivo (DOC)" + '\'' +
                ", problemDescription='" + "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani" + '\'' +
                ", dangerous=" + false +
                '}', this.patient.toString());
    }

}
