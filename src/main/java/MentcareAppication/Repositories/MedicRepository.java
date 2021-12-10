package MentcareAppication.Repositories;

import MentcareAppication.Models.Medic;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MedicRepository extends CrudRepository<Medic, Long> {
    Medic findById(long id);
}
