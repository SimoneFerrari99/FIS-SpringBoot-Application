package MentcareApplication.RepositoriesTest;

import MentcareApplication.Models.Appointment;
import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import MentcareApplication.Repositories.AppointmentRepository;
import MentcareApplication.Repositories.MedicRepository;
import MentcareApplication.Repositories.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Appointment appointment1, appointment2;
    private Medic medic1, medic2;
    private Patient patient1, patient2;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;

    @Before
    public void setUp(){
        medic1 = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        medic2 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
        patient1 = new Patient(medic1, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
        patient2 = new Patient(medic2, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);

        now = LocalDate.now();
        appointment1 = new Appointment(medic1, patient1, dtf.format(now), "Venezia");
        appointment2 = new Appointment(medic2, patient2, dtf.format(now), "Vicenza");

        medicRepository.save(medic1);
        medicRepository.save(medic2);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
    }

    @Test
    public void findByMedicMedicIDTest(){
        assertEquals(appointment1, appointmentRepository.findById(appointment1.getAppointmentID()));
        assertEquals(appointment2, appointmentRepository.findById(appointment2.getAppointmentID()));

        List<Appointment> l1 = appointmentRepository.findByMedicMedicID(medic1.getMedicID());
        List<Appointment> l2 = appointmentRepository.findByMedicMedicID(medic2.getMedicID());
        assertEquals(l1.get(0), appointment1);
        assertEquals(l2.get(0), appointment2);
    }

    @Test
    public void findByPatientPatientIDTest(){
        assertEquals(appointment1, appointmentRepository.findById(appointment1.getAppointmentID()));
        assertEquals(appointment2, appointmentRepository.findById(appointment2.getAppointmentID()));

        List<Appointment> l1 = appointmentRepository.findByPatientPatientID(patient1.getPatientID());
        List<Appointment> l2 = appointmentRepository.findByPatientPatientID(patient2.getPatientID());
        assertEquals(l1.get(0), appointment1);
        assertEquals(l2.get(0), appointment2);
    }









}
