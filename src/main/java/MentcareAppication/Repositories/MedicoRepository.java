package MentcareAppication.Repositories;

import MentcareAppication.Models.Medico;
import org.springframework.data.repository.CrudRepository;

public interface MedicoRepository extends CrudRepository<Medico, Long> {
    Medico findById(long id);

}
