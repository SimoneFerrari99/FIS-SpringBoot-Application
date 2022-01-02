package MentcareApplication.ControllersTest.Utils;

import MentcareApplication.Controllers.Utils.*;
import MentcareApplication.Models.*;
import MentcareApplication.Repositories.*;
import net.sf.cglib.core.Local;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UtilsTest {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RequestRepository requestRepository;

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;

    @Before
    public void setUp(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Medic medic1 = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        medicRepository.save(medic1);
        Medic medic2 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
        medicRepository.save(medic2);

        Patient patient1 = new Patient(medic1, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
        patientRepository.save(patient1);
        Patient patient2 = new Patient(medic2, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
        patientRepository.save(patient2);
        Patient patient3 = new Patient(medic1, "Robert", "De Niro", "RD75TO", "07/08/1943", "Verona", "Attacchi di ira", "Repentini cambio d'umore caratterizzano la persona, spesso rendendola aggressiva e pericolosa.", true);
        patientRepository.save(patient3);

        LocalDate now = LocalDate.now();

        Patient patient4 = new Patient(null, "Harrison", "Ford", "HF75ND", "13/07/1942", "Belluno", "", "Difficoltà nel mantenere l'attenzione, anche per brevi periodi di tempo", false);
        patientRepository.save(patient4);
        requestRepository.save(new Request(patient4, dtf.format(now)));

        Appointment appointment1 = new Appointment(medic1, patient1, dtf.format(now), "Mestre");
        appointmentRepository.save(appointment1);
        Appointment appointment2 = new Appointment(medic2, patient2, dtf.format(now), "Vicenza");
        appointmentRepository.save(appointment2);

        LocalDate tomorrow = now.plusDays(1);
        Appointment appointment3 = new Appointment(medic1, patient3, dtf.format(now), "Verona");
        appointmentRepository.save(appointment3);
        Appointment appointment4 = new Appointment(medic1, patient1, dtf.format(tomorrow), "Marghera");
        appointmentRepository.save(appointment4);
        Appointment appointment5 = new Appointment(medic2, patient2, dtf.format(tomorrow), "Padova");
        appointmentRepository.save(appointment5);

        LocalDate yesterday = now.minusDays(2);
        Appointment appointment6 = new Appointment(medic1, patient3, dtf.format(yesterday), "Bassano");
        Appointment appointment7 = new Appointment(medic1, patient1, dtf.format(yesterday), "Oriago");
        Appointment appointment8 = new Appointment(medic2, patient2, dtf.format(yesterday), "Monselice");
        appointmentRepository.save(appointment6);
        appointmentRepository.save(appointment7);
        appointmentRepository.save(appointment8);
    }

    @Test
    public void getTodayAppointmentsTest() {
        List<Appointment> appointments = Utils.getTodayAppointments(appointmentRepository);
        for(Appointment a : appointments){
            assertTrue(a.getAppointmentDateToLocalDate().isEqual(LocalDate.now()));
        }
    }

    @Test
    public void getFutureAppointmentsTest() {
        List<Appointment> appointments = Utils.getFutureAppointments(appointmentRepository, true);
        for(Appointment a : appointments){
            assertTrue(a.getAppointmentDateToLocalDate().isAfter(LocalDate.now()) || a.getAppointmentDateToLocalDate().isEqual(LocalDate.now()));
        }
    }

    @Test
    public void getRequestsTest() {
        List<Request> requests = Utils.getRequests(requestRepository);
        for(Request r : requests){
            assertTrue(r.isActive());
        }
    }

    @Test
    public void getPatientsTest() {
        List<Patient> patients = Utils.getPatients(patientRepository);
        for(Patient p : patients){
            assertTrue(p.isConfirmed());
        }
    }

    @Test
    public void getFutureAppointmentsByMedicIDTest() {
        List<Appointment> appointments = Utils.getAppointmentsByMedicID(appointmentRepository, 1, false);
        for(Appointment a : appointments){
            assertTrue(a.isActive());
            assertTrue(a.getAppointmentDateToLocalDate().isAfter(LocalDate.now()) || a.getAppointmentDateToLocalDate().isEqual(LocalDate.now()));
        }
    }

    @Test
    public void getPastAppointmentsByMedicIDTest() {
        List<Appointment> appointments = Utils.getAppointmentsByMedicID(appointmentRepository, 1, true);
        for(Appointment a : appointments){
            assertTrue(a.isActive());
            assertTrue(a.getAppointmentDateToLocalDate().isBefore(LocalDate.now()));
        }
    }

    @Test
    public void getFutureAppointmentsByPatientIDTest() {
        List<Appointment> appointments = Utils.getAppointmentsByPatientID(appointmentRepository, 1, false);
        for(Appointment a : appointments){
            assertTrue(a.isActive());
            assertTrue(a.getAppointmentDateToLocalDate().isAfter(LocalDate.now()) || a.getAppointmentDateToLocalDate().isEqual(LocalDate.now()));
        }
    }

    @Test
    public void getPastAppointmentsByPatientIDTest() {
        List<Appointment> appointments = Utils.getAppointmentsByPatientID(appointmentRepository, 1, true);
        for(Appointment a : appointments){
            assertTrue(a.isActive());
            assertTrue(a.getAppointmentDateToLocalDate().isBefore(LocalDate.now()));
        }
    }

    @Test
    public void getPatientsByMedicIDTest() {
        List<Patient> patients = Utils.getPatientsByMedicID(patientRepository,1);
        for(Patient p : patients){
            assertTrue(p.isConfirmed());
            assertEquals(1, p.getMedic().getMedicID());
        }
    }

    @Test
    public void convertDateToSlashTest(){
        String stringDate = "2021-01-02";
        assertEquals("02/01/2021", Utils.convertDateToSlash(stringDate));
    }


}
