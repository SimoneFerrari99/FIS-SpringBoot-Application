package MentcareApplication.RepositoriesTest;

import MentcareApplication.Models.Patient;
import MentcareApplication.Models.Request;
import MentcareApplication.Repositories.PatientRepository;
import MentcareApplication.Repositories.RequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Request request;
    private Patient patient;

    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private LocalDate now;


    @Before
    public void setUp() {
        now = LocalDate.now();
        patient = new Patient(null, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);
        request = new Request(patient, dtf.format(now));

        patientRepository.save(patient);
        requestRepository.save(request);
    }

    @Test
    public void findByPatientPatientIDTest() {
        assertEquals(request, requestRepository.findById(request.getRequestID()));

        Request r = requestRepository.findByPatientPatientID(patient.getPatientID());
        assertEquals(r, request);
    }
}
