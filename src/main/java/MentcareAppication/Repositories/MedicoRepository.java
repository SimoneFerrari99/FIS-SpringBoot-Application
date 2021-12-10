package MentcareAppication.Repositories;

import MentcareAppication.Models.Medic;
import org.springframework.data.repository.CrudRepository;

public interface MedicoRepository extends CrudRepository<Medic, Long> {
    Medic findById(long id);

}
