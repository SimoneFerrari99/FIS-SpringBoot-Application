package MentcareApplication.RepositoriesTest;

import MentcareApplication.Models.Appointment;
import MentcareApplication.Models.Communication;
import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import MentcareApplication.Repositories.AppointmentRepository;
import MentcareApplication.Repositories.CommunicationRepository;
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
public class CommunicationRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private CommunicationRepository communicationRepository;
    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Communication communication;
    private Appointment appointment;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;

    @Before
    public void setUp(){
        Medic medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        Patient patient = new Patient(medic, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);

        now = LocalDate.now();
        appointment = new Appointment(medic, patient, dtf.format(now), "Venezia");

        communication = new Communication(appointment, dtf.format(now), "Le ricordo l'appuntamento di oggi.", true, false);

        medicRepository.save(medic);
        patientRepository.save(patient);
        appointmentRepository.save(appointment);
        communicationRepository.save(communication);
    }

    @Test
    public void findByAppointmentAppointmentIDTest(){
        assertEquals(communication, communicationRepository.findById(communication.getCommunicationID()));

        List<Communication> l = communicationRepository.findByAppointmentAppointmentID(communication.getCommunicationID());
        assertEquals(l.get(0), communication);
    }


}
