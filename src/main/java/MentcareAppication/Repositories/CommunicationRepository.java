package MentcareAppication.Repositories;

import MentcareAppication.Models.Communication;
import org.springframework.data.repository.CrudRepository;

public interface CommunicationRepository extends CrudRepository<Communication, Long> {
    Communication findById(long id);
}
