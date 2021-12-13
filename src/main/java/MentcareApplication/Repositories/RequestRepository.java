package MentcareApplication.Repositories;

import MentcareApplication.Models.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
    Request findById(long id);

    Request findByPatientPatientID(long patientID);
}
