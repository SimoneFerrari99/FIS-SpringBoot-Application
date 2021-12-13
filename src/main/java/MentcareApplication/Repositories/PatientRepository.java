package MentcareApplication.Repositories;

import MentcareApplication.Models.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PatientRepository extends CrudRepository<Patient, Long> {
    Patient findById(long id);
    List<Patient> findByMedicMedicID(long medicID);
}
