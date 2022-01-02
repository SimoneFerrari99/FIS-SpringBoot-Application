package MentcareApplication.RepositoriesTest;

import MentcareApplication.Models.Medic;
import MentcareApplication.Models.Patient;
import MentcareApplication.Repositories.MedicRepository;
import MentcareApplication.Repositories.PatientRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class PatientRepositoryTest {

    @Autowired
    private MedicRepository medicRepository;
    @Autowired
    private PatientRepository patientRepository;

    private Medic medic1, medic2;
    private Patient patient1, patient2;


    @Before
    public void setUp() {
        medic1 = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        medic2 = new Medic("Tom", "Cruise", "Disturbo del sonno", "Studia con precisione eventuali alterazioni del sonno e della respirazione durante il sonno e offre le soluzioni terapeutiche adeguate.");
        patient1 = new Patient(medic1, "Johnny", "Depp", "JD63YP", "09/06/1963", "Venezia", "Disturbo ossessivo compulsivo (DOC)", "Ossessione per le mani pulite. Dopo ogni singola azione svolta, il paziente necessita di un lavaggio delle mani", false);
        patient2 = new Patient(medic2, "Angelina", "Jolie", "AJ75AE", "04/06/1975", "Padova", "Attacchi di panico", "Attacchi di panico molto frequenti, spesso manifestati a fine giornata, rendono la qualità del sonno molto scadente.", false);

        medicRepository.save(medic1);
        medicRepository.save(medic2);
        patientRepository.save(patient1);
        patientRepository.save(patient2);
    }

    @Test
    public void findByMedicMedicIDTest() {
        assertEquals(patient1, patientRepository.findById(patient1.getPatientID()));
        assertEquals(patient2, patientRepository.findById(patient2.getPatientID()));

        List<Patient> l1 = patientRepository.findByMedicMedicID(patient1.getMedic().getMedicID());
        List<Patient> l2 = patientRepository.findByMedicMedicID(patient2.getMedic().getMedicID());
        assertEquals(l1.get(0), patient1);
        assertEquals(l2.get(0), patient2);
    }
}
