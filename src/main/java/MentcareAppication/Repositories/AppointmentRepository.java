package MentcareAppication.Repositories;

import MentcareAppication.Models.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    Appointment findById(long id);
    List<Appointment> findByMedicMedicID(long id);
    List<Appointment> findByPatientPatientID(long patientID);
}
