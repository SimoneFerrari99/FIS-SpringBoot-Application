package MentcareAppication.Repositories;

import MentcareAppication.Models.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    Appointment findById(long id);
}
