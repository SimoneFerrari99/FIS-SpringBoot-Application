package MentcareAppication.Repositories;

import MentcareAppication.Models.Paziente;
import org.springframework.data.repository.CrudRepository;

public interface PazienteRepository extends CrudRepository<Paziente, Long> {
    Paziente findById(long id);
}
