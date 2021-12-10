package MentcareAppication.Repositories;

import MentcareAppication.Models.Appuntamento;
import org.springframework.data.repository.CrudRepository;

public interface AppuntamentoRepository extends CrudRepository<Appuntamento, Long> {
    Appuntamento findById(long id);
}
