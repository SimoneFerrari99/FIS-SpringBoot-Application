package MentcareAppication.Repositories;

import MentcareAppication.Models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
    Request findById(long id);

    Request findByPatientPatientID(long patientID);
}
