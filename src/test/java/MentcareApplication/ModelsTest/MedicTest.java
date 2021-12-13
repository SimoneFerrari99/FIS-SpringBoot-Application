package MentcareApplication.ModelsTest;

import MentcareApplication.Models.Medic;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MedicTest {

    private Medic medic;

    @Before
    public void setUp(){
        medic = new Medic("Brad", "Pitt", "Psicologo", "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.");
    }

    @Test
    public void getFirstnameTest() {
        assertEquals("Brad", this.medic.getFirstname());
    }

    @Test
    public void getLastnameTest() {
        assertEquals("Pitt", this.medic.getLastname());
    }

    @Test
    public void getSpecializationTest() {
        assertEquals("Psicologo", this.medic.getSpecialization());
    }

    @Test
    public void getSpecializationDescriptionTest() {
        assertEquals("Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico.", this.medic.getSpecializationDescription());
    }

    @Test
    public void getFirstnameLastnameTest() {
        assertEquals("Brad Pitt", this.medic.getFirstnameLastname());
    }

    @Test
    public void toStringTest() {
        assertEquals("Medic{" +
                "medicID=" + 0+
                ", firstname='" + "Brad" + '\'' +
                ", lastname='" + "Pitt" + '\'' +
                ", specialization='" + "Psicologo" + '\'' +
                ", specializationDescription='" + "Svolge attività di prevenzione, diagnosi, intervento, promozione della salute, abilitazione-riabilitazione, sostegno e consulenza in ambito psicologico." + '\'' +
                '}', this.medic.toString());
    }

}
