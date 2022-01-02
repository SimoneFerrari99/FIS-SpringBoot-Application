package MentcareApplication.RepositoriesTest;

import MentcareApplication.Models.Medic;
import MentcareApplication.Repositories.MedicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class MedicRepositoryTest {
    @Autowired
    private MedicRepository medicRepository;

    private Medic medic;

    @Before
    public void setUp(){
        medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
        medicRepository.save(medic);
    }

    @Test
    public void findByIdTest(){
        assertEquals(medic, medicRepository.findById(medic.getMedicID()));
    }

}
