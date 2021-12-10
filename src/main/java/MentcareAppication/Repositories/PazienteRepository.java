package MentcareAppication.Repositories;

import MentcareAppication.Models.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PazienteRepository extends CrudRepository<Patient, Long> {
    Patient findById(long id);
}
