package MentcareApplication.Repositories;

import MentcareApplication.Models.Medic;
import org.springframework.data.repository.CrudRepository;

public interface MedicRepository extends CrudRepository<Medic, Long> {
    Medic findById(long id);
}
