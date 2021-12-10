package MentcareAppication.Repositories;

import MentcareAppication.Models.Comunicazione;
import org.springframework.data.repository.CrudRepository;

public interface ComunicazioneRepository extends CrudRepository<Comunicazione, Long> {
    Comunicazione findById(long id);
}
